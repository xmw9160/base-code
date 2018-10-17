package com.xmw.singleton;

/**
 * Date 2018/2/2.
 * Author xmw .
 */
public class Singleton {

    private Singleton() {
    }

    public static Singleton getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private final static Singleton instance = new Singleton();
    }

}
