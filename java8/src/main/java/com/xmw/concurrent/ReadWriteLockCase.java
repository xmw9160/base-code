package com.xmw.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Date 2018/2/25.
 * Author xmw .
 */
public class ReadWriteLockCase {
    public static void main(String[] args) {
        int[] array = new int[1];
        ReadWriteLock lock = new ReentrantReadWriteLock();
        // 线程1
        new Thread(() -> {
            Lock read = lock.readLock();
            read.lock();
            System.out.println(array[0]);
            try {
                Thread.sleep(100);
                System.out.println("wake up");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Lock write = lock.writeLock();
            write.lock();
            System.out.println("1st writing locked");
            array[0] = 1;
            write.unlock();
            read.unlock();
        }).start();

        System.out.println("second thread");

        // 线程2
//        new Thread(() -> {
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("2nd try writing lock");
//            Lock write = lock.writeLock();
//            write.lock();
//            System.out.println("2nd writing locked");
//            array[0] = 2;
//            write.unlock();
//        }).start();
    }
}
