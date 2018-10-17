package com.xmw.guava.collection2;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

/**
 * Date 2018/1/28.
 * Author xmw .
 */
public class FilterDemo {

    public static void main(String[] args) {
        // filter（）：只保留集合中满足特定要求的元素
        List<String> list = Lists.newArrayList("moon", "dad", "refer", "son", "adccda");
        Collection<String> palindromeList = Collections2.filter(list, input -> {
            return new StringBuilder(input).reverse().toString().equals(input); //找回文串
        });
        System.out.println(palindromeList);
    }
}
