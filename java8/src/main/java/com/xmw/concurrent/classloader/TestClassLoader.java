package com.xmw.concurrent.classloader;

/**
 * ClassLoader
 *
 * @author mingwei.xia
 * @date 2018/3/14 15:07
 * @since V1.0
 */
public class TestClassLoader {
    public static void main(String[] args) throws ClassNotFoundException {
        // 一、BootStrap ClassLoader启动类加载器加载的jar的两种方式
        // 第一种
//        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
//        for (URL url : urls) {
//            System.out.println(url.toExternalForm());
//        }
        // 第二种
//        System.out.println(System.getProperty("sun.boot.class.path"));
        // 二、Extension ClassLoader：称为扩展类加载器，负责加载Java的扩展类库，默认加载JAVA_HOME/jre/lib/ext/目下的所有jar。
        // 三、App ClassLoader：称为系统类加载器，负责加载应用程序classpath目录下的所有jar和class文件。

        /**
         * 除了Java默认提供的三个ClassLoader之外，用户还可以根据需要定义自已的ClassLoader，
         * 而这些自定义的ClassLoader都必须继承自java.lang.ClassLoader类，也包括Java提供的
         * 另外二个ClassLoader（Extension ClassLoader和App ClassLoader）在内，
         * 但是Bootstrap ClassLoader不继承自ClassLoader，因为它不是一个普通的Java类，
         * 底层由C++编写，已嵌入到了JVM内核当中，当JVM启动后，Bootstrap ClassLoader也随着启动，
         * 负责加载完核心类库后，并构造Extension ClassLoader和App ClassLoader类加载器。
         */

//        ClassLoader loader = TestClassLoader.class.getClassLoader();
//        while (loader != null) {
//            System.out.println(loader);
//            loader = loader.getParent(); // 获取父类加载器的引用
//        }
//        System.out.println(loader);

//        sun.misc.Launcher$AppClassLoader@146ccf3e //ClassLoaderTest的类加载器是AppClassLoader
//        sun.misc.Launcher$ExtClassLoader@7399f9eb //AppClassLoader的类加器是ExtClassLoader，即parent=ExtClassLoader。
        // ExtClassLoader的类加器是Bootstrap ClassLoader，因为Bootstrap ClassLoader不是
        // 一个普通的Java类，所以ExtClassLoader的parent=null，所以第三行的打印结果为null就是这个原因。
//        null

        // 获取extClassLoader加载的jar路径
        System.out.println(System.getProperty("java.ext.dirs"));
        // 获取AppClassLoader加载的类路径
        System.out.println(System.getProperty("java.class.path"));

//        String rootUrl = "http://localhost:8080/httpweb/classes";
//        CustomClassLoader customClassLoader = new CustomClassLoader(rootUrl);
//        String classname = "org.classloader.simple.NetClassLoaderTest";
//        Class clazz = customClassLoader.loadClass(classname);
//        System.out.println(clazz.getClassLoader());
    }
}
