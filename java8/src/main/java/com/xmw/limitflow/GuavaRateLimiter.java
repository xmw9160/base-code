package com.xmw.limitflow;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Date 2018/2/3.
 * Author xmw .
 */
public class GuavaRateLimiter {
    /*
     * Guava RateLimiter基于令牌桶算法，我们
     * 只需要告诉RateLimiter系统限制的QPS是多少，
     * 那么RateLimiter将以这个速度往桶里面放入令牌，
     * 然后请求的时候，通过tryAcquire()方法向RateLimiter获取许可（令牌）。
     */

    private static ConcurrentHashMap<String, RateLimiter> resourceRateLimiter =
            new ConcurrentHashMap<>();

    static {
        createResourceLimiter("order", 50);
    }

    public static void createResourceLimiter(String resource, double qps) {
        if (resourceRateLimiter.contains(resource)) {
            resourceRateLimiter.get(resource).setRate(qps);
        } else {
            RateLimiter rateLimiter = RateLimiter.create(qps);
            resourceRateLimiter.putIfAbsent(resource, rateLimiter);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (resourceRateLimiter.get("order").tryAcquire(10, TimeUnit.MILLISECONDS)) {
                        System.out.println("执行业务逻辑");
                    } else {
                        System.out.println("限流...");
                    }
                }
            }).start();
        }
    }
}
