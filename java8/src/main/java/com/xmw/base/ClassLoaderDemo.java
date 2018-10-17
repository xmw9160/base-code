package com.xmw.base;

/**
 * Date 2018/2/8.
 * Author xmw .
 */
public class ClassLoaderDemo extends ClassLoader {

    public static void main(String[] args) {
        ClassLoader classLoader = String.class.getClassLoader();
        // null .引导类加载器特殊，它根本就不是java实现。
        // 所有在得到引导类回载器是结果就是null.
        System.out.println(classLoader); // null

        ClassLoader classLoader1 = BaseDemo.class.getClassLoader();
        System.out.println(classLoader1);

    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
