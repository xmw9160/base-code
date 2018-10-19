package com.xmw.concurrent.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * TODO 功能描述
 *
 * @author mingwei.xia
 * @date 2018/3/9 11:52
 * @since V1.0
 */
public class CyclicBarrierTest_1 {

    private static CyclicBarrier barrier;

    public static void main(String[] args) {
        barrier = new CyclicBarrier(5);
        for (int i = 1; i <= 5; i++) {
            new threadTest1().start();
        }
    }

    static class threadTest1 extends Thread {
        public void run() {
            System.out.println(Thread.currentThread().getName() + "达到...");
            try {
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行完成...");
        }
    }
}
