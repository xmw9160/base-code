package com.xmw.sort;

import java.util.Arrays;

/**
 * 选择排序
 *
 * @author xmw.
 * @date 2018/3/20 20:29.
 */
public class SelectSort {

    public static void choiceSort(Integer[] a) {
        if (a == null || a.length <= 0) {
            return;
        }
        for (int i = 0; i < a.length; i++) {
            // 将当前下标定义为最小值下标
            int min = i;

            for (int j = i + 1; j < a.length; j++) {
                // 如果有小于当前最小值的关键字
                if (a[min] > a[j]) {
                    // 将此关键字的下标赋值给min
                    min = j;
                }
            }

            // 若min不等于i，说明找到最小值，交换
            if (i != min) {
                int tmp = a[min];
                a[min] = a[i];
                a[i] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{1, 3, 5, 8, 4, 2, 0};
        choiceSort(array);
        System.out.println(Arrays.toString(array));
    }
}
