package com.xmw.guava;

import com.google.common.base.CharMatcher;

/**
 * Guava CharMatcher
 * 场景: 字符匹配
 * https://my.oschina.net/realfighter/blog/349815
 *
 * @author mingwei.xia
 * @date 2018/10/25 9:47
 * @since V1.0
 */
public class CharMatcherDemo {
    // 用于匹配所有的可换行的空白符
    public static final CharMatcher BREAKING_WHITESPACE = new CharMatcher() {
        @Override
        public boolean matches(char c) {
            switch (c) {
                case '\t':
                case '\n':
                case '\013':
                case '\f':
                case '\r':
                case ' ':
                case '\u0085':
                case '\u1680':
                case '\u2028':
                case '\u2029':
                case '\u205f':
                case '\u3000':
                    return true;
                case '\u2007':
                    return false;
                default:
                    return c >= '\u2000' && c <= '\u200a';
            }
        }
    };

    //打印方法
    private static void print(Object obj) {
        System.out.println(String.valueOf(obj));
    }

    public static void main(String[] args) {
        //原始字符串
        String sequence = "HELLO   RealFighter ~!@#$%^&*() ，,.。   \n123(*&gS   你好\t234啊   abc  \n";
        print(sequence);
        //使用JAVA_ISO_CONTROL去除所有控制字符\n\t
        String str = CharMatcher.javaIsoControl().removeFrom(sequence);
        print(str);
        //筛选出所有的字母(包含中文)或数字
        str = CharMatcher.javaLetterOrDigit().retainFrom(sequence);
        print(str);
        //匹配sequence中的数字并全部替换成*号
        str = CharMatcher.javaDigit().replaceFrom(sequence, "*");
        print(str);
        //去除首尾连续匹配到的大写字符
        str = CharMatcher.javaUpperCase().trimFrom(sequence);
        print(str);
        //去除首部连续匹配到的大写字符
        str = CharMatcher.javaUpperCase().trimLeadingFrom(sequence);
        print(str);
        //去除尾部连续匹配到的大写字符
        str = CharMatcher.javaUpperCase().trimTrailingFrom(sequence);
        print(str);
        //将匹配到的大写字符替换成问号
        str = CharMatcher.javaUpperCase().collapseFrom(sequence, '?');
        print(str);
        //去除首尾空白符后将匹配到的小写字符替换为问号
        str = CharMatcher.javaLowerCase().trimAndCollapseFrom(sequence, '?');
        print(str);
    }
}
