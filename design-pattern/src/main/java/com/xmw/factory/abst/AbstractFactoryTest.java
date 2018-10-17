package com.xmw.factory.abst;

/**
 * @author xmw.
 * @date 2018/3/9 21:50.
 */
public class AbstractFactoryTest {

    public static void main(String[] args) {
        DefaultFactory factory = new DefaultFactory();
        System.out.println(factory.getCar());
        System.out.println(factory.getCar("Audi"));

        // 设计模式的经典之处, 在于解决白那些代码的人和调用代码的人双方的痛楚
    }
}
