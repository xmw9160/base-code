package com.xmw.prototype.greatestage;

/**
 * @author xmw.
 * @date 2018/3/10 22:06.
 */
public class TestProtoType {

    public static void main(String[] args) throws CloneNotSupportedException {
        TheGreatestSage sage = new TheGreatestSage();
        sage.change();

        // 克隆不走构造方法
        // 不一致, 怎么办?

    }
}
