package com.xmw.guava.collection;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * Date 2018/1/28.
 * Author xmw .
 */
public class BitMapDemo {

    /**
     * 在实际场景中有这样的需求吗？比如通过用户ID找到mail，也需要通过mail找回用户名。
     * 没有guava的时候，我们需要create forward map AND create backward map，
     * and now just let guava do that for you.
     */
    public static void main(String[] args) {
        BiMap<String, String> biMap = HashBiMap.create();
        biMap.put("sina", "sina.com");
        biMap.put("qq", "qq.com");
        biMap.put("sina", "sina.cn"); //会覆盖原来的value
       /*
          key/value 都不允许重复, 可以过value找key
         在BiMap中,如果你想把键映射到已经存在的值，会抛出IllegalArgumentException异常
         如果对特定值,你想要强制替换它的键，请使用 BiMap.forcePut(key, value)
        */
        biMap.put("tecent", "qq.com"); //抛出异常
        biMap.forcePut("tecent", "qq.com"); //强制替换key
        System.out.println(biMap);
        System.out.println(biMap.inverse().get("sina.com")); //通过value找key
        System.out.println(biMap.inverse().inverse() == biMap); //true

    }
}
