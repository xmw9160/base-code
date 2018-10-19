package com.xmw.guava.eventbus;

/**
 * Date 2018/1/28.
 * Author xmw .
 */
//Guava 发布-订阅模式中传递的事件,是一个普通的POJO类
public class OrderEvent {  //事件
    private String message;

    public OrderEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
