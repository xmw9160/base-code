package com.xmw.zookeeper.javaapi;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Date 2017/12/18.
 * Author xmw .
 */
public class CreateSessionDemo {

    private final static String CONNECT_STRING = "192.168.31.129:2181";

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("-------------");
        ZooKeeper zooKeeper = new ZooKeeper(CONNECT_STRING, 1000, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                // 如果当前的连接是成功的, 那么通过计数器去控制
                if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                    countDownLatch.countDown();
                }
                if (watchedEvent.getType() == Event.EventType.None) {
                    System.out.println("none");
                }
                System.out.println(watchedEvent.getState());
            }
        });
        countDownLatch.await();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(zooKeeper.getState());
        System.out.println("==================");
    }
}
