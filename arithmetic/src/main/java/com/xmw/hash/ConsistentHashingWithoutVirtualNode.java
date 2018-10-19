package com.xmw.hash;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 不带虚拟节点的一致性Hash算法. FNV1_32_HASH算法.
 *
 * @author mingwei.xia
 * @date 2018/3/7 17:53
 * @since V1.0
 */
public class ConsistentHashingWithoutVirtualNode {

    /**
     * 待添加入Hash环的服务器列表
     */
    private static String[] servers = {"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111",
            "192.168.0.3:111", "192.168.0.4:111"};

    /**
     * key表示服务器的hash值，value表示服务器的名称
     */
    private static SortedMap<Integer, String> sortedMap = new TreeMap<>();

    /**
     * 程序初始化，将所有的服务器放入sortedMap中
     */
    static {
        for (String server : servers) {
            int hash = HashUtil.getHash(server);
            System.out.println("[" + server + "]加入集合中, 其Hash值为: " + hash);
            sortedMap.put(hash, server);
        }
        System.out.println("-----------------------------------------");
    }

    /**
     * 得到应当路由到的结点
     */
    private static String getServer(String node) {
        // 得到带路由的结点的Hash值
        int hash = HashUtil.getHash(node);
        // 得到大于该Hash值的所有Map
        SortedMap<Integer, String> subMap = sortedMap.tailMap(hash);
        // 第一个Key就是顺时针过去离node最近的那个结点
        Integer i = subMap.firstKey();
        // 返回对应的服务器名称
        return subMap.get(i);
    }

    public static void main(String[] args) {
        String[] nodes = {"127.0.0.1:1111", "221.226.0.1:2222", "10.211.0.1:3333"};
        for (String node : nodes) {
            System.out.println("[" + node + "]的hash值为: " + HashUtil.getHash(node) + ", 被路由到结点[" + getServer(node) + "]");
        }
    }
}