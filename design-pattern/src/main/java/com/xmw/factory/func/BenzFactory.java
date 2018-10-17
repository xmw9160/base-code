package com.xmw.factory.func;

import com.xmw.factory.simple.Audi;
import com.xmw.factory.simple.Car;

/**
 * @author xmw.
 * @date 2018/3/9 21:38.
 */
public class BenzFactory implements Factory {
    @Override
    public Car getCar() {
        return new Audi();
    }


}
