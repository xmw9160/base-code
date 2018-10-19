package com.xmw.java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import static java.util.Comparator.comparing;

/**
 * @author mingwei.xia
 * @date 2018/6/21 11:15
 */
public class LambdaTest01 {
    public static void main(String[] args) {
//        Thread thread = new Thread(()-> System.out.println("hello..."));
//        thread.start();

        // (String s) -> "Iron Man"
        // (Strings)->{return "IronMan";}

        // 错误：Lambda表达式引用的局部变量必须是最终的（final）或事实上最终的
//        int number = 5;
//        Runnable r = () -> System.out.println(number);
//        number = 6;

//        List<String> str = Arrays.asList("a","b","A","B");
//        str.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
//        str.sort(String::compareToIgnoreCase);
//        System.out.println(str.toString());

        List<Apple> inventory = new ArrayList<>();
        Comparator<Apple> c = comparing(Apple::getWeight);

        // 按重量递减排序
        inventory.sort(comparing(Apple::getWeight).reversed());

        inventory.sort(comparing(Apple::getWeight)
                .reversed()                          // 按重量递减排序
                .thenComparing(Apple::getCountry));  // 两个苹果一样重时，进一步按国家排序

        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);  // f -> g
        Function<Integer, Integer> j = f.compose(g);  // g -> f
        int result = h.apply(6);
        Integer apply = j.apply(6);
//        System.out.println(result);
//        System.out.println(apply);

        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> pipeline = addHeader.andThen(Letter::checkSpelling).andThen(Letter::addFooter);
        String apply1 = pipeline.apply("999");
//        System.out.println(apply1);
    }
}
