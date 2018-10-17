package com.xmw.template;

/**
 * @author xmw.
 * @date 2018/3/10 22:46.
 */
public class Coffee extends Bevegrage {
    @Override
    protected void addCondiment() {
        System.out.println("添加牛奶...");
    }

    @Override
    protected void pourInCup() {
        System.out.println("放入coffee..");
    }
}
