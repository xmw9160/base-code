package com.xmw.factory.abst;

import com.xmw.factory.simple.Car;

/**
 * @author xmw.
 * @date 2018/3/9 21:46.
 */
@SuppressWarnings("Duplicates")
public abstract class AbstractFactory {
    protected abstract Car getCar();

    public Car getCar(String name) {
        if ("bmw".equalsIgnoreCase(name)) {
            return new BmwFactory().getCar();
        }
        if ("audi".equalsIgnoreCase(name)) {
            return new AudiFactory().getCar();
        }
        if ("benz".equalsIgnoreCase(name)) {
            return new BenzFactory().getCar();
        }
        System.out.println("没有这个产品!!!");
        return null;
    }
}
