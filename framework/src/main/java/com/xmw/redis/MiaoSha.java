package com.xmw.redis;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import redis.clients.jedis.Jedis;

/**
 * 使用redis秒杀demo
 *
 * @author mingwei.xia
 * @date 2018/3/15 15:58
 * @since V1.0
 */
public class MiaoSha {

    public static void main(String[] args) {
        final String watchKeys = "watchkeys";
        final Jedis jedis = RedisUtil.getJedis();
        final AtomicInteger successNum = new AtomicInteger(0);
        final AtomicInteger failNum = new AtomicInteger(0);
        jedis.set(watchKeys, "100"); //设置起始的抢购数
        jedis.close();

        ExecutorService executor = Executors.newFixedThreadPool(20);  //20个线程池并发数
        for (int i = 0; i < 1000; i++) {//设置1000个人来发起抢购
            executor.execute(new MiaoShaRunnable(getRandomString(6), successNum, failNum));
        }
        executor.shutdown();
    }


    private static String getRandomString(int length) { //length是随机字符串长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
