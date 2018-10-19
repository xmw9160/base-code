package com.xmw.search;

/**
 * 二分查找算法实现
 * 前提: 原始数组有序
 *
 * @author xmw.
 * @date 2018/3/19 23:55.
 */
public class BinarySerach {

    public static int binarySerach(int[] list, int target) {
        int low = 0;
        int high = list.length - 1;
        int serachTimes = 0;
        while (low <= high) {
            ++serachTimes;
            int mid = (low + high) / 2;
            int guess = list[mid];
            if (target == guess) {
                System.out.println("查找次数为：" + serachTimes);
                return mid;
            }
            if (target > guess) {
                // 查找数大于中间数,索引需要向后移动
                low = mid + 1;
            } else {
                // 查找数小于中间数,索引需要向前移动
                high = mid - 1;
            }
        }
        System.out.println("查找次数为：" + serachTimes);
        return -1;
    }

    public static void main(String[] args) {
        int[] list = new int[]{1, 3, 4, 5, 7, 9, 13, 17, 19};
        int index = binarySerach(list, 4);
        System.out.println("查找的值的索引为： " + index);
    }
}
