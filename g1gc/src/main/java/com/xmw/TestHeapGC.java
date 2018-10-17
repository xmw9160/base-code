package com.xmw;

/**
 * @author xmw.
 * @date 2018/6/28 23:42.
 */
public class TestHeapGC {
    public static void main(String[] args) {
        byte[] b1 = new byte[1024 * 1024 / 2];
        byte[] b2 = new byte[1024 * 1024 / 8];
        b2 = null;
        b2 = new byte[1024 * 1024 / 8];  //进行一次YGC
        System.gc();
    }
}
