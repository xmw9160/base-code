package com.xmw.httpclient;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.google.common.base.Stopwatch;

/**
 * @author mingwei.xia
 * @date 2018/7/2 9:13
 * @since V1.0
 */
public class HttpClientTest {

    /**
     * @Controller public class DemoController {
     * @GetMapping(name="/helloworld",produces = "application/json")
     * @ResponseBody public Mono<String> get(){
     * return Mono.<String>defer(()->Mono.just("helloworld"));
     * }
     * }
     */

    private static void test(int threads) throws InterruptedException {
//        int loop = 10_0000;
        int loop = 10_0;
        ExecutorService es = Executors.newFixedThreadPool(threads);
        CountDownLatch latch = new CountDownLatch(threads);
        AtomicLong consumed = new AtomicLong(0);
        for (int i = 0; i < threads; i++) {
            es.submit(() -> {
                String result = null;
                Stopwatch watch = Stopwatch.createStarted();
                int count = loop / threads;
                for (int c = 0; c < count; c++) {
                    result = HttpClientUtil.httpGetRequest("http://localhost:8080/helloworld");
                }
                watch.stop();
                consumed.addAndGet(watch.elapsed(TimeUnit.SECONDS));
                latch.countDown();
                System.out.println(result);
            });
        }
        latch.await();
        System.out.println("threads:" + threads);
        System.out.println("total:" + consumed.get());
        System.out.println("average:" + (loop / consumed.get()));
        es.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        test(1);
        test(1);
        test(2);
        test(4);
        test(8);
        test(16);
    }
}