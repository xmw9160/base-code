package com.xmw.aviator;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.Options;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * google aviator
 * https://blog.csdn.net/u011870280/article/details/80400959
 * https://code.google.com/archive/p/aviator/wikis/User_Guide_zh.wiki
 * https://github.com/killme2008/aviator/wiki
 * <p>
 * 场景:
 * 1. 风控系统，是要给业务配置表达式的，然后程序执行它
 * 2. 业务规则可配置执行
 *
 * @author xmw.
 * @date 2018/11/3 10:28 AM.
 */
public class TestAviator {

    public Integer getAge() {
        return 20;
    }

    @Test
    public void testExpress() {
        int[] intArray = new int[]{6, 7, 8, 9};
        HashMap<String, Object> env = Maps.newHashMap();
        env.put("a", intArray);

        // 6
        System.out.println(AviatorEvaluator.execute("1 + 2 + 3"));
        // 107
        System.out.println(AviatorEvaluator.execute("a[1] + 100", env));
        // a[1]=7
        System.out.println(AviatorEvaluator.execute("'a[1]=' + a[1]", env));
        // 4 数组长度
        System.out.println(AviatorEvaluator.execute("count(a)", env));
        // 30 数组求和
        System.out.println(AviatorEvaluator.execute("reduce(a, +, 0)", env));
        // false 检测数组每个元素都在 0 <= e < 5 之间. ge greater than equals, lt less than
        System.out.println(AviatorEvaluator.execute("seq.every(a, seq.and(seq.ge(0), seq.lt(5)))", env));
    }

    @Test
    public void testAviator() throws Exception {
        // 默认 AviatorEvaluator 以执行速度优先:
        AviatorEvaluator.setOption(Options.OPTIMIZE_LEVEL, AviatorEvaluator.EVAL);
        // 编译速度优先,这样不会做编译优化:
        //AviatorEvaluator.setOption(Options.OPTIMIZE_LEVEL, AviatorEvaluator.COMPILE);

        // 如果想查看每个表达式生成的字节码，可以通过打开 Trace 选项
        AviatorEvaluator.setOption(Options.TRACE, true);

        // 默认是打印到标准输出,你可以改变输出指向:
        // AviatorEvaluator.setTraceOutputStream(new FileOutputStream(new File("aviator.log")));

        List<Integer> list = Lists.newArrayList(30, 3, 50);
        HashMap<String, Object> env = Maps.newHashMap();
        env.put("list", list);
        env.put("email", "xmw0619@gmial.com");
        env.put("test", new TestAviator());
        env.put("date", new Date());
        // 正则匹配: 会在env 中插入$0=xmw0619@gmial.com, $1=xmw0619
        // 匹配成功后, Aviator 会自动将匹配成功的捕获分组(capturing groups) 放入 env ${num}的变量中,
        // 其中$0 指代整个匹配的字符串,而$1表示第一个分组，$2表示第二个分组以此类推。
        // 分组捕获放入 env 是默认开启的，因此如果传入的 env 不是线程安全并且被并发使用，可能存在线程安全的隐患
        String username = (String) AviatorEvaluator.execute("email=~/([\\w0-8]+)@\\w+[\\.\\w+]+/ ? $1 : 'unknow' ", env);
        // xmw0619
        System.out.println(username);

        // yes
        System.out.println(AviatorEvaluator.exec("param > 0 ? 'yes':'no'", 1));
        // 20
        System.out.println(AviatorEvaluator.execute("'test.age ='+test.age", env));
        // false 判断key及其value是否存在
        // nil是 Aviator 内置的常量,类似 java 中的null,表示空的值。
        // il跟null不同的在于,在 java 中null只能使用在==、!=的比较运算符,而nil还可以使用>、>=、<、<=等比较运算符。
        // Aviator 规定,任何对象都比nil大除了nil本身
        System.out.println(AviatorEvaluator.execute(" list == nil", env));
        // true
        // Aviator 并不支持日期类型,如果要比较日期,你需要将日期写字符串的形式,
        // 并且要求是形如 “yyyy-MM-dd HH:mm:ss:SS”的字符串,否则都将报错
        System.out.println(AviatorEvaluator.execute(" date > '2018-11-03 00:00:00:00'", env));
        System.out.println(AviatorEvaluator.execute("'---------date: ' + date", env));

        Object result = AviatorEvaluator.execute("count(list)", env);
        // 3
        System.out.println(result);
        result = AviatorEvaluator.execute("reduce(list, +, 0)", env);
        // 83
        System.out.println(result);
        // [30, 50] seq.gt函数用于生成一个谓词,表示大于某个值
        result = AviatorEvaluator.execute("filter(list, seq.gt(25))", env);
        System.out.println(result);
        // true
        result = AviatorEvaluator.execute("include(list, 30)", env);
        System.out.println(result);
        // [3, 30, 50]
        result = AviatorEvaluator.execute("sort(list)", env);
        System.out.println(result);
        // 遍历整个集合 print list
        AviatorEvaluator.execute("map(list, println)", env);

        // 三元表达式
        // Aviator的三元表达式对于两个分支的结果类型并不要求一致,可以是任何类型,这一点与 java 不同。
        AviatorEvaluator.exec("a>0? 'yes':'no'", 1);  // yes

        // 大数计算和精度 BigInteger/BigDecimal
        // 199999999999999999999999999999998
        System.out.println(AviatorEvaluator.exec("99999999999999999999999999999999 + 99999999999999999999999999999999"));
    }

    @Test
    // 从4.0.0开始， aviator支持以分号 ; 隔开的多行表达式，对于多行表达式求值的结果将是最后一个表达式的结果
    public void testAviator4() {
//        AviatorEvaluator.execute("println('hello world'); println(1+2+3); print(100-1)");

        // instance invoke
        AviatorEvaluatorInstance instance = AviatorEvaluator.newInstance();
        instance.execute("println('hello world'); println(1+2+3); println(100-1)");

        // lambda lambda (参数1,参数2...) -> 参数体表达式 end
        System.out.println("---------lambda-----------");
        Object exec = AviatorEvaluator.exec("(lambda (x,y) -> x + y end)(x,y)", 1, 2);
        System.out.println(exec);

        // 匿名函数可以作为参数使用，也可以作为结果返回
        // 匿名函数更大的用户在于seq库配合高阶函数使用。
        Object exec1 = AviatorEvaluator
                .exec("(lambda (x) -> lambda(y) -> lambda(z) -> x + y + z end end end)(1)(2)(3)");
        System.out.println(exec1);

        // custom function
        AviatorEvaluator.defineFunction("add", "lambda (x,y) -> x + y end");
        System.out.println(AviatorEvaluator.exec("add(66, 67)"));
    }

    @Test
    public void testParam() {
        AviatorEvaluator.execute(" 'a\"b' ");           // 字符串 a"b
        AviatorEvaluator.execute(" \"a\'b\" ");         // 字符串 a'b
        AviatorEvaluator.execute(" 'hello ' + 3 ");     // 字符串 hello 3
        AviatorEvaluator.execute(" 'hello '+ unknow "); // 字符串 hello null

        // exec
        String name = "dennis";
        Object exec = AviatorEvaluator.exec(" 'hello ' + yourName ", name);// hello dennis
        System.out.println(exec);

        // function invoke
        AviatorEvaluator.execute("string.length('hello')");  // 5
        // true
        AviatorEvaluator.execute("string.contains(\"test\", string.substring('hello', 1, 2))");
    }

    @Test
    public void testCustomFunction() {
        // 注册函数
        AddFunction addFunction = new AddFunction();
        AviatorEvaluator.addFunction(addFunction);
        System.out.println(AviatorEvaluator.execute("add(1, 2)"));           // 3.0
        System.out.println(AviatorEvaluator.execute("add(add(1, 2), 100)")); // 103.0
        // 移除函数
        AviatorEvaluator.removeFunction(addFunction);
        // Could not find function named 'add'
        //System.out.println(AviatorEvaluator.execute("add(1, 2)"));
    }

    @Test
    public void testCompileExpression() {
        String expression = "a-(b-c)>100";
        // 编译表达式
        Expression compiledExp = AviatorEvaluator.compile(expression, true);
        Map<String, Object> env = Maps.newHashMap();
        env.put("a", 100.3);
        env.put("b", 45);
        env.put("c", -199.100);
        // 执行表达式
        Boolean result = (Boolean) compiledExp.execute(env);
        System.out.println(result);  // false
    }

    @Test
    // 以大写字母N为后缀的整数都被认为是big int
    // 以大写字母M为后缀的数字都被认为是decimal,默认Aviator的计算精度为MathContext.DECIMAL128
    // AviatorEvaluator.setOption(Options.MATH_CONTEXT, MathContext.DECIMAL64);
    // long < big int < decimal < double的规则做提升
    public void testOperation() {
        Object rt = AviatorEvaluator.exec("9223372036854775807100.356M * 2");
        System.out.println(rt + " " + rt.getClass());  // 18446744073709551614200.712 class java.math.BigDecimal
        rt = AviatorEvaluator.exec("92233720368547758074+1000");
        System.out.println(rt + " " + rt.getClass());  // 92233720368547759074 class java.math.BigInteger
        BigInteger a = new BigInteger(String.valueOf(Long.MAX_VALUE) + String.valueOf(Long.MAX_VALUE));
        BigDecimal b = new BigDecimal("3.2");
        BigDecimal c = new BigDecimal("9999.99999");
        rt = AviatorEvaluator.exec("a+10000000000000000000", a);
        System.out.println(rt + " " + rt.getClass());  // 92233720368547758089223372036854775807 class java.math.BigInteger
        rt = AviatorEvaluator.exec("b+c*2", b, c);
        System.out.println(rt + " " + rt.getClass());  // 20003.19998 class java.math.BigDecimal
        rt = AviatorEvaluator.exec("a*b/c", a, b, c);
        System.out.println(rt + " " + rt.getClass());  // 2.951479054745007313280155218459508E+34 class java.math.BigDecimal
    }

    @Test
    /**
     * aviator 拥有强大的操作集合和数组的 seq 库。整个库风格类似函数式编程中的高阶函数。
     * 在aviator中, 数组以及java.util.Collection下的子类都称为seq,
     * 可以直接利用seq库进行遍历、过滤和聚合等操作
     */
    public void testSeq() {
        ArrayList<Integer> list = Lists.newArrayList();
        list.add(3);
        list.add(20);
        list.add(10);
        Map<String, Object> env = Maps.newHashMap();
        env.put("list", list);
        Object result = AviatorEvaluator.execute("count(list)", env);
        System.out.println(result);  // 3
        result = AviatorEvaluator.execute("reduce(list, +, 0)", env);
        System.out.println(result);  // 33
        result = AviatorEvaluator.execute("filter(list, seq.gt(9))", env);
        System.out.println(result);  // [10, 20]
        result = AviatorEvaluator.execute("include(list, 10)", env);
        System.out.println(result);  // true
        result = AviatorEvaluator.execute("sort(list)", env);
        System.out.println(result);  // [3, 10, 20]
        AviatorEvaluator.execute("map(list, print)", env);
    }
}
