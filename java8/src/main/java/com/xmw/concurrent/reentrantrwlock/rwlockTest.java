package com.xmw.concurrent.reentrantrwlock;

/**
 * Date 2018/2/27.
 * Author xmw .
 */
public class rwlockTest {
    public static void main(String[] args) {
        PricesInfo pricesInfo = new PricesInfo();

        Reader[] readers = new Reader[5];
        Thread[] readerThread = new Thread[5];
        for (int i = 0; i < 5; i++) {
            readers[i] = new Reader(pricesInfo);
            readerThread[i] = new Thread(readers[i]);
        }

        Writer writer = new Writer(pricesInfo);
        Thread threadWriter = new Thread(writer);

        for (int i = 0; i < 5; i++) {
            readerThread[i].start();
        }
        threadWriter.start();
    }
}
