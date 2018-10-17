package com.xmw.javaapi;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * Date 2017/12/18.
 * Author xmw .
 */
public class ApiOperationDemo implements Watcher {

    private final static String CONNECT_STRING = "192.168.31.129:2181";

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;
    private static Stat stat = new Stat();

    public static void main(String[] args) throws Exception {
        zooKeeper = new ZooKeeper(CONNECT_STRING, 5000, new ApiOperationDemo());
        countDownLatch.await();
        System.out.println("zk连接状态： " + zooKeeper.getState());

        // 创建节点
//        String result = zooKeeper.create("/xmw", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//        System.out.println("创建成功: " + result);

        // 修改数据
//        zooKeeper.setData("/xmw", "x123".getBytes(), -1);
//        Thread.sleep(2000);

        // 修改数据
//        zooKeeper.setData("/xmw", "x567".getBytes(), -1);
//        Thread.sleep(2000);

        // 删除数据
//        zooKeeper.delete("/xmw", -1);

        // 创建节点和子节点, 临时节点下不能挂在子节点
        String path = "/node1";
//        String s = zooKeeper.create(path, "1234".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//        System.out.println("创建节点: " + s);
//        TimeUnit.SECONDS.sleep(1);

//        Stat stat = zooKeeper.exists(path + "/node1", true);
        // 节点不存在
//        if (stat == null) {
//            String childNode = zooKeeper.create(path + "/node1", "567".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//            System.out.println("子节点创建成功: " + childNode);
//            TimeUnit.SECONDS.sleep(1);
//        }

        // 修改子路径
//        zooKeeper.setData(path + "/node1", "789".getBytes(), -1);
//        TimeUnit.SECONDS.sleep(1);

        // 获取指定路径下的子路径
//        List<String> children = zooKeeper.getChildren("/node1", true);
//        System.out.println(children);
    }

    public void process(WatchedEvent watchedEvent) {
        // 如果当前的连接是成功的, 那么通过计数器去控制
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            if (watchedEvent.getType() == Event.EventType.None && watchedEvent.getPath() == null) {
                countDownLatch.countDown();
                System.out.println("zk连接成功: 连接状态->" + watchedEvent.getState() + " 事件类型--> " + watchedEvent.getType());
            } else if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
                try {
                    System.out.println("数据变更触发路径: " + watchedEvent.getPath() + " ->改变后的值: " + zooKeeper.getData(watchedEvent.getPath(), true, stat));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
                try {
                    System.out.println("子节点数据变更触发路径: " + watchedEvent.getPath() + " ->改变后的值: " + zooKeeper.getData(watchedEvent.getPath(), true, stat));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (watchedEvent.getType() == Event.EventType.NodeCreated) {
                // 创建子节点时会触发
                try {
                    System.out.println("创建节点路径: " + watchedEvent.getPath() + " ->节点的值: " + zooKeeper.getData(watchedEvent.getPath(), true, stat));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (watchedEvent.getType() == Event.EventType.NodeDeleted) {
                // 子节点删除被触发
                try {
                    System.out.println("被删除的路径: " + watchedEvent.getPath() + " ->改变后的值: " + zooKeeper.getData(watchedEvent.getPath(), true, stat));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("事件类型为: " + watchedEvent.getType());
        }
    }
}

