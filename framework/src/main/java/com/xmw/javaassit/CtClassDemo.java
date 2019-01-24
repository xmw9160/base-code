package com.xmw.javaassit;

import javassist.ClassPool;
import javassist.CtClass;

/**
 * https://www.jianshu.com/p/43424242846b
 */
public class CtClassDemo {

    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("com.xmw.javaassit.FirstDemo");
        cc.setSuperclass(pool.get("com.xmw.javaassit.CtClassDemo"));
        cc.writeFile();
    }
}
