package com.xmw.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 单例redis连接实例
 *
 * @author mingwei.xia
 * @date 2018/3/15 16:49
 * @since V1.0
 */
public class RedisUtil {

    private RedisUtil() {
    }

    public static Jedis getJedis() {
//        JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);
//        return jedisPool.getResource();
        return RedisInstance.REDIS_INSTANCE.getResource();
    }

    private static class RedisInstance {
        private static final JedisPool REDIS_INSTANCE = new JedisPool("127.0.0.1", 6379);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            try (Jedis jedis = getJedis()) {
                jedis.set("test-" + i, "test-" + i);
                jedis.expire("test-" + i, 60);
//                jedis.del("test-" + i);
            }
        }
        System.out.println("execute finish...");
    }
}
