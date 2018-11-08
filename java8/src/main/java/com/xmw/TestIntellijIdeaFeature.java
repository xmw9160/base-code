package com.xmw;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * @author mingwei.xia
 * @date 2018/11/8 9:17
 * @since V1.0
 */
public class TestIntellijIdeaFeature {

    @Test
    public void test2018point1() {
        // 1. stream代码自动生成更智能
        List<Object> list = Lists.newArrayList();
        list.stream()
                .filter(String.class::isInstance)
                .map(s -> ((String) s).trim())
                .collect(Collectors.toSet());

        //2. while循环优化
//        int i = 0;
//        while (true) {
//            System.out.println("i=" + i);
//            i++;
//            if (i == 10) break;
//        }
        // ->
//        do {
//            System.out.println("i=" + i);
//            i++;
//        } while (i != 10);

        //3. 优化多余的资源关闭操作
        // 使用过IDEA的同学可能会经常看到代码里面有灰色的代码，这就提示你，这段代码是多余了，不可达的代码，可以删掉，代码更干净整洁

        //4. 字符串数组自动排序
//        String[] cats = {"CoCo", "Able", "Lucy"};
        // ->
//        String[] cats = {"Able", "CoCo", "Lucy"};

        //6. postfix支持自定义模板
//        10.for

        //7. 自定义类生成文件名前后缀

        //8. debug异常模拟
        try {
            System.out.println("Hello");
            // throw new IllegalArgumentException("test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
