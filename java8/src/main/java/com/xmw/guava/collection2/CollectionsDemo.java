package com.xmw.guava.collection2;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Date 2018/1/28.
 * Author xmw .
 */
public class CollectionsDemo {

    public static void main(String[] args) {
        Set<Integer> set1 = Sets.newHashSet(1, 2, 3, 4, 5);
        Set<Integer> set2 = Sets.newHashSet(3, 4, 5, 6);
        Sets.SetView<Integer> inter = Sets.intersection(set1, set2); //交集
        System.out.println(inter);
        Sets.SetView<Integer> diff = Sets.difference(set1, set2); //差集,在A中不在B中
        System.out.println(diff);
        Sets.SetView<Integer> union = Sets.union(set1, set2); //并集
        System.out.println(union);
    }
}
