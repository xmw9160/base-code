package com.xmw.disabuse;

/**
 * java 解惑
 *
 * @author mingwei.xia
 * @date 2018/3/8 15:52
 * @since V1.0
 */
public class JavaDisabuse {

    public static void main(String[] args) {
        /*找零*/
//        System.out.println(2.0 - 1.1); //0.8999999999999999
        // 解决方案: 1, 使用整数分来加减
        //          2, 使用BigDecimal来处理: new BigDecimal("2.0"); 必须使用字符串来初始化

        /*长整除*/
//        final long MICROS_PER_DAY = 24 * 60 * 60 * 1000 * 1000; // 500654080
//        final long MICROS_PER_DAY_ = 24L * 60 * 60 * 1000 * 1000;
//        final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;        // 86400000
//        final long MILLIS_PER_DAY_ = 24L * 60 * 60 * 1000;
//        System.out.println(MICROS_PER_DAY / MILLIS_PER_DAY); // 5
        // long maxValue : 9223372036854775807

        // 初级问题
//        System.out.println(12345 + 54321);

//        System.out.println((int)(char)(byte)-1);  //65535

//        char x = 'X';
//        int i = 0;
//        System.out.println(true ? x : i); //88
//        System.out.println(false ? i : x); //88
    }

    /**
     * 判断奇数
     */
    public boolean isOdd(int i) {
//        return i % 2 == 1; //false
//        return i % 2 != 0; //true
        return (i & 1) != 0; //better
    }
}
