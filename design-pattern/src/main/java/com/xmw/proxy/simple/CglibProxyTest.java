package com.xmw.proxy.simple;

import net.sf.cglib.core.DebuggingClassWriter;

public class CglibProxyTest {
    public static void main(String[] args) {
        /**
         * 对应Cglib则使用的继承机制，具体说被代理类和代理类是继承关系，
         * 所以代理类是可以赋值给被代理类的,如果被代理类有接口，那么代理类也可以赋值给接口。
         *
         * 另外JDK代理只能对接口进行代理，Cglib则是对实现类进行代理。
         */

        //生成代理类到本地
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/xmw/workspace/code/github/base-code/design-pattern/src/main/java/com/xmw/proxy/simple");

        MyMethodInterceptor cglibProxy = new MyMethodInterceptor();
        HelloWorld helloworld = (HelloWorld) cglibProxy.getProxy(HelloworldImpl.class);
        helloworld.sayHello();
    }
}
