package com.xmw.concurrent.phaser;

import java.util.concurrent.Phaser;

/**
 * 在Phaser中，它把多个线程协作执行的任务划分为多个阶段，
 * 编程时需要明确各个阶段的任务，每个阶段都可以有任意个参与者，线程都可以随时注册并参与到某个阶段。
 *
 * @author mingwei.xia
 * @date 2018/3/12 14:49
 * @since V1.0
 */
public class PhaserTest_1 {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(5);

        for (int i = 0; i < 5; i++) {
            Task_01 task_01 = new Task_01(phaser);
            Thread thread = new Thread(task_01, "PhaseTest_" + i);
            thread.start();
        }
    }

    static class Task_01 implements Runnable {
        private final Phaser phaser;

        Task_01(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "执行任务完成，等待其他任务执行......");
            //等待其他任务执行完成
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName() + "继续执行任务...");
        }
    }
}
