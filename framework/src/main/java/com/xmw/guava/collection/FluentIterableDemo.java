package com.xmw.guava.collection;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;

/**
 * FluentIterable
 *
 * @author mingwei.xia
 * @date 2018/10/25 11:34
 * @since V1.0
 */
public class FluentIterableDemo {

    public static void main(String[] args) {
        Girl girl1 = new Girl(17, "nice");
        Girl girl2 = new Girl(18, "not so nice");
        Girl girl3 = new Girl(19, "nice");
        Girl girl4 = new Girl(20, "not so nice");
        //这里使用Lists.newArrayList添加对象，避免多次调用list.add方法，下篇会有说明
        List<Girl> girls = Lists.newArrayList(girl1, girl2, girl3, girl4);
        /**
         * from方法：用于包装Iterable接口，返回FluentIterable实例
         * filter方法：用于过滤集合中元素，返回过滤后的集合
         */
        Iterable<Girl> iterable = FluentIterable.from(girls).filter(input -> {
            //过滤相貌nice的Girl对象
            return "nice".equals(input.getFace());
        });
        for (Girl girl : iterable) {
            /**打印结果：
             17
             19
             */
            System.out.println(girl.getAge());
        }
        /**
         * transform方法：用于根据指定Function转换集合元素，返回转换后的集合
         */
        Iterable<String> fluentIterable = FluentIterable.from(girls).transform(input -> {
            //Joiner类对相应的元素内容进行拼接处理
            return Joiner.on("，").join(input.getAge(), input.getFace());
        });
        for (String girl : fluentIterable) {
            /** 打印结果：
             17，nice
             18，not so nice
             19，nice
             20，not so nice
             */
            System.out.println(girl);
        }
    }

    //测试用Girl对象
    static class Girl {
        int age;
        String face;

        Girl(int age, String face) {
            this.age = age;
            this.face = face;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }
    }
}
