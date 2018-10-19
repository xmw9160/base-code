package com.xmw.guava.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * Date 2018/1/28.
 * Author xmw .
 */
public class MultiEventListener {  //多个订阅者

    @Subscribe
    public void listen(OrderEvent event) {
        System.out.println("MultiEventListener receive msg: " + event.getMessage());
    }

    @Subscribe
    public void listen(String message) {
        System.out.println("MultiEventListener receive msg: " + message);
    }
}
