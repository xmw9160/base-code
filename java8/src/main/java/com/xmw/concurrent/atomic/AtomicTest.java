package com.xmw.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Date 2018/1/23.
 * Author xmw .
 */
public class AtomicTest {

    private static AtomicInteger race = new AtomicInteger(0);

    private static void increase() {
        race.incrementAndGet();
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[20];
        for (Thread thread : threads) {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("11111");
                    for (int i = 0; i < 10000; i++) {
                        increase();
                    }
                }
            });
            thread.start();
        }
        System.out.println(race);
        while (Thread.activeCount() > 1) {
            /**
             *
             * Yield 的中文翻译为 “让步，让位”，这里意思是当前线程主动让出时间片，并让操作系统调度其它就绪态的线程使用时间片。
             如果调用 Yield，只是把当前线程放入到就绪队列中，而不是阻塞队列
             如果没有找到其它就绪态的线程，则当前线程继续运行
             比 Thread.Sleep(0) 速度要快，可以让低于当前优先级的线程得以运行
             */
            Thread.yield();
        }

        System.out.println(race);
    }
}
