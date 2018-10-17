package com.xmw.search;

/**
 * 二分查找算法实现
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
                low = mid + 1;
            } else {
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
