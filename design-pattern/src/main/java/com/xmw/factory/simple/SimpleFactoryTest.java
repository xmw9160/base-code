package com.xmw.factory.simple;

/**
 * @author xmw.
 * @date 2018/3/7 23:08.
 */
public class SimpleFactoryTest {
    public static void main(String[] args) {
        // 这就是我们的消费者
        Car car = new SimpleFactory().getCar("BMW");
        System.out.println(car.getName());
    }
}
