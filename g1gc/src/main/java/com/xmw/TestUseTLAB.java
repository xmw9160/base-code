package com.xmw;

/**
 * @author xmw.
 * @date 2018/7/2 23:29.
 */
public class TestUseTLAB {
    public static void allocdemo() {
        byte[] by = new byte[2];
        by[0] = 1;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            allocdemo();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("use time: " + (endTime - startTime));
        while (true) {
            System.out.println(".....");
        }
    }
}
