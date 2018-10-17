package com.xmw.factory.abst;

import com.xmw.factory.simple.Audi;
import com.xmw.factory.simple.Car;

/**
 * @author xmw.
 * @date 2018/3/9 21:38.
 */
public class AudiFactory extends AbstractFactory {
    @Override
    public Car getCar() {
        return new Audi();
    }
}
