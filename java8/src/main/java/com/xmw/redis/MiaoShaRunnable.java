package com.xmw.redis;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * 抢购逻辑
 *
 * @author mingwei.xia
 * @date 2018/3/15 16:16
 * @since V1.0
 */
public class MiaoShaRunnable implements Runnable {

    private String watchKeys = "watchkeys";// 监视keys
    private Jedis jedis = RedisUtil.getJedis();
    private String userInfo;
    private AtomicInteger successNum;
    private AtomicInteger failNum;

    public MiaoShaRunnable() {
    }

    public MiaoShaRunnable(String uinfo, AtomicInteger successNum, AtomicInteger failNum) {
        this.userInfo = uinfo;
        this.successNum = successNum;
        this.failNum = failNum;
    }

    @Override
    public void run() {
        try {
            jedis.watch(watchKeys); // watchKeys

            String val = jedis.get(watchKeys);
            int number = Integer.valueOf(val);

            if (number <= 100 && number >= 1) {

                Transaction tx = jedis.multi(); // 开启事务
                // tx.incr("watchkeys");
                tx.incrBy(watchKeys, -1); // 扣除秒杀数量

                List<Object> list = tx.exec();// 提交事务，如果此时watchKeys被改动了，则返回null

                if (list == null || list.size() == 0) {
                    // 抢购失败
                    String failuserifo = "fail-" + userInfo + failNum.getAndIncrement();
                    String failinfo = "用户-" + userInfo + "-商品争抢失败，抢购失败";
                    System.out.println(failinfo);
                    /* 抢购失败业务逻辑 */
//                    jedis.setnx(failuserifo, failinfo);
                } else {
                    // 抢购成功
                    for (Object succ : list) {
                        String succuserifo = "succ-" + succ.toString() + userInfo + "-当前成功人数-" + successNum.getAndIncrement();
                        String succinfo = "用户-" + succuserifo + "-抢购成功，当前抢购成功人数:"
                                + (1 - (number - 100));
                        System.out.println(succinfo);
                        /* 抢购成功业务逻辑 */
                        jedis.setnx(succuserifo, succinfo);
                    }
                }
            } else {
                String failuserifo = "kcfail-" + userInfo;
                String failinfo1 = "用户：" + failuserifo + "-商品被抢购完毕，抢购失败" + failNum.getAndIncrement();
                System.out.println(failinfo1);
//                jedis.setnx(failuserifo, failinfo1);
                // Thread.sleep(500);
                // return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }
}
