package com.xmw.factory.abst;

import com.xmw.factory.simple.Bmw;
import com.xmw.factory.simple.Car;

/**
 * @author xmw.
 * @date 2018/3/9 21:38.
 */
public class BmwFactory extends AbstractFactory {
    @Override
    public Car getCar() {
        return new Bmw();
    }
}
