package com.xmw.concurrent.classloader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 自定义classLoader, 重写findClass方法
 * JDK已经在loadClass方法中帮我们实现了ClassLoader搜索类的算法，当在loadClass方法中搜索不到类时，
 * loadClass方法就会调用findClass方法来搜索类，所以我们只需重写该方法即可。如没有特殊的要求，一般不建议重写loadClass搜索类的算法
 *
 * @author mingwei.xia
 * @date 2018/3/14 16:00
 * @since V1.0
 */
public class CustomClassLoader extends ClassLoader {

    private String classPah;

    public CustomClassLoader() {
    }

    public CustomClassLoader(String classPath) {
        this.classPah = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        return super.findClass(name);

        Class clazz;  //this.findLoadedClass(name); // 父类已加载
        //根据类的二进制名称,获得该class文件的字节码数组
        byte[] classData = getClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        }
        //将class的字节码数组转换成Class类的实例
        clazz = defineClass(name, classData, 0, classData.length);
        return clazz;
    }

    private byte[] getClassData(String name) {
        InputStream is = null;
        try {
            String path = classNameToPath(name);
            URL url = new URL(path);
            byte[] buff = new byte[1024 * 4];
            int len;
            is = url.openStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((len = is.read(buff)) != -1) {
                baos.write(buff, 0, len);
            }
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private String classNameToPath(String name) {
        return classPah + "/" + name.replace(".", "/") + ".class";
    }
}
