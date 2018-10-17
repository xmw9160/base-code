package com.xmw.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Date 2018/1/23.
 * Author xmw .
 */
public class ExecutorCase {

    // 初始化一个包含10个线程的线程池executor；
    private static Executor executor1 = Executors.newFixedThreadPool(10);


    /**
     * corePoolSize: 线程池中的核心线程数，当提交一个任务时，线程池创建一个新线程执行任务，
     * 直到当前线程数等于corePoolSize；如果当前线程数为corePoolSize，
     * 继续提交的任务被保存到阻塞队列中，等待被执行；如果执行了线程池的prestartAllCoreThreads()方法，
     * 线程池会提前创建并启动所有核心线程。
     * <p>
     * maximumPoolSize: 线程池中允许的最大线程数。如果当前阻塞队列满了，
     * 且继续提交任务，则创建新的线程执行任务，前提是当前线程数小于maximumPoolSize；
     * <p>
     * keepAliveTime: 线程空闲时的存活时间，即当线程没有任务执行时，继续存活的时间；
     * 默认情况下，该参数只在线程数大于corePoolSize时才有用；
     * <p>
     * unit: keepAliveTime 的单位
     * <p>
     * <p>
     * workQueue: 用来保存等待被执行的任务的阻塞队列，且任务必须实现Runable接口，在JDK中提供了如下阻塞队列：
     * 1、ArrayBlockingQueue：基于数组结构的有界阻塞队列，按FIFO排序任务；
     * 2、LinkedBlockingQuene：基于链表结构的阻塞队列，按FIFO排序任务，吞吐量通常要高于ArrayBlockingQueue；
     * 3、SynchronousQuene：一个不存储元素的阻塞队列，每个插入操作必须等到另一个线程调用移除操作，
     * 否则插入操作一直处于阻塞状态，吞吐量通常要高于LinkedBlockingQuene；
     * 4、priorityBlockingQuene：具有优先级的无界阻塞队列；
     * <p>
     * threadFactory:  创建线程的工厂，通过自定义的线程工厂可以给每个新建的线程设置一个具有识别度的线程名。
     * <p>
     * handler: 线程池的饱和策略，当阻塞队列满了，且没有空闲的工作线程，如果继续提交任务，必须采取一种策略处理该任务，线程池提供了4种策略：
     * 1、AbortPolicy：直接抛出异常，默认策略；
     * 2、CallerRunsPolicy：用调用者所在的线程来执行任务；
     * 3、DiscardOldestPolicy：丢弃阻塞队列中靠最前的任务，并执行当前任务；
     * 4、DiscardPolicy：直接丢弃任务；
     * 当然也可以根据应用场景实现RejectedExecutionHandler接口，自定义饱和策略，如记录日志或持久化存储不能处理的任务。
     */
    private static Executor executor = new ThreadPoolExecutor(10,
            10,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

    private static Executor schedulExcutor = Executors.newScheduledThreadPool(2);

    private static ExecutorService executorService = Executors.newScheduledThreadPool(5);


    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        Future<?> submit = executorService.submit(new Task());
        submit.get(4, TimeUnit.SECONDS);
//        schedulExcutor.execute(new Task());
        for (int i = 0; i < 20; i++) {
            // 通过executor.execute方法提交20个任务，每个任务打印当前的线程名；
            // 负责执行任务的线程的生命周期都由Executor框架进行管理；
            executor.execute(new Task(i));
//            executor.execute(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(Thread.currentThread().getName());
//                }
//            });
        }

        int COUNT_BITS = Integer.SIZE - 3;
        int CAPACITY = (1 << COUNT_BITS) - 1;

        // runState is stored in the high-order bits
        int RUNNING = -1 << COUNT_BITS;
        int SHUTDOWN = 0 << COUNT_BITS;
        int STOP = 1 << COUNT_BITS;
        int TIDYING = 2 << COUNT_BITS;
        int TERMINATED = 3 << COUNT_BITS;

        System.out.println("COUNT_BITS :" + COUNT_BITS);
        System.out.println("COUNT_BITS :" + Integer.toBinaryString(COUNT_BITS));
        System.out.println("CAPACITY :" + CAPACITY);
        System.out.println("CAPACITY :" + Integer.toBinaryString(CAPACITY));
        System.out.println("RUNNING :" + RUNNING);
        System.out.println("RUNNING :" + Integer.toBinaryString(RUNNING));
        System.out.println("SHUTDOWN :" + SHUTDOWN);
        System.out.println("SHUTDOWN :" + Integer.toBinaryString(SHUTDOWN));
        System.out.println("STOP :" + STOP);
        System.out.println("STOP :" + Integer.toBinaryString(STOP));
        System.out.println("TIDYING :" + TIDYING);
        System.out.println("TIDYING :" + Integer.toBinaryString(TIDYING));
        System.out.println("TERMINATED :" + TERMINATED);
        System.out.println("TERMINATED :" + Integer.toBinaryString(TERMINATED));

    }

    static class Task implements Runnable {

        private int i;

        public Task() {
        }

        public Task(int i) {
            this.i = i;
        }

        @Override
        public void run() {
//            try {
//                TimeUnit.SECONDS.sleep(4);
//            } catch (Exception e) {
//                System.out.println(e);
//            }

            System.out.println(Thread.currentThread().getName() + "-->" + i);
        }
    }
}
