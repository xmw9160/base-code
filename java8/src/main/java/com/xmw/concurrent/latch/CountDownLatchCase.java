package com.xmw.concurrent.latch;

import java.util.concurrent.CountDownLatch;

/**
 * Date 2018/1/24.
 * Author xmw .
 */
public class CountDownLatchCase {

    private static volatile Integer race = 0;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(() -> {
                race++;  // ++不是原子操作
                cdl.countDown();
                System.out.println(Thread.currentThread().getName() + "-->" + race);
            });
            thread.start();
        }
        cdl.await();
        System.out.println(race);
    }
}
