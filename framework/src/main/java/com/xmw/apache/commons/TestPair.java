package com.xmw.apache.commons;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Pair抽象类，实现这个抽象类的类能够存储两个对象
 * 场景: 当用两个字段作为缓存key的时候使用,避免了手动拼接key
 *
 * @author xmw.
 * @date 2018/11/11 2:49 PM.
 */
public class TestPair {
    public static void main(String[] args) {
        Pair.of(1, 3);
        ImmutablePair.of(2, 4);
        MutablePair.of(3, 5);
    }
}
