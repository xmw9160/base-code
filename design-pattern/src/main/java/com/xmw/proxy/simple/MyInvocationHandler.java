package com.xmw.proxy.simple;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

    /**
     * 被代理对象
     */
    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("method :" + method.getName() + " is invoked!");
        return method.invoke(target, args);
    }
}
