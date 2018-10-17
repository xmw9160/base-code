package com.xmw.template;

/**
 * 冲饮料
 *
 * @author xmw.
 * @date 2018/3/10 22:41.
 */
public abstract class Bevegrage {

    // 不能被重写
    public final void create() {
        //1, 把水烧开
        boilWater();
        //2, 把杯子准备好, 原材料放到杯中
        pourInCup();
        //3, 用水冲泡
        brew();
        //4, 添加辅料
        addCondiment();
    }

    protected abstract void addCondiment();

    protected abstract void pourInCup();

    private void boilWater() {
        System.out.println("把水烧开到100度...");
    }

    private void brew() {
        System.out.println("将开水倒入杯中, 开始饮用...");
    }
}
