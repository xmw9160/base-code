package com.xmw.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 媒婆
 * Date 2018/3/6.
 * Author xmw .
 */
public class Meipo implements InvocationHandler {

    // 被代理对象的引用作为一个成员变量保存下来了
    private Person target;

    // 获取代理人的个人资料
    public Object getInstance(Person target) {
        this.target = target;
        Class<? extends Person> clazz = target.getClass();
        System.out.println("被代理对象的class是: " + clazz);
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);

        // 原理:
        //1. 拿到被代理对象的引用, 然后获取它的接口
        //2. JDK代理重新生成一个类, 同时实现我们给的代理对象所实现的接口
        //3. 把被代理对象的引用也拿到了
        //4. 重新动态生成一个class字节码
        //5. 然后编译
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是媒婆, 你的性别是: " + this.target.getSex() + ", 给你找一个异性");
        System.out.println("开始进行海选.....");
        System.out.println("---------------");

        // 调用的时候,
        method.invoke(this.target, args);

        System.out.println("---------------");
        System.out.println("如果合适的话, 准备办事....");
        return null;
    }
}
