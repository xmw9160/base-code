package com.xmw.prototype.simple;

import java.util.ArrayList;

/**
 * @author xmw.
 * @date 2018/3/10 21:24.
 */
public class ConCreatePrototype implements Cloneable {

    public ArrayList<String> list = new ArrayList<>();
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        ConCreatePrototype prototype;
        prototype = (ConCreatePrototype) super.clone();
        prototype.list = (ArrayList<String>) list.clone();

        // 克隆基于字节码
        // 用反射, 或者循环


        return prototype;
    }
}
