package com.xmw.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * TODO 功能描述
 *
 * @author mingwei.xia
 * @date 2018/3/9 13:11
 * @since V1.0
 */
public class CDLTest {

    private static CountDownLatch latch = new CountDownLatch(5);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                public void run() {
                    System.out.println("进入线程: " + Thread.currentThread().getName() + ". count: " + latch.getCount());
                    System.out.println("-->>");
                    latch.countDown();
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程启动: " + Thread.currentThread().getName() + ". count: " + latch.getCount());
                    System.out.println("----<<");
                }
            }).start();
        }
        latch.await();
    }
}
