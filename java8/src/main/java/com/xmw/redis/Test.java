package com.xmw.redis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import redis.clients.jedis.Jedis;

/**
 * TODO 功能描述
 *
 * @author mingwei.xia
 * @date 2018/3/15 17:32
 * @since V1.0
 */
public class Test {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(50);
//        final AtomicInteger number = new AtomicInteger(100);
//        for (int i = 0; i < 10000; i++) {
//            executorService.execute(new Runnable() {
//                @Override
//                public void run() {
//                    if (number.get() >0) {
//                        int result = number.decrementAndGet();
//                        System.out.println(result);
//                    }
//                }
//            });
//        }

        // 不使用redis的事务, 使用key的自增或自减, 并发状态下存在问题
        final Jedis jedis = RedisUtil.getJedis();
        final String key = "testNum";
        jedis.set(key, "100");
        jedis.close();
        for (int i = 0; i < 10000; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    Jedis jedis = RedisUtil.getJedis();
                    Integer temp = Integer.valueOf(jedis.get(key));
                    if (temp > 0) {
                        Long res = jedis.decr(key);
//                        jedis.set(res+"", res+"");
                        System.out.println(res);
                    }
                    jedis.close();
                }
            });
        }
    }
}
