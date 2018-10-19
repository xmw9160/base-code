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
        JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);
        return jedisPool.getResource();
    }

    private static class RedisInstance {
        private static final Jedis REDIS_INSTANCE = new Jedis("127.0.0.1", 6379);
    }
}
