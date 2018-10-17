package com.xmw.prototype.greatestage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * 孙猴子
 *
 * @author xmw.
 * @date 2018/3/10 21:55.
 */
public class TheGreatestSage extends Monkey implements Cloneable, Serializable {

    // 金箍棒
    GoldRingedStaff staff;

    // 从石头缝里蹦出来
    public TheGreatestSage() {
        this.staff = new GoldRingedStaff();
        this.birthday = new Date();
        this.height = 150;
        this.weight = 60;
    }

    // 分身技能
    @Override
    protected Object clone() throws CloneNotSupportedException {
        // 浅可能
//        return super.clone();

        // 深度克隆
        TheGreatestSage copy = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        ByteArrayInputStream bis = null;
        try {
            // 序列化
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            // 反序列化
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            copy = (TheGreatestSage) ois.readObject();
            copy.birthday = new Date();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return copy;
    }

    // 变法
    public void change() throws CloneNotSupportedException {
        TheGreatestSage copySage = (TheGreatestSage) clone();
        System.out.println("大圣本尊生日: " + this.getBirthday().getTime());
        System.out.println("大圣克隆后生日: " + copySage.birthday.getTime());
        System.out.println("大圣本身和克隆后是否为同一个对象: " + (this == copySage));
        System.out.println("大神本尊持有的金箍棒跟克隆大圣持有金箍棒是否为同一个对象: " + (this.staff == copySage.staff));
    }

    public GoldRingedStaff getStaff() {
        return staff;
    }

    public void setStaff(GoldRingedStaff staff) {
        this.staff = staff;
    }
}
