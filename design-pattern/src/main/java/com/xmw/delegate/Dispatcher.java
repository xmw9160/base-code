package com.xmw.delegate;

/**
 * 项目经理, 虽然也有执行方法
 * 但是他的工作职责是不一样的
 *
 * @author xmw.
 * @date 2018/3/9 23:01.
 */
public class Dispatcher implements IExecutor {

    private IExecutor executor;

    Dispatcher(IExecutor executor) {
        this.executor = executor;
    }

    @Override
    public void doing() {
        this.executor.doing();
    }
}
