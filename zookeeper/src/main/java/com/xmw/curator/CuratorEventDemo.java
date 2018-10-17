package com.xmw.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.TimeUnit;

/**
 * Date 2017/12/18.
 * Author xmw .
 */
public class CuratorEventDemo {
    public static void main(String[] args) throws Exception {
        /**
         * 三种watcher来做节点监听
         * pathCache 监听一个路径下子节点的创建,删除,节点的数据更新
         * nodeCache 监视一个节点的创建,更新,删除
         * TreeCache pathCache + nodeCache的合体(监视路径下的创建,更新, 删除事件)
         * 缓存路径下所有子节点和数据
         */

        CuratorFramework curatorFramework = CuratorClientUtils.getInstance();

        /**
         * 节点变化
         */
        final NodeCache cache = new NodeCache(curatorFramework, "/curator", false);
        cache.start(true);
        cache.getListenable().addListener(() -> System.out.println("节点数据发生变化后, 变化后的结果 -> " + new String(cache.getCurrentData().getData())));
        curatorFramework.setData().forPath("/xmw", "test".getBytes());
        System.in.read();

        /**
         * patchChildrenCache
         */
        final PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, "/xxx", true);
        pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        //Normal / build init cache / POST_INITIALIZED_EVENT
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                switch (pathChildrenCacheEvent.getType()) {
                    case CHILD_ADDED:
                        System.out.println("增加子节点.");
                        break;
                    case CHILD_REMOVED:
                        System.out.println("删除子节点.");
                        break;
                    case CHILD_UPDATED:
                        System.out.println("修改子节点.");
                        break;
                    default:
                        break;
                }
            }
        });

        curatorFramework.create()
                .withMode(CreateMode.PERSISTENT)
                .forPath("/xxx", "xxx".getBytes());
        TimeUnit.SECONDS.sleep(1);

        curatorFramework.create()
                .withMode(CreateMode.EPHEMERAL)
                .forPath("/xxx/ooo", "000".getBytes());
        TimeUnit.SECONDS.sleep(1);

        curatorFramework.setData().forPath("/xxx/ooo", "111".getBytes());
        TimeUnit.SECONDS.sleep(1);

        curatorFramework.delete().forPath("/xxx/ooo");

        System.in.read();
    }
}
