package com.xmw.guava;

import java.util.Map;

import com.google.common.base.Splitter;

/**
 * Guava Splitter
 * 场景: 按照一定方式切割字符串
 * Date 2018/1/28.
 * Author xmw .
 */
public class SplitterDemo {

    /**
     * Splitter 能够将一个字符串按照指定的分隔符拆分成可迭代遍历的字符串集合，Iterable
     * 对于Splitter，常用的方法是： trimResults()/omitEmptyStrings()。
     * 注意拆分的方式，有字符串，还有正则，还有固定长度分割.
     */

    public static void main(String[] args) {
       /*
         on():指定分隔符来分割字符串
         limit():当分割的子字符串达到了limit个时则停止分割
         fixedLength():根据长度来拆分字符串
         trimResults():去掉子串中的空格
         omitEmptyStrings():去掉空的子串
         withKeyValueSeparator():要分割的字符串中key和value间的分隔符,分割后的子串中key和value间的分隔符默认是=
        */
        System.out.println(Splitter.on(",").limit(3).trimResults().split(" a,  b,  c,  d, e, f"));//[ a, b, c,d]
        System.out.println(Splitter.fixedLength(3).trimResults().split("1a2cfadsf 3"));//[1 2,  3]
        System.out.println(Splitter.on(" ").omitEmptyStrings().splitToList("12 34"));
        System.out.println(Splitter.on(",").omitEmptyStrings().split("1,,,,2,,,3"));//[1, 2, 3]
        System.out.println(Splitter.on(" ").trimResults().split("1 2 3")); //[1, 2, 3],默认的连接符是,
        System.out.println(Splitter.on(";").withKeyValueSeparator(":").split("a:1;b:2;c:3"));//{a=1, b=2, c=3}

        // Splitter也提供了一个内部类MapSplitter来处理字符串，返回map集合
        String mapStr = "i hate u=u hate me...i like u=u like me...i love u=u love me";
        Splitter.MapSplitter mapSplitter = Splitter.on("...").omitEmptyStrings().withKeyValueSeparator("=");
        Map<String, String> map = mapSplitter.split(mapStr);
        map.forEach((key, value) -> System.out.println("key: " + key + ", value: " + value));
    }
}
