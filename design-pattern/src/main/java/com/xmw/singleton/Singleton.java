package com.xmw.singleton;

/**
 * 静态内部类单例设计实现
 *
 * @author xmw.
 * @date 2018/3/9 22:28.
 */
public final class Singleton {

    private Singleton() {
    }

    public static Singleton getInstance() {
        return SingleHolder.SINGLETON_INSTANCE;
    }

    private static class SingleHolder {
        private static final Singleton SINGLETON_INSTANCE = new Singleton();
    }
}
