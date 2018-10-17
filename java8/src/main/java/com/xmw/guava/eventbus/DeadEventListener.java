package com.xmw.guava.eventbus;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

/**
 * Date 2018/1/28.
 * Author xmw .
 */
public class DeadEventListener {

    // 如果EventBus发送的消息都不是订阅者关心的称之为Dead Event。

    boolean isDelivered = true;

    @Subscribe
    public void listen(DeadEvent event) {
        isDelivered = false;
        System.out.println(event.getSource().getClass() + "  " + event.getEvent()); //source通常是EventBus
    }

    public boolean isDelivered() {
        return isDelivered;
    }
}
