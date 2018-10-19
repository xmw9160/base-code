package com.xmw.guava.collection;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;

/**
 * Date 2018/1/28.
 * Author xmw .
 */
public class ImmutableDemo {

    public static void main(String[] args) {
        ImmutableSet<String> set = ImmutableSet.of("a", "b", "c", "d", "d", "d");
        ImmutableSet<String> set1 = ImmutableSet.copyOf(set);
        ImmutableSet<String> set2 = ImmutableSet.<String>builder().addAll(set).add("e").build();
        System.out.println(set);
        ImmutableList<String> list = set.asList();
        for (String s : list) {
            System.out.println(s);
        }
        UnmodifiableIterator<String> iterator = set1.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
