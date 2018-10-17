package com.xmw;

import java.util.HashMap;

/**
 * @author xmw.
 * @date 2018/6/26 23:39.
 */
public class StopWorldDemo {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        PrintThread printThread = new PrintThread();
        myThread.start();
        printThread.start();
    }

    public static class MyThread extends Thread {
        HashMap<Long, byte[]> map = new HashMap<>();

        @Override
        public void run() {
            try {
                while (true) {
                    if (map.size() * 512 / 1024 / 1024 > 400) {
                        map.clear(); //防止内存溢出
                        System.out.println("clear map");
                    }
                    byte[] b1;
                    for (int i = 0; i < 100; i++) {
                        b1 = new byte[512]; //模拟内存占用
                        map.put(System.nanoTime(), b1);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class PrintThread extends Thread {
        public final long startTime = System.currentTimeMillis();

        @Override
        public void run() {
            try {
                while (true) {
                    // 每毫秒打印时间信息
                    long t = System.currentTimeMillis() - startTime;
                    System.out.println(t / 1000 + "." + t % 1000);
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
