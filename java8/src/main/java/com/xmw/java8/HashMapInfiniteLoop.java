package com.xmw.java8;

import java.util.HashMap;

/**
 * jdk8 hashMap 无限循环测试
 *
 * @author mingwei.xia
 * @date 2018/9/10 11:42
 * @since V1.0
 */
public class HashMapInfiniteLoop {

    private static HashMap<Integer, String> map = new HashMap<>(2, 0.75F);

    public static void main(String[] args) {
        map.put(5, "C");

        new Thread("Thread1") {
            @Override
            public void run() {
                map.put(7, "B");
                System.out.println(map);
            }
        }.start();

        new Thread("Thread2") {
            @Override
            public void run() {
                map.put(3, "A");
                System.out.println(map);
            }
        }.start();
    }
}
