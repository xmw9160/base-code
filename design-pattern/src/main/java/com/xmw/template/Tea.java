package com.xmw.template;

/**
 * @author xmw.
 * @date 2018/3/10 22:47.
 */
public class Tea extends Bevegrage {
    @Override
    protected void addCondiment() {
        System.out.println("添加茶叶1..");
    }

    @Override
    protected void pourInCup() {
        System.out.println("添加茶叶0...");
    }
}
