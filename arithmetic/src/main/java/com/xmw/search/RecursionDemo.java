package com.xmw.search;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 递归测试
 *
 * @author xmw.
 * @date 2018/3/20 21:22.
 */
public class RecursionDemo {

    /**
     * 求num的阶乘
     */
    public static int jieCheng(int num) {
        if (num == 1) {
            return 1;
        }
        return num * jieCheng(num - 1);
    }

    /**
     * 求array中的元素和
     */
    private static int sum(int[] array) {
        if (array.length == 1 || array.length == 0) {
            return array[0];
        }
        return array[0] + sum(ArrayUtils.remove(array, 0));
    }

    /**
     * 求array中的个数
     */
    private static int count(int[] array) {
        if (array.length == 1 || array.length == 0) {
            return 1;
        }
        return 1 + count(ArrayUtils.remove(array, 0));
    }

    public static void main(String[] args) {
//        System.out.println(jieCheng(5));

        System.out.println(count(new int[]{1, 2, 3, 4, 5, 6}));
    }
}
