package com.xmw.guava.collection2;

import java.util.HashMap;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

/**
 * Immutable Collections Test
 * https://my.oschina.net/realfighter/blog/349912
 *
 * @author mingwei.xia
 * @date 2018/10/30 13:52
 * @since V1.0
 */
public class ImmutableCollectionsTest {
    public static void main(String[] args) {
        //builder模式创建不可变Set集合
        ImmutableSet<String> immutableSetByBuilder = ImmutableSet
                .<String>builder().add("First")
                .addAll(Lists.asList("Second", new String[]{"Third"}))
                .build();
        //of方法创建不可变Set集合
        ImmutableSet<String> immutableSetByOf = ImmutableSet
                .of("First", "Second", "Third");
        //copyOf方法创建不可变Set集合
        ImmutableSet<String> immutableSetByCopyOf = ImmutableSet
                .copyOf(new String[]{"First", "Second", "Third"});

        //builder模式创建不可变List集合
        ImmutableList<String> immutableListByBuilder = ImmutableList
                .<String>builder().add("First")
                .addAll(Lists.asList("Second", new String[]{"Third"}))
                .build();
        //of方法创建不可变List集合
        ImmutableList<String> immutableListByOf = ImmutableList
                .of("First", "Second", "Third");
        //copyOf方法创建不可变List集合
        ImmutableList<String> immutableListByCopyOf = ImmutableList
                .copyOf(new String[]{"First", "Second", "Third"});

        //builder模式创建不可变Map集合
        ImmutableMap<String, String> immutableMapByBuilder = ImmutableMap
                .<String, String>builder().put("First", "1")
                .putAll(new HashMap<String, String>() {
                    {
                        put("Second", "2");
                        put("Third", "3");
                    }
                }).build();
        //of方法创建不可变Map集合
        ImmutableMap<String, String> immutableMapByOf = ImmutableMap
                .of("First", "1", "Second", "2", "Third", "3");
        System.out.println(immutableMapByOf);
        //copyOf方法创建不可变Map集合
        ImmutableMap<String, String> immutableMapByCopyOf = ImmutableMap
                .copyOf(new HashMap<String, String>() {
                    {
                        put("First", "1");
                        put("Second", "2");
                        put("Third", "3");
                    }
                });

        /**
         * Maps.uniqueIndex(Iterable,Function)通常针对的场景是：
         * 有一组对象，它们在某个属性上分别有独一无二的值，而我们希望能够按照这个属性值查找对象
         * 译者注：这个方法返回一个Map，
         * 键为Function返回的属性值，值为Iterable中相应的元素，因此我们可以反复用这个Map进行查找操作。
         */
//        Maps.uniqueIndex();
    }
}
