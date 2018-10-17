package com.xmw.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Date 2017/12/18.
 * Author xmw .
 */
public class CuratorCreateSessionDemo {

    private final static String CONNECT_STRING = "192.168.31.129:2181";

    public static void main(String[] args) {
        // 创建会话的两个方式
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(CONNECT_STRING, 5000, 5000, new ExponentialBackoffRetry(1000, 3));
        // start启动一个连接
        curatorFramework.start();
        System.out.println("curatorFramework: " + curatorFramework.getState());
        // fluent 风格
        CuratorFramework curatorFramework1 = CuratorFrameworkFactory.builder()
                .connectString(CONNECT_STRING)
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .namespace("curator")
                .build();
        curatorFramework1.start();
        System.out.println("curatorFramework1: " + curatorFramework1.getState());

    }


}
