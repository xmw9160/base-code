package com.xmw.java8;

/**
 * @author mingwei.xia
 * @date 2018/8/24 9:56
 * @since V1.0
 */
public class Demo {
    public static void main(String[] args) {
//        Proxy.newProxyInstance()
//        byte[] bytes = new byte[100 * 1024 * 1024];

//        System.out.println(Objects.equals(null, null));

        int a = Integer.MAX_VALUE;
        int b = 5;
        if (a < 5 - b) {
            System.out.println(a + b);
        }
    }
}
