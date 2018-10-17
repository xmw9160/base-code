package com.xmw.javaapi;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Date 2017/12/18.
 * Author xmw .
 */
public class AuthController implements Watcher {

    private final static String CONNECT_STRING = "192.168.31.129:2181";

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static CountDownLatch countDownLatch2 = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;
    private static Stat stat = new Stat();

    public static void main(String[] args) throws Exception {
        zooKeeper = new ZooKeeper(CONNECT_STRING, 5000, new AuthController());
        countDownLatch.await();
        System.out.println("zk1: " + zooKeeper.getState());

        ACL acl = new ACL(ZooDefs.Perms.CREATE, new Id("digest", "root:root"));
        ACL acl2 = new ACL(ZooDefs.Perms.CREATE, new Id("ip", "192.168.31.130"));
        List<ACL> acls = new ArrayList<>();
        acls.add(acl);
        acls.add(acl2);
        zooKeeper.create("/auth", "789".getBytes(), acls, CreateMode.PERSISTENT);
        // acl (create /delete /admin /read / write)
        //权限模式: ip / Digest(username/password) / word / super
//        zooKeeper.addAuthInfo("digest", "root:root".getBytes());
//        zooKeeper.create("/auth", "123".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);

        TimeUnit.SECONDS.sleep(3);
        ZooKeeper zooKeeper1 = new ZooKeeper(CONNECT_STRING, 5000, watchedEvent -> {
            if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                if (watchedEvent.getType() == Event.EventType.None && watchedEvent.getPath() == null) {
                    countDownLatch2.countDown();
                    System.out.println("zk2: " + watchedEvent.getState() + "-->" + watchedEvent.getType());
                }
            }
        });
        countDownLatch2.await();
        System.out.println("zk2: " + zooKeeper1.getState());
        zooKeeper1.delete("/auth", -1);
//        zooKeeper1.setData("/auth", "789".getBytes(), -1);
    }

    public void process(WatchedEvent watchedEvent) {
        // 如果当前的连接是成功的, 那么通过计数器去控制
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            if (watchedEvent.getType() == Event.EventType.None && watchedEvent.getPath() == null) {
                countDownLatch.countDown();
                System.out.println("zk1: " + watchedEvent.getState() + "-->" + watchedEvent.getType());
            }
        }
    }
}
