package com.xmw.prototype.greatestage;

import java.io.Serializable;

/**
 * 金箍棒
 *
 * @author xmw.
 * @date 2018/3/10 21:56.
 */
public class GoldRingedStaff implements Serializable {

    private float height = 100; // 长度
    private float diameter = 10; // 直径

    public void grow() {
        this.diameter *= 2;
        this.height *= 2;
    }

    public void shrink() {
        this.diameter /= 2;
        this.height /= 2;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getDiameter() {
        return diameter;
    }

    public void setDiameter(float diameter) {
        this.diameter = diameter;
    }
}
