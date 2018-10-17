package com.xmw.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Date 2017/12/18.
 * Author xmw .
 */
public class CuratorClientUtils {

    private final static String CONNECT_STRING = "192.168.31.129:2181";

    public static CuratorFramework getInstance() {
        // 创建会话的两个方式
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(CONNECT_STRING,
                5000,
                5000,
                new ExponentialBackoffRetry(1000, 3)); // ExponentialBackoffRetry 衰减重试
        // start启动一个连接
        curatorFramework.start();
        return curatorFramework;
    }
}
