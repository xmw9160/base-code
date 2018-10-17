package com.xmw.proxy.cglib;

/**
 * @author xmw.
 * @date 2018/3/7 22:00.
 */
public class CglibProxyTest {

    // JDK 的动态代理是通过接口来进行强制转换的
    // 生成以后的代理对象, 可以强制转换为接口

    // Cglib的动态代理是通过生成一个被代理对象的子类, 然后重写父类的
    // 方法, 生成以后的对象, 可已强制转换为被代理对象(也就是用自己写的类)
    // 子类引用赋值给父类

    public static void main(String[] args) {
//        YunZhongYu obj = (YunZhongYu) new MeiPo().getInstance(new YunZhongYu());
        YunZhongYu obj = (YunZhongYu) new MeiPo().getInstance(YunZhongYu.class);
        obj.findLove();
    }
}
