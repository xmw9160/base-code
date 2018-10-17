package com.xmw.proxy.jdk;

/**
 * 张三, 单身
 * Date 2018/3/6.
 * Author xmw .
 */
public class Zhangsan implements Person {

    private String sex = "女";
    private String name = "小星星";

    public Zhangsan() {
        System.out.println("这是张三的构造方法....");
    }

    @Override
    public void findLove() {
        System.out.println("我叫: " + this.name + ", 性别: " + this.sex + ", 我的要求是: \n");
        System.out.println("高富帅");
        System.out.println("有房有车");
        System.out.println("身高1.8m以上, 体重60kg");
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
