package com.xmw.java8.java8.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author mingwei.xia
 * @date 2018/7/4 19:37
 * @since V1.0
 */
public class FutureTest {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Double> future = executor.submit(() -> 0.1D);
        doSomethingElse();
        try {
            Double result = future.get(1, TimeUnit.SECONDS);
        } catch (ExecutionException | InterruptedException | TimeoutException ee) {
            // 计算抛出一个异常
        }
    }

    private static void doSomethingElse() {
    }

    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            double price = calculatePrice(product);
            futurePrice.complete(price);
        }).start();
        return futurePrice;
    }

    private double calculatePrice(String product) {
        return 0.3D;
    }
}
