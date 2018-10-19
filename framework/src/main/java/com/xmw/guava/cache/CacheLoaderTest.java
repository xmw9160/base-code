package com.xmw.guava.cache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

/**
 * @author xmw.
 * @date 2018/3/31 23:04.
 */
public class CacheLoaderTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 缓存接口这里是LoadingCache, LoadingCache在缓存项不存在时可以自动加载缓存
        //CacheBuilder是构造函数私有的, 只能通过其静态方法newBuilder()来获取CacheBuilde实例
        LoadingCache<Integer, String> strCache = CacheBuilder.newBuilder()
                // 设置并发级别为8, 并发级别是指可以同时写入缓存的线程数
                .concurrencyLevel(8)
                // 设置写缓存8秒后过期
                .expireAfterWrite(8, TimeUnit.SECONDS)
                // 设置缓存的容量为10
                .initialCapacity(10)
                // 设置缓存最大容量为100, 超过100后就会按照LRU最近最少只用算法来移除缓存
                .maximumSize(100)
                // 设置要统计缓存的命中率
                .recordStats()
                // 设置缓存移除通知
                .removalListener(new RemovalListener<Integer, String>() {
                    @Override
                    public void onRemoval(RemovalNotification<Integer, String> notification) {
                        System.out.println(notification.getKey() + "key被移除了...");
                    }
                }).build(
                        // build方法可以指定CacheLoader, 在缓存不存在时, 通过CacheLoader的实现自动加载缓存
                        new CacheLoader<Integer, String>() {
                            @Override
                            public String load(Integer key) {
                                System.out.println("load data: " + key);
                                // 从数据库获取value
                                String str = key + " : cache-value";
                                return str;
                            }
                        });

        for (int i = 0; i < 20; i++) {
            // 从缓存中获取数据
            String str = strCache.get(1);
            System.out.println(str);
            TimeUnit.SECONDS.sleep(1);
        }

        System.out.println("cache status: ");
        System.out.println(strCache.stats().toString());
    }
}
