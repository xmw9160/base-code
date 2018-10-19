package com.xmw.java8.java8;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * FlatMap
 *
 * @author mingwei.xia
 * @date 2018/8/22 15:57
 * @since V1.0
 */
public class FlatMapExample2 {
    public static void main(String[] args) {
        test2();
    }

    private static void test1() {
        String[][] data = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};
        //Stream<String[]>
        Stream<String[]> temp = Arrays.stream(data);
        //filter a stream of string[], and return a string[]?
        Stream<String[]> stream = temp.filter(x -> "a".equals(Arrays.toString(x)));
        stream.forEach(System.out::println);
    }

    private static void test2() {
        String[][] data = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};
        //Stream<String[]>
//        Stream<String[]> temp = Arrays.stream(data);
//        //Stream<String>, GOOD!
//        Stream<String> stringStream = temp.flatMap(Arrays::stream);
//        Stream<String> stream = stringStream.filter("a"::equals);
//        stream.forEach(System.out::println);

        Arrays.stream(data)
                .flatMap(Arrays::stream)
                .filter("a"::equals).forEach(System.out::println);
    }
}
