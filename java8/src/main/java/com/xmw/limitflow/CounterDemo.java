package com.xmw.limitflow;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Date 2018/2/3.
 * Author xmw .
 */
public class CounterDemo {
    /*
     * 计数器是一种比较简单的限流算法，用途比较广泛，在接口层面，
     * 很多地方使用这种方式限流。在一段时间内，进行计数，与阀值进行比较，到了时间临界点，将计数器清0。
     */
    /*
    这里需要注意的是，存在一个时间临界点的问题。举个栗子，在12:01:00到12:01:58这段时间内没有用户请求，
    然后在12:01:59这一瞬时发出100个请求，OK，然后在12:02:00这一瞬时又发出了100个请求。这里你应该能感受到，
    在这个临界点可能会承受恶意用户的大量请求，甚至超出系统预期的承受。
     */

    private static long timeStamp = System.currentTimeMillis();
    // 限制为1s内限制在100请求
    private static long limitCount = 100;
    private static long interval = 1000;
    // 请求数
    private static long reqCount = 0;

    public static boolean grant() {
        long now = System.currentTimeMillis();
        if (now < timeStamp + interval) {
            if (reqCount < limitCount) {
                ++reqCount;
                return true;
            } else {
                return false;
            }
        } else {
            timeStamp = System.currentTimeMillis();
            reqCount = 0;
            return false;
        }
    }

    public static void main(String[] args) {
        AtomicInteger req = new AtomicInteger(0);
        AtomicInteger refuse = new AtomicInteger(0);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 500; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (grant()) {
                        req.incrementAndGet();
//                        System.out.println("执行业务逻辑");
                    } else {
                        refuse.incrementAndGet();
//                        System.out.println("限流");
                    }
                }
            }).start();
        }
        System.out.println("程序执行时间: " + (System.currentTimeMillis() - startTime));
        System.out.println("请求成功次数: " + req);
        System.out.println("请求失败次数: " + refuse);
    }

}
