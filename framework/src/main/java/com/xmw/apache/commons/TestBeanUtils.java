package com.xmw.apache.commons;

import com.google.common.collect.Maps;
import lombok.Data;
import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * apache commons util
 * http://zhoualine.iteye.com/blog/1770014
 * @author xmw.
 * @date 2018/11/4 10:36 PM.
 */
public class TestBeanUtils {

    public static void main(String[] args) throws Exception {
        Person person = new Person();
        person.setName("tom");
        person.setAge(21);
        //克隆
        Person person2 =  (Person) BeanUtils.cloneBean(person);
        System.out.println(person2.getName() + " >> " + person2.getAge());

        // 原理也是通过Java的反射机制来做的。
        // 2、 将一个Map对象转化为一个Bean
        // 这个Map对象的key必须与Bean的属性相对应。
        Map<String, String> map = Maps.newHashMap();
        map.put("name","tom");
        map.put("email","tom@");
        map.put("age","21");
        //将map转化为一个Person对象
        person = new Person();
        BeanUtils.populate(person,map);
        System.out.println(person);
        // 通过上面的一行代码，此时person的属性就已经具有了上面所赋的值了。
        // 将一个Bean转化为一个Map对象了，如下：
        map = BeanUtils.describe(person);
        System.out.println(map);
    }

    @Data
    public static class Person {
        private String name = "";
        private String email = "";
        private Integer age;
    }
}
