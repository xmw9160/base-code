package com.xmw.proxy.simple;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * http://www.importnew.com/23168.html
 * http://ifeve.com/jdk%E5%8A%A8%E6%80%81%E4%BB%A3%E7%90%86%E4%BB%A3%E7%90%86%E4%B8%8Ecglib%E4%BB%A3%E7%90%86%E5%8E%9F%E7%90%86%E6%8E%A2%E7%A9%B6/
 * VM args: -Dsun.misc.ProxyGenerator.saveGeneratedFiles=true
 * 把JDK动态生成的proxy class 的字节码保存到硬盘中，帮助查看具体生成proxy的内容
 *
 * 代理类生成的过程主要包括两部分：
 *  1.代理类字节码生成
 *  2.把字节码通过传入的类加载器加载到虚拟机中
 *
 * XXX 代理类中，所有的代理方法逻辑都一样都是调用invocationHandler的invoke方法
 */
public class JDKProxyTest {

    /**
     * ProxyClassFactory里的逻辑包括了包名的创建逻辑，调用ProxyGenerator. generateProxyClass生成代理类，把代理类字节码加载到JVM。
     *  1.包名生成逻辑默认是com.sun.proxy，如果被代理类是 non-public proxy interface ，则用和被代理类接口一样的包名，类名默认是$Proxy 加上一个自增的整数值。
     *  2.包名类名准备好后，就是通过ProxyGenerator. generateProxyClass根据具体传入的接口创建代理字节码，
     *      -Dsun.misc.ProxyGenerator.saveGeneratedFiles=true 这个参数就是在该方法起到作用，如果为true则保存字节码到磁盘。
     *      代理类中，所有的代理方法逻辑都一样都是调用invocationHandler的invoke方法，这个我们可以看后面具体代理反编译结果。
     *  3.把字节码通过传入的类加载器加载到JVM中: defineClass0(loader, proxyName,proxyClassFile, 0, proxyClassFile.length);。
     */

    /**
     * 根据代理类的字节码(项目更目录下:com/sun/proxy/$Proxy0.class)进行反编译，可以得到如下结果，其中HelloWorld只有sayHello方法，
     * 但是代理类中有四个方法 包括了Object上的三个方法：equals,toString,hashCode。
     *
     * 代理的大概结构包括4部分：
     *  1.静态字段：被代理的接口所有方法都有一个对应的静态方法变量；
     *  2.静态块：主要是通过反射初始化静态方法变量；
     *  3.具体每个代理方法：逻辑都差不多就是 h.invoke，主要是调用我们定义好的invocationHandler逻辑, 触发目标对象target上对应的方法;
     *  4.构造函数：从这里传入我们InvocationHandler逻辑；
     */

    /**
     * 常见问题：
     *  1.toString() hashCode() equal()方法 调用逻辑：这个三个Object上的方法，如果被调用将和其他接口方法方法处理逻辑一样，
     *     都会经过invocationHandler逻辑，从上面的字节码结果就可以明显看出。其他Object上的方法将不会走代理处理逻辑，直接走Proxy继承的Object上方法逻辑。
     *  2.interface 含有equals,toString hashCode方法时，和处理普通接口方法一样，都会走invocation handler逻辑，
     *      以目标对象重写的逻辑为准去触发方法逻辑；
     *  3.interface含有重复的方法签名,以接口传入顺序为准，谁在前面就用谁的方法，代理类中只会保留一个，不会有重复的方法签名；
     */

    /**
     * 对应JDK动态代理机制是委托机制，具体说动态实现接口类，在动态生成的实现类里面委托为hanlder去调用原始实现类方法。
     */

    public static void main(String[] args) throws Exception {

        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        // 这里有两种写法，我们采用略微复杂的一种写法，这样更有助于大家理解。
        // 生成HelloWorld的代理类, 创建代理类的具体逻辑是通过ProxyClassFactory的apply方法来创建的
        Class<?> proxyClass= Proxy.getProxyClass(JDKProxyTest.class.getClassLoader(), HelloWorld.class);
        final Constructor<?> cons = proxyClass.getConstructor(InvocationHandler.class);
        final InvocationHandler ih = new MyInvocationHandler(new HelloworldImpl());
        HelloWorld helloWorld= (HelloWorld)cons.newInstance(ih);
        helloWorld.sayHello();

        // 下面是更简单的一种写法，本质上和上面是一样的
        /*
        HelloWorld helloWorld=(HelloWorld)Proxy.
                 newProxyInstance(JDKProxyTest.class.getClassLoader(),
                        new Class<?>[]{HelloWorld.class},
                        new MyInvocationHandler(new HelloWorldImpl()));
        helloWorld.sayHello();
        */
    }
}
