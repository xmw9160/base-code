package com.xmw.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Date 2018/1/23.
 * Author xmw .
 */
public class ExecutorCase2 {

    private static ExecutorService executor = Executors.newFixedThreadPool(100);

    public static void main(String[] args) {

        Future<String> future = executor.submit(new Task());
        System.out.println("do other things...");
        try {
            String result = future.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static class Task implements Callable<String> {
        @Override
        public String call() {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "this is future case2";
        }
    }
}
