package com.xmw.limitflow;

/**
 * Date 2018/2/3.
 * Author xmw .
 */
public class LeakyBucketDemo {

    /*
     * 虽然滑动窗口有效避免了时间临界点的问题，但是依然有时间片的概念，
     * 而漏桶算法在这方面比滑动窗口而言，更加先进。
     * 有一个固定的桶，进水的速率是不确定的，但是出水的速率是恒定的，当水满的时候是会溢出的。
     */

    // 时间刻度
    private static long time = System.currentTimeMillis();
    // 桶里面现在的水
    private static int water = 0;
    // 桶的大小
    private static int size = 10;
    // 出水速率
    private static int rate = 3;

    private static boolean grant() {
        // 计算出水的数量
        long now = System.currentTimeMillis();
        int out = (int) ((now - time) / 700 * rate);
        // 漏水后的剩余
        water = Math.max(0, water - out);
        time = now;
        if ((water + 1) < size) {
            ++water;
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 500; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (grant()) {
                        System.out.println("执行业务逻辑");
                    } else {
                        System.out.println("限流");
                    }
                }
            }).start();
        }
    }

}
