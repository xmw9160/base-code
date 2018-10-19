package com.xmw.concurrent.classloader;

import java.lang.reflect.Method;

/**
 * TODO 功能描述
 *
 * @author mingwei.xia
 * @date 2018/3/14 17:24
 * @since V1.0
 */
public class DiskClassLoaderTest {
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        //创建自定义classloader对象。
        DiskClassLoader diskLoader = new DiskClassLoader("C:\\xmw\\code");
        try {
            //加载class文件
            Class c = diskLoader.loadClass("com.hcb.classloader.Test1");

            if (c != null) {
                try {
                    Object obj = c.newInstance();
                    Method method = c.getDeclaredMethod("say", String.class);
                    //通过反射调用Test类的say方法
                    method.invoke(obj, "custom class loader");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        byte b = 3;
    }
}
