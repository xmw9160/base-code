package com.xmw.delegate;

/**
 * 想法, 用代码来描述这种想法
 * 员工a
 *
 * @author xmw.
 * @date 2018/3/9 22:58.
 */
public class ExecutorA implements IExecutor {
    @Override
    public void doing() {
        System.out.println("员工A干活了...");
    }
}
