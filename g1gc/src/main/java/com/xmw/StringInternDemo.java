package com.xmw;

/**
 * https://blog.csdn.net/chao_19/article/details/77622960
 *
 * @author xmw.
 * @date 2018/6/25 22:19.
 */
public class StringInternDemo {
    public static void main(String[] args) {
        //生成了常量池的“1”和堆空间中的字符串对象。 s为堆空间的内存地址。
        String s = new String("1");
        // s.intern() 检查对象s是否在常量池中存在，如果存在则返回这个常量的值。
        s.intern();
        // s2指向常量池的地址
        String s2 = "1";
        System.out.println(s == s2);          // jdk6,7,8 false
        System.out.println(s.intern() == s2); // true
        System.out.println(s == s.intern());  // false 常量池已经存在1, s.intern返回的是1这个值

        String s3 = new String("ss") + new String("ss");
        s3.intern(); // 返回存储在堆中的引用
        String s4 = "ssss";
        System.out.println(s3 == s4);
        System.out.println(s3.intern() == s4);
        System.out.println(s3.intern() == s3);

        /**
         * s.intern()检查常量池中是否存在某一个常量，
         * 如果存在则返回这个常量的值。
         * 如果不存在，将字符串s的内容放入String常量池中，具体在JDK1.7中常量池不需要再存储一份对象了，
         * 可以直接存储堆中的引用，也就是s3==s3.intern()返回true。
         * PS： s3==s3.intern()并不总是成立。如果常量池中已经存在了这个字符串，则s==s.intern()并不相等。
         */
    }
}
