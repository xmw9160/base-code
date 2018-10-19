package com.xmw.test;

import java.util.HashMap;
import java.util.Map;

import com.xmw.test.bean.Person;

/**
 * Date 2018/1/27.
 * Author xmw .
 */
public class HashCodeCase {

    public static void main(String[] args) {
        Map<Person, String> map = new HashMap<>();
        map.put(new Person(1, "a"), "person1");
        String result = map.get(new Person(1, "a"));
        System.out.println(result);
    }
}
