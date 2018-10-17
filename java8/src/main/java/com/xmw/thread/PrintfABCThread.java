package com.xmw.thread;

import java.util.concurrent.locks.Lock;

/**
 * Date 2018/2/1.
 * Author xmw .
 */
public class PrintfABCThread implements Runnable {
    public static final int MAX = 30;
    public static int count = 0;
    private String name;
    private Lock lock;
    private Integer flag;

    public PrintfABCThread(String name, Lock lock, Integer flag) {
        this.name = name;
        this.lock = lock;
        this.flag = flag;
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();

            if (count >= MAX) {
                lock.unlock();
                return;
            }

            if (count % 3 == flag) {
                System.out.println(name);
                count++;
            }
            lock.unlock();
        }
    }
}
