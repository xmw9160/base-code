package com.xmw.throughput;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author xmw.
 * @date 2018/6/26 22:02.
 */
public class PigInThePython {
    private static final int ENOUGH_PIGS = 5000;
    private static volatile List pigs = new ArrayList();
    private static volatile int pigsEaten = 0;

    public static void main(String[] args) {
        new PigEater().start();
        new PigDigester().start();
        System.gc();
    }


    private static class PigEater extends Thread {
        @Override
        public void run() {
            while (true) {
                pigs.add(new byte[32 * 1024 * 1024]);  //32M per pig
                if (pigs.size() > ENOUGH_PIGS) {
                    return;
                }
                try {
                    // 睡100ms
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class PigDigester extends Thread {
        @Override
        public void run() {
            long start = System.currentTimeMillis();
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pigsEaten += pigs.size();
                pigs = new ArrayList();
                if (pigsEaten > ENOUGH_PIGS) {
                    long time = (System.currentTimeMillis() - start);
                    System.out.println("Digested " + pigsEaten + " pigs in " + time + " ms. %n");
                    return;
                }
            }
        }
    }

}
