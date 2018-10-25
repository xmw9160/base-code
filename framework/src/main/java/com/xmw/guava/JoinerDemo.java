package com.xmw.guava;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Joiner;

/**
 * Guava Joiner
 * 使用场景: 把List集合中的元素通过特定的分割符，拼接成一个字符串
 * <p>
 * Date 2018/1/28.
 * Author xmw .
 */
public class JoinerDemo {

    /**
     * Joiner 提供了各种方法来处理字符串加入操作，对象等。
     * Joiner的实例不可变的，因此是线程安全的。
     * 对于Joiner，常用的方法是 跳过NULL元素：skipNulls() / 对于NULL元素使用其他替代：useForNull(String)
     */

    private static Map<String, String> map = new HashMap<String, String>() {
        {
            put("i love u", "u love me");
            put("i like u", "u like me");
            put("i hate u", "u hate me");
        }
    };

    public static void main(String[] args) {
       /*
         on:制定拼接符号，如：test1-test2-test3 中的 “-“ 符号
         skipNulls()：忽略NULL,返回一个新的Joiner实例
         useForNull(“Hello”)：NULL的地方都用字符串”Hello”来代替
        */
        StringBuilder sb = new StringBuilder();
        Joiner.on("-").skipNulls().appendTo(sb, "Hello", "guava");
        // Hello-guava
        System.out.println(sb);
        // 1,none,3
        System.out.println(Joiner.on(",").useForNull("none").join(1, null, 3));
        // 1,2,3,4,6
        System.out.println(Joiner.on(",").skipNulls().join(Arrays.asList(1, 2, 3, 4, null, 6)));
        // withKeyValueSeparator(String separator)方法，处理map时使用，接收map中key和value之间的分隔符
        // i hate u=u hate me...i like u=u like me...i love u=u love me
        System.out.println(Joiner.on("...").withKeyValueSeparator("=").join(map));
    }
}
