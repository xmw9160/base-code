package com.xmw.zookeeper.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

/**
 * Date 2017/12/18.
 * Author xmw .
 */
public class ZkClientApiDemo {

    private final static String CONNECT_STRING = "192.168.31.133:2181,192.168.31.134:2181,192.168.31.135:2181";

    private static ZkClient getInstance() {
        return new ZkClient(CONNECT_STRING, 5000);
    }

    public static void main(String[] args) {
        ZkClient zkClient = getInstance();
//        zkClient.createEphemeral("/xmw12", "123".getBytes());
//        System.out.println("zkClient success.");

        // 递归创建
        zkClient.createPersistent("/xmw3/xmw4/xmw5", true);
        // 递归删除
//        String path = "/xmw3";
//        zkClient.deleteRecursive(path);

        // 获取子节点
//        List<String> children = zkClient.getChildren("/xmw3");
//        System.out.println(children);

        // watcher
        zkClient.subscribeDataChanges("/xmw", new IZkDataListener() {
            public void handleDataChange(String s, Object o) {
                System.out.println("数据被修改了, 节点路径: " + s + "节点修改后的值: " + o);
            }

            public void handleDataDeleted(String s) {
                System.out.println("数据被删除了");
            }
        });

        // 修改数据
//        zkClient.writeData("/xmw", "node");
//        TimeUnit.SECONDS.sleep(2);
    }
}
