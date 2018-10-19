package com.xmw.hash;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 带虚拟节点的一致性Hash算法
 * 从代码运行结果看，每个点路由到的服务器都是Hash值顺时针离它最近的那个服务器节点，没有任何问题。
 * 通过采取虚拟节点的方法，一个真实结点不再固定在Hash换上的某个点，而是大量地分布在整个Hash环上，
 * 这样即使上线、下线服务器，也不会造成整体的负载不均衡。
 * @author 五月的仓颉 http://www.cnblogs.com/xrq730/
 */
@SuppressWarnings("Duplicates")
public class ConsistentHashingWithVirtualNode {
    /**
     * 待添加入Hash环的服务器列表
     */
    private static String[] servers = {"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111",
            "192.168.0.3:111", "192.168.0.4:111"};

    /**
     * 真实结点列表,考虑到服务器上线、下线的场景，即添加、删除的场景会比较频繁，这里使用LinkedList会更好
     */
    private static List<String> realNodes = new LinkedList<>();

    /**
     * 虚拟节点，key表示虚拟节点的hash值，value表示虚拟节点的名称
     */
    private static SortedMap<Integer, String> virtualNodes = new TreeMap<>();

    /**
     * 虚拟节点的数目，这里写死，为了演示需要，一个真实结点对应5个虚拟节点
     */
    private static final int VIRTUAL_NODES = 5;

    static {
        // 先把原始的服务器添加到真实结点列表中
        realNodes.addAll(Arrays.asList(servers));

        // 再添加虚拟节点，遍历LinkedList使用foreach循环效率会比较高
        /**
         * 这两个问题其实有很多解决办法，我这里使用了一种简单的办法，给每个真实结点后面根据虚拟
         * 节点加上后缀再取Hash值，比如"192.168.0.0:111"就把它变成"192.168.0.0:111&&VN0"
         * 到"192.168.0.0:111&&VN4"，VN就是Virtual Node的缩写，还原的时候只需要从头截取字符串到"&&"的位置就可以了。
         */
        for (String str : realNodes) {
            for (int i = 0; i < VIRTUAL_NODES; i++) {
                String virtualNodeName = str + "&&VN" + String.valueOf(i);
                int hash = HashUtil.getHash(virtualNodeName);
                System.out.println("虚拟节点[" + virtualNodeName + "]被添加, hash值为" + hash);
                virtualNodes.put(hash, virtualNodeName);
            }
        }
        System.out.println();
    }

    /**
     * 得到应当路由到的结点
     */
    private static String getServer(String node) {
        // 得到带路由的结点的Hash值
        int hash = HashUtil.getHash(node);
        // 得到大于该Hash值的所有Map
        SortedMap<Integer, String> subMap = virtualNodes.tailMap(hash);
        // 第一个Key就是顺时针过去离node最近的那个结点
        Integer i = subMap.firstKey();
        // 返回对应的虚拟节点名称，这里字符串稍微截取一下
        String virtualNode = subMap.get(i);
        return virtualNode.substring(0, virtualNode.indexOf("&&"));
    }

    public static void main(String[] args) {
        String[] nodes = {"127.0.0.1:1111", "221.226.0.1:2222", "10.211.0.1:3333"};
        for (String node : nodes) {
            System.out.println("[" + node + "]的hash值为" + HashUtil.getHash(node) + ", 被路由到结点[" + getServer(node) + "]");
        }
    }
}