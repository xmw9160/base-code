package com.xmw.concurrent;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Date 2018/1/24.
 * Author xmw .
 */
public class AQSCase extends AbstractQueuedSynchronizer {
    @Override
    protected boolean tryAcquire(int arg) {
        return super.tryAcquire(arg);
    }
}
