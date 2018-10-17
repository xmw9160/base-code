package com.xmw.factory.abst;

import com.xmw.factory.simple.Car;

/**
 * @author xmw.
 * @date 2018/3/9 21:51.
 */
public class DefaultFactory extends AbstractFactory {

    private AudiFactory factory = new AudiFactory();

    @Override
    public Car getCar() {
        return factory.getCar();
    }
}
