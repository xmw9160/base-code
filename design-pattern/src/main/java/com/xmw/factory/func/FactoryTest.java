package com.xmw.factory.func;

import com.xmw.factory.simple.Car;

/**
 * @author xmw.
 * @date 2018/3/9 21:40.
 */
public class FactoryTest {

    public static void main(String[] args) {
        // 工厂方法模式
        // 产品的生产商, 都拥有自己的工厂
        // 生成工艺
        Factory factory = new AudiFactory();
        Car car = factory.getCar();
        System.out.println(car);

        // 需要用户关心, 这个产品的生成商
        factory = new BenzFactory();
        System.out.println(factory.getCar());

        // 增加了代码的使用复杂度
    }
}
