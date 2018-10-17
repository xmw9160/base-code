package com.xmw.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author xmw.
 * @date 2018/3/10 21:14.
 */
public class ListTest {

    public static void main(String[] args) {

        // 策略模式
        List<Long> numbers = new ArrayList<>();

        Collections.sort(numbers, new Comparator<Long>() {

            // 返回值是固定的
            // 0, -1, 1
            @Override
            public int compare(Long o1, Long o2) {
                // 总监的逻辑是不一样的
                return 0;
            }
        });
    }
}
