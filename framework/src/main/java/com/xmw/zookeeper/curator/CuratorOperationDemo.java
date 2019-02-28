package com.xmw.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Date 2017/12/18.
 * Author xmw .
 */
public class CuratorOperationDemo {

    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorClientUtils.getInstance();

        // fluent 风格

        /*
         * 创建节点
         */
        String result = curatorFramework.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .forPath("/curator/curaort1/xxx", "123".getBytes());
        System.out.println(result);

        /**
         * 删除节点
         * 默认情况下, version为-1
         */
        curatorFramework.delete()
                .deletingChildrenIfNeeded()
                .withVersion(-1)
                .forPath("/node1");

        /**
         * 获得数据
         */
        Stat stat = new Stat();
        byte[] bytes = curatorFramework.getData()
                .storingStatIn(stat)
                .forPath("/curator");
        System.out.println(new String(bytes));

        /**
         * 修改数据
         */
        Stat stat1 = curatorFramework.setData().forPath("/curator", "123".getBytes());
        System.out.println(stat1);

        /**
         * 异步操作
         */
        ExecutorService service = Executors.newFixedThreadPool(1);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        curatorFramework.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .inBackground(new BackgroundCallback() {
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        System.out.println(Thread.currentThread().getName() + "-> resultCode: "
                                + curatorEvent.getResultCode() + "->" + curatorEvent.getType());
                        countDownLatch.countDown();
                    }
                }, service)
                .forPath("/xxoo", "1234".getBytes());
        countDownLatch.await();
        service.shutdown();

        /**
         * 事务操作, curator独有
         */
        Collection<CuratorTransactionResult> resultCollection = curatorFramework.inTransaction()
                .create()
                .forPath("/transaction", "12455".getBytes())
                .and()
                .setData()
                .forPath("/xxoo", "111".getBytes())
                .and()
                .commit();
        for (CuratorTransactionResult transactionResult : resultCollection) {
            System.out.println(transactionResult.getForPath() + ":" + transactionResult.getType());
        }

    }
}
