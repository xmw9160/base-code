package com.xmw.apache.commons;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.MutableTriple;
import org.apache.commons.lang3.tuple.Triple;

/**
 * Triple抽象类，实现这个抽象类的类能够存储三个对象
 * 场景: 三个字段作为缓存key时使用
 *
 * @author xmw.
 * @date 2018/11/11 2:39 PM.
 */
public class TestTriple {
    public static void main(String[] args) {
        Triple.of(1, 2, 3);
        MutableTriple.of(2, 3, 4);
        ImmutableTriple.of(3, 4, 5);
    }
}
