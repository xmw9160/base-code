package com.xmw.delegate;

/**
 * @author xmw.
 * @date 2018/3/9 23:04.
 */
public class DispatcherTest {

    public static void main(String[] args) {
        // 看上去好像是项目经理在搞活
        // 但是实际干活的是普通员工
        // 这是典型的, 干活是我的, 功劳是你的.
        Dispatcher dispatcher = new Dispatcher(new ExecutorA());
        dispatcher.doing();
    }
}
