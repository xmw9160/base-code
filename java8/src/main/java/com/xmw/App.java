package com.xmw;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Hello world!
 */
public class App {

    static String str0 = "0123456789";
    static String str1 = "0123456789";
    volatile int i = 20;


    public static void main(String[] args) {
//        System.out.println(8 >> 1);
//        System.out.println(1 << 4);
//        System.out.println(8>>>2);

//        Map<String, String> map = new HashMap<>();
//        map.put("1", "2");
//        System.out.println(map.get("1"));

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        String time = sdf.format(new Date());
//        System.out.println(time);  //201801242012184 20180124201311

//        System.out.println((1 << 31) -1);
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println((1 << 31));
//        System.out.println(Integer.MIN_VALUE);

//        Long temp = 1L;
//        System.out.println(temp << 64);

//        Integer a = 128;
//        Integer b = 128;
//        System.out.println(a == b);

//        System.out.println(4 << 0);

//        BigDecimal a = new BigDecimal(4.000000000000000000000001);
//        BigDecimal b = new BigDecimal(4.000000000000000000000001);
//        System.out.println("== "+ (a == b));
//        int i = a.compareTo(b);
//        System.out.println("compare: " + i);
//        boolean equals = a.equals(a);
//        System.out.println("equals: " + equals);

//        Map<String, String> map = new HashMap<>();
//        String put = map.put("key", "value1");
//        System.out.println(put);
//        String key = map.put("key", "value1=2");
//        System.out.println(key);
//        System.out.println(map.get("key"));

        // 验证Integer缓存
//        Integer i = new Integer(1);
//        System.out.println(i);

//        String str2 = str1.substring(5);
//        System.out.println(str2);
//        String str3 = new String(str2);
//        String str4 = new String(str3.toCharArray());
//        str0 = null;

//        int i = 0;
//        System.out.println(i++);
//        int j = 0;
//        System.out.println(++j);
//        System.out.println("i=" + i + ",j=" + j);

        // ++i 没有中间缓存变量, 理论上比 i++ 更加高效
//        int h = 0;
        // i++ 是执行完后面的语句, 才加1
        /*
            i++ 有中间缓存变量, i = i++ 等价于
            temp = i;
            i = i + 1;
            i = temp;
         */
//        h = h++;
//        System.out.println(h);
//        int l = 0;
        // ++i 就先加1, 才执行后面的语句
        // j = ++j 相当于 j = j = j + 1
//        l = ++l;
//        System.out.println(l);

//        int[] a = new int[20];
//        int[] b = new int[20];

//        byte a = 127;
//        System.out.println(++a);   // -128
//        a += 1;
//        System.out.println(a);

//        P1:for (int i = 0; i < 20; i++) {
//            P2:for (int i1 = 0; i1 < 3; i1++) {
//                if (i == 2) {
//                    break P1;
//                }
//                for (int i2 = 0; i2 < 4; i2++) {
//                    if (i == 1) {
//                        break P2;
//                    }
//                }
//            }
//        }

        // 使用^交换两个数的值
//        int a = 30, b = 29;
//        a = a^b;
//        b = a^b;
//        a = b^a;
//        System.out.println(a);
//        System.out.println(b);

//        System.out.println("Hello" + 'a' + 1); //Helloa1
//        System.out.println('a' + 1 + "Hello"); //98Hello

//        System.out.println(-12/0.00D);  //-Infinity

//        Byte test = 5;
//        System.out.println(test == 5);              // true
//        System.out.println(test.equals(5));         // false
//        System.out.println(test.equals((byte)5));   // true
//        String str = "        ";
//        System.out.println(StringUtils.isBlank(str));   // true
        // str == null || str.length == 0
//        System.out.println(Strings.isNullOrEmpty(str)); // false

//        System.out.println("1".hashCode());
//        System.out.println(Integer.valueOf(1).hashCode());

//        Map<Long, String> map = new HashMap<>();
//        map.put(1L, "11111");
//        map.put(2L, "222222");
//        map.put(3L, "333333");
//        System.out.println(map.get(Integer.valueOf(2).toString()));  // null
//        System.out.println(map.get(2L));
//        System.out.println(map.get(Long.valueOf(1)));

//        Map<String, String> map2 = new ConcurrentHashMap<>();
//        String aaa = map2.computeIfAbsent("AAA", key -> map2.computeIfAbsent("CCC", key2 -> "2222"));
//        System.out.println(aaa);

        Map<String, String> map = new ConcurrentHashMap<>();
        // AaAa AaBb BbBb BbAa CcCc CCCC CcCc DDDD
        String s = map.computeIfAbsent("AaAa", key -> map.computeIfAbsent("BBBB", key2 -> "value"));
        System.out.println(s);
    }
}
