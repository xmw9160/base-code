package com.xmw;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * https://mp.weixin.qq.com/s/RTOhU-20cpyVvW-IcnPdFw
 *
 * @author mingwei.xia
 * @date 2018/11/27 9:05
 * @since V1.0
 */
public class AddSortedList {

    private static List<Integer> sortedList = Lists.newLinkedList();

    public static void main(String[] args) {
//        add1(1);
//        add1(5);
//        add1(3);
//        add1(8);
//        add1(2);

//        add2(1);
//        add2(5);
//        add2(3);
//        add2(8);
//        add2(2);

        sortedList.add(1);
        sortedList.add(5);
        sortedList.add(3);
        sortedList.add(8);
        sortedList.add(2);

//        Collections.sort(sortedList);

        // jdk8 List 提供了默认排序接口实现 -> Arrays.sort()
        sortedList.sort(Integer::compareTo);
        System.out.println(sortedList);

        Integer[] array = new Integer[]{1, 5, 7, 2, 9};
        Arrays.sort(array);
        Arrays.stream(array).forEach(System.out::print);
    }

    private static void add1(int e) {
        int i;
        for (i = 0; i < sortedList.size(); i++) {
            if (e <= sortedList.get(i)) {
                break;
            }
        }
        sortedList.add(i, e);
    }

    private static void add2(int e) {
        int i = 0;
        for (Integer j : sortedList) {
            if (e <= j) {
                break;
            }
            i++;
        }
        sortedList.add(i, e);
    }
}
