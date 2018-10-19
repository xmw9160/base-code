package com.xmw.java8;

/**
 * @author mingwei.xia
 * @date 2018/6/21 17:24
 * @since V1.0
 */
public class Letter {
    public static String addHeader(String text) {
        return "From Raoul, Mario and Alan: " + text;
    }

    public static String addFooter(String text) {
        return text + " Kind regards";
    }

    public static String checkSpelling(String text) {
        return text.replaceAll("labda", "lambda");
    }
}
