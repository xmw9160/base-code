package com.xmw.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * full gc
 * https://mp.weixin.qq.com/s/IuhoC5XezyWs3qNPz3ppRA
 *
 * @author xmw
 * @date 2018/11/14 11:14 PM
 * @since V1.0
 */
public class FullGCDemo {
    // -Xmx256m -Xms256m -Xmn64m

    private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(50,
            new ThreadPoolExecutor.DiscardOldestPolicy());

    public static void main(String[] args) throws Exception {
        executor.setMaximumPoolSize(50);

        // 模拟xxl-job 100ms 调用一次, 原代码没有这么频繁
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            buildBar();
            Thread.sleep(100);
        }
    }

    private static void buildBar() {
        List<FutureContract> futureContractList = getAllFutureContract();
        futureContractList.forEach(contract -> {
            // do something
            executor.scheduleWithFixedDelay(() -> {
                try {
                    doFutureContract(contract);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, 2, 3, TimeUnit.SECONDS);
        });
    }

    private static void doFutureContract(FutureContract contract) {
        // do something with futureContract
    }

    private static List<FutureContract> getAllFutureContract() {
        List<FutureContract> futureContractList = new ArrayList<>();
        // 问题代码这里每次只会new不到10个对象, 我这里new了100个是为了更快重现问题
        for (int i = 0; i < 100; i++) {
            FutureContract contract = new FutureContract(i, "135" + i, "成都" + i);
            futureContractList.add(contract);
        }
        return futureContractList;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class FutureContract {
        // 16个BigDecimal类型属性
        // 3个Long类型属性
        // 3个String类型属性
        // 4个Integer类型属性
        // 2个Date类型属性
        private Integer value;
        private String member;
        private String address;
    }
}
