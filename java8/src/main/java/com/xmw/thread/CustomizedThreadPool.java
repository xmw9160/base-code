package com.xmw.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xmw.
 * @date 2019/1/20 22:06.
 */
public class CustomizedThreadPool {
    private BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(100);

    private static AtomicInteger queueSize = new AtomicInteger(0);

    private final static int MAX_WORKER = 5;

    private int poolSize;

    public void addWorker(Runnable task) {
        workQueue.offer(task);
        if (poolSize >= MAX_WORKER) {
            return;
        }
        Worker worker = new Worker();
        worker.thread.start();
        poolSize++;
    }

    public void runWorker(Worker worker) {
        try {
            Thread ct = Thread.currentThread();
            System.out.println("thread name is : " + ct.getName());
            while (true) {
                // 阻塞(重用)
                Runnable task = workQueue.take();
                if (task != null) {
                    task.run();
                }
            }
        } catch (InterruptedException e) {
            System.err.println("任务执行失败: error >> " + e);
        }
    }

    class Worker implements Runnable {

        Thread thread;

        Worker() {
            this.thread = new Thread(this);
        }

        @Override
        public void run() {
            runWorker(this);
        }
    }

    public static void main(String[] args) {
        CustomizedThreadPool threadPool = new CustomizedThreadPool();
        for (int i = 0; i < 100; i++) {
            threadPool.addWorker(() -> {
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(1));
                } catch (InterruptedException e) {
                    System.err.println("error : " + e);
                }
                System.out.println(Thread.currentThread().getName() + " >>>>> 搞得什么玩意? " + queueSize.incrementAndGet());
            });
        }
    }
}
