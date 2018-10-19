package com.xmw.reflection;

/**
 * TODO 功能描述
 *
 * @author mingwei.xia
 * @date 2018/5/3 20:44
 * @since V1.0
 */
public class MyClassObject {

    private String name;
    private int age;

    public MyClassObject(int age) {
        this.age = age;
    }

    public MyClassObject() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getList() {
        return "testList";
    }

    public String getTest(String name) {
        return "testList123";
    }
}
