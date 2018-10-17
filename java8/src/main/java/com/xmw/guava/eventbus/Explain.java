package com.xmw.guava.eventbus;

/**
 * Date 2018/1/28.
 * Author xmw .
 */
public class Explain {
/*
 * Guava为我们提供了事件总线EventBus库，它是事件发布-订阅模式的实现，
 * 让我们能在领域驱动设计(DDD)中以事件的弱引用本质对我们的模块和领域边界很好的解耦设计。
 Guava为我们提供了同步事件EventBus和异步实现AsyncEventBus两个事件总线，
 他们都不是单例的。

 Guava发布的事件默认不会处理线程安全的，
 但我们可以标注@AllowConcurrentEvents来保证其线程安全

 如果Listener A监听Event A, 而Event A有一个子类Event B,
 此时Listener A将同时接收Event A和B消息
 */
}
