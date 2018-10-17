package com.xmw.prototype.greatestage;

import java.io.Serializable;
import java.util.Date;

/**
 * 猴子
 *
 * @author xmw.
 * @date 2018/3/10 21:54.
 */
public class Monkey implements Serializable {

    public int height; // 基本
    public int weight; // 基本
    public Date birthday; // 不是今本类型

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
