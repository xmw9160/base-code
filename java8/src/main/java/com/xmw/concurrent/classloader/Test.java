package com.xmw.concurrent.classloader;

/**
 * TODO 功能描述
 *
 * @author mingwei.xia
 * @date 2018/3/14 17:26
 * @since V1.0
 */
public class Test {
    public static void main(String[] args) {
        ClassLoader classLoader = Test.class.getClassLoader();
        System.out.println(classLoader);
    }

    public void say(String name) {
        System.out.println("hello world, i am a " + name);
    }
}
