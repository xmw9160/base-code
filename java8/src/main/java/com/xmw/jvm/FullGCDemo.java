package com.xmw.jvm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

    private static class FutureContract {

        FutureContract() {
        }

        FutureContract(Integer value, String member, String member1) {
            this.value = value;
            this.member = member;
            this.member1 = member1;
        }

        // 16个BigDecimal类型属性
        private BigDecimal b1;
        private BigDecimal b2;
        private BigDecimal b3;
        private BigDecimal b4;
        private BigDecimal b5;
        private BigDecimal b6;
        private BigDecimal b7;
        private BigDecimal b8;
        private BigDecimal b9;
        private BigDecimal b10;
        private BigDecimal b11;
        private BigDecimal b12;
        private BigDecimal b13;
        private BigDecimal b14;
        private BigDecimal b15;
        private BigDecimal b16;
        // 3个Long类型属性
        private Long l1;
        private Long l2;
        private Long l3;
        // 3个String类型属性
        private String member;
        private String member1;
        private String member2;
        // 4个Integer类型属性
        private Integer value;
        private Integer value1;
        private Integer value2;
        private Integer value3;
        // 2个Date类型属性
        private Date birthday;
        private Date birthday1;

        public BigDecimal getB1() {
            return b1;
        }

        public void setB1(BigDecimal b1) {
            this.b1 = b1;
        }

        public BigDecimal getB2() {
            return b2;
        }

        public void setB2(BigDecimal b2) {
            this.b2 = b2;
        }

        public BigDecimal getB3() {
            return b3;
        }

        public void setB3(BigDecimal b3) {
            this.b3 = b3;
        }

        public BigDecimal getB4() {
            return b4;
        }

        public void setB4(BigDecimal b4) {
            this.b4 = b4;
        }

        public BigDecimal getB5() {
            return b5;
        }

        public void setB5(BigDecimal b5) {
            this.b5 = b5;
        }

        public BigDecimal getB6() {
            return b6;
        }

        public void setB6(BigDecimal b6) {
            this.b6 = b6;
        }

        public BigDecimal getB7() {
            return b7;
        }

        public void setB7(BigDecimal b7) {
            this.b7 = b7;
        }

        public BigDecimal getB8() {
            return b8;
        }

        public void setB8(BigDecimal b8) {
            this.b8 = b8;
        }

        public BigDecimal getB9() {
            return b9;
        }

        public void setB9(BigDecimal b9) {
            this.b9 = b9;
        }

        public BigDecimal getB10() {
            return b10;
        }

        public void setB10(BigDecimal b10) {
            this.b10 = b10;
        }

        public BigDecimal getB11() {
            return b11;
        }

        public void setB11(BigDecimal b11) {
            this.b11 = b11;
        }

        public BigDecimal getB12() {
            return b12;
        }

        public void setB12(BigDecimal b12) {
            this.b12 = b12;
        }

        public BigDecimal getB13() {
            return b13;
        }

        public void setB13(BigDecimal b13) {
            this.b13 = b13;
        }

        public BigDecimal getB14() {
            return b14;
        }

        public void setB14(BigDecimal b14) {
            this.b14 = b14;
        }

        public BigDecimal getB15() {
            return b15;
        }

        public void setB15(BigDecimal b15) {
            this.b15 = b15;
        }

        public BigDecimal getB16() {
            return b16;
        }

        public void setB16(BigDecimal b16) {
            this.b16 = b16;
        }

        public Long getL1() {
            return l1;
        }

        public void setL1(Long l1) {
            this.l1 = l1;
        }

        public Long getL2() {
            return l2;
        }

        public void setL2(Long l2) {
            this.l2 = l2;
        }

        public Long getL3() {
            return l3;
        }

        public void setL3(Long l3) {
            this.l3 = l3;
        }

        public String getMember() {
            return member;
        }

        public void setMember(String member) {
            this.member = member;
        }

        public String getMember1() {
            return member1;
        }

        public void setMember1(String member1) {
            this.member1 = member1;
        }

        public String getMember2() {
            return member2;
        }

        public void setMember2(String member2) {
            this.member2 = member2;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public Integer getValue1() {
            return value1;
        }

        public void setValue1(Integer value1) {
            this.value1 = value1;
        }

        public Integer getValue2() {
            return value2;
        }

        public void setValue2(Integer value2) {
            this.value2 = value2;
        }

        public Integer getValue3() {
            return value3;
        }

        public void setValue3(Integer value3) {
            this.value3 = value3;
        }

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }

        public Date getBirthday1() {
            return birthday1;
        }

        public void setBirthday1(Date birthday1) {
            this.birthday1 = birthday1;
        }
    }
}
