package com.xmw.zookeeper.zkclient;

import org.I0Itec.zkclient.ZkClient;

/**
 * Date 2017/12/18.
 * Author xmw .
 */
public class SessionDemo {

    private final static String CONNECT_STRING = "192.168.31.133:2181";

    public static void main(String[] args) {

        ZkClient client = new ZkClient(CONNECT_STRING, 4000);
        System.out.println(client + "-> success");
    }
}
