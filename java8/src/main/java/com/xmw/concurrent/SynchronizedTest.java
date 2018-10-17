package com.xmw.concurrent;

/**
 * Date 2018/1/22.
 * Author xmw .
 */
public class SynchronizedTest {
    private static Object object = new Object();

    public static void main(String[] args) throws Exception {
        synchronized (object) {

        }
    }

    public static synchronized void m() {
    }
}