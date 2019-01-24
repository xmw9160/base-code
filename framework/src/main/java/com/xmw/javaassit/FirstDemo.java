package com.xmw.javaassit;

import java.io.FileOutputStream;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;

public class FirstDemo {
    public static void main(String[] args) throws Exception {
        // 默认的类路径搜索
        ClassPool classPool = ClassPool.getDefault();
        // 生成一个ctClass对象
        CtClass ctClass = classPool.makeClass("com.xmw.javaassit.Demo");
        // 精简不必要的属性
//        ctClass.prune();
        // 取消精简
        ctClass.stopPruning(true);
        // 添加age属性
        ctClass.addField(CtField.make("private int age=1;", ctClass));
        //添加setAge方法
        ctClass.addMethod(CtMethod.make("public void setAge(int age){this.age = age;}", ctClass));
        //添加getAge方法
        ctClass.addMethod(CtMethod.make("public int getAge(){return this.age;}", ctClass));
        //将ctClass生成字节数组，并写入文件
        byte[] byteArray = ctClass.toBytecode();
        FileOutputStream output = new FileOutputStream("/Users/xmw/desktop/Demo.class");
        output.write(byteArray);
        output.close();
        System.out.println("文件写入成功!!!");

        // 获取ctClass对象，表示默认JVM已经加载此类.
        ctClass = classPool.get("com.xmw.javaassit.Demo");
        // 如果一个CtClass对象通过writeFile()，toClass()或者toBytecode()转换成了class文件，
        // 那么Javassist会冻结这个CtClass对象。后面就不能继续修改这个CtClass对象了。
        // 这样是为了警告开发者不要修改已经被JVM加载的class文件，因为JVM不允许重新加载一个类。
        if (ctClass.isFrozen()) {
            ctClass.defrost();
        }
        ctClass.addField(CtField.make("private String sex;", ctClass));
        ctClass.addField(CtField.make("private String name;", ctClass));

        byteArray = ctClass.toBytecode();
        output = new FileOutputStream("/Users/xmw/workspace/code/github/base-code/framework/target/classes/com/xmw/javaassit/Demo.class");
        output.write(byteArray);
        output.close();

        System.out.println("文件修改成功!!!!");
    }
}
