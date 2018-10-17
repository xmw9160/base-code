package com.xmw.prototype.simple;

/**
 * @author xmw.
 * @date 2018/3/10 21:25.
 */
public class CloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {

        ConCreatePrototype cp = new ConCreatePrototype();
        cp.setAge(18);
        cp.setName("谢谢");
        cp.list.add("abc");
        cp.list.add("def");
        ConCreatePrototype copy = (ConCreatePrototype) cp.clone();
        System.out.println("年龄: " + copy.getAge());
        System.out.println("名称: " + copy.getName());
        System.out.println("list size: " + copy.list.size());
        System.out.println("cp.list == copy.list :" + (cp.list == copy.list));
//        System.out.println(cp == copy);

        // 就是一个现成的对象, 这个对象里面有已经设置好的值
        // 当需要新建一个对象的时候, 并且要给新建的对象复制内容跟以前的一模一样
        // 用循环, 用反射, 可行但效率不高
        // 字节码复制 newInstance()

        // 能够直接拷贝其实际内容的数据类型/只支持9种, 8种基本数据类型 + String
    }
}
