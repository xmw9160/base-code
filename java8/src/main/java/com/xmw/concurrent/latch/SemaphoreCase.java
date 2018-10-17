package com.xmw.concurrent.latch;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Date 2018/1/24.
 * Author xmw .
 */
public class SemaphoreCase {

    private static final int COUNT = 40;
    private static Executor executor = Executors.newFixedThreadPool(COUNT);
    private static Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < COUNT; i++) {
            executor.execute(new Task());
        }
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            try {
                // 读取文件操作
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName());
                // 存数据过程
                semaphore.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
