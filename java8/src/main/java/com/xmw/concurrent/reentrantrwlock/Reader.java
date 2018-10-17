package com.xmw.concurrent.reentrantrwlock;

/**
 * Date 2018/2/27.
 * Author xmw .
 */
public class Reader implements Runnable {

    private PricesInfo pricesInfo;

    public Reader(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "--Price-1: " + pricesInfo.getPrice1());
            System.out.println(Thread.currentThread().getName() + "--Price-2: " + pricesInfo.getPrice2());
            System.out.println("----------------------");
        }
    }

}
