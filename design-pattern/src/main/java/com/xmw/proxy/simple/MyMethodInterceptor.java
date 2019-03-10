package com.xmw.proxy.simple;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class MyMethodInterceptor implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz) {
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        //enhancer.setCallbackType(clazz);;
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("Cglib MethodInterceptor intercept ...");
        Object result = proxy.invokeSuper(obj, args);
        // 会递归调用->sof
//          Object result = method.invoke(obj, args);
        return result;
    }
}
