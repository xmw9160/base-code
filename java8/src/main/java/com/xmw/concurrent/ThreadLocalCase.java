package com.xmw.concurrent;

/**
 * Date 2018/1/24.
 * Author xmw .
 */
public class ThreadLocalCase {

    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        try {
            threadLocal.set("xmw01");
            threadLocal.set("xmw02");
            String result = threadLocal.get();
            System.out.println(result);
        } finally {
            threadLocal.remove();
        }
    }
}
