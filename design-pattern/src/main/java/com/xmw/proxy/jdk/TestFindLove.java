package com.xmw.proxy.jdk;

/**
 * Date 2018/3/6.
 * Author xmw .
 */
public class TestFindLove {

    public static void main(String[] args) throws Exception {
//        Zhangsan zhangsan = new Zhangsan();
//        zhangsan.findLove();

        Person instance = (Person) new Meipo().getInstance(new Zhangsan());
        System.out.println("对象: " + instance);
        instance.findLove();

    }
}
