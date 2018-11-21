package com.xmw.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MeiPo implements MethodInterceptor {


    // 疑问?
    // 好像并没有持有被代理对象的引用
    public Object getInstance(Class clazz) {
        // 通过反射机制, 给他实例化
        Enhancer enhancer = new Enhancer();
        // 把父类设置为谁
        // 告诉cglib, 生成的子类需要继承那个类
        enhancer.setSuperclass(clazz);
        // 设置回调
        enhancer.setCallback(this);
        // 第一步, 生成源代码
        // 第二步, 编译class文件
        // 第三步, 加载class文件到jvm中, 并返回被代理的对象
        return enhancer.create();
    }

    // 同样是做了字节码重组这样一件事
    // 对于使用API的用户来说, 是无感知的
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("我是cglib媒婆,  给你找一个异性");
        System.out.println("开始进行海选.....");
        System.out.println("---------------");

        // 这个obj的引用是由Cglib给我们new出来的
        // cglib new出来的对象, 是被代理对象的子类(继承了我们自己写的那个类)
        // OOP, 在new子类之前, 实际上默认调用了我们super()方法的
        // new 了子类的同时, 必须先new出父类, 这就相当于是间接的持有了我们父类的引用
        // 子类重写了父类的所有方法
        // 我们改变了子类对象的某些属性, 是可以间接的操作父类的属性的
        proxy.invokeSuper(obj, args);
//        proxy.invoke(obj, args);  // 代理自己调用自己, sof stack over flow

        System.out.println("---------------");
        System.out.println("如果合适的话, 准备办事....");
        return null;
    }
}
