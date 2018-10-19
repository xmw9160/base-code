package com.xmw.redis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import redis.clients.jedis.Jedis;

/**
 * 缓存雪崩以后, 使用互斥锁进行缓存重建
 * 作为一个并发量较大的应用，在使用缓存时有三个目标：
 * 第一，加快用户访问速度，提高用户体验。
 * 第二，降低后端负载，减少潜在的风险，保证系统平稳。
 * 第三，保证数据“尽可能”及时更新
 *
 * @author mingwei.xia
 * @date 2018/3/15 19:28
 * @since V1.0
 */
public class CacheCrush {

    private static Jedis jedis = RedisUtil.getJedis();

    /**
     * 热点 key 失效后大量线程重建缓存
     * 要解决这个问题也不是很复杂，但是不能为了解决这个问题给系统带来更多的麻烦，所以需要制定如下目标：
     * 减少重建缓存的次数
     * 数据尽可能一致
     * 较少的潜在危险
     */
    /**
     * 2）永远不过期
     * “永远不过期”包含两层意思：
     * 1-从缓存层面来看，确实没有设置过期时间，所以不会出现热点 key 过期后产生的问题，也就是“物理”不过期。
     * 2-从功能层面来看，为每个 value 设置一个逻辑过期时间，当发现超过逻辑过期时间后，会使用单独的线程去构建缓存。
     */

    private static ExecutorService executor = Executors.newFixedThreadPool(20);

    /**
     * 1-互斥锁 (mutex key)
     * 存在问题: 如果构建缓存过程出现问题或者时间较长，可能会存在死锁和线程池阻塞的风险，
     * 但是这种方法能够较好的降低后端存储负载并在一致性上做的比较好。
     * <p>
     * 此方法只允许一个线程重建缓存，其他线程等待重建缓存的线程执行完，重新从缓存获取数据即可
     */
    public static String getDataInfo(String userId) {
        // 从Redis中获取数据
        String value = jedis.get(userId);
        // 如果value为null, 则重新开始构建缓存
        if (StringUtils.isBlank(value)) {
            // 只允许一个线程重构缓存, 使用nx(只在键不存在时，才对键进行设置操作), 并设置过期时间ex(设置键的过期时间为 second 秒)
            String mutexKey = "mutex:key:" + userId;
            String result = jedis.set(mutexKey, "1", "nx", "ex", 180);
            if (StringUtils.isNotBlank(result) && "ok".equalsIgnoreCase(result)) {
                // 从数据库读取数据
                value = "this is data";
                // 回写redis, 并设置过期时间(时间可以设置随机值, 防止瞬时压力, 雪崩)
                jedis.setex(userId, 180, value);
                // 删除mutex_key
                jedis.del(mutexKey);
            } else {
                // 其他线程休息50毫秒后重试
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    getDataInfo(userId);
                }
            }
        }
        return value;
    }

    /**
     * " 永远不过期 " 策略
     * 问题: 这种方案由于没有设置真正的过期时间，
     * 实际上已经不存在热点 key 产生的一系列危害，但是会存在数据不一致的情况，同时代码复杂度会增大。
     * <p>
     * 从实战看，此方法有效杜绝了热点key产生的问题，但不足的就是重构缓存期间，
     * 会出现数据不一致的情况，这取决于应用方是否容忍这种不一致。下面代码使用 Redis 进行模拟：
     */
    public String getForeverData(final String key) {
        // 从缓冲中获取数据
        Value v = new Value();
        String value = v.getValue();
        // 逻辑过期时间
        long logicTimeOut = v.getLogicTimeOut();
        // 如果逻辑过期时间小于当前时间, 开始后台构建
        if (logicTimeOut <= System.currentTimeMillis()) {
            final String mutexKey = "mutex:key:" + key;
            String result = jedis.set(mutexKey, "1", "nx", "ex", 180);
            if (StringUtils.isNotBlank(result) && "ok".equalsIgnoreCase(result)) {
                // 重构缓存
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        String dbValue = "database value";
                        jedis.set(key, new Value(dbValue, System.currentTimeMillis() + 3600).toString());
                        jedis.del(mutexKey);
                    }
                });
            }
        }
        return value;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Value {
        private String value;
        private long logicTimeOut;
    }
}