package com.xmw.delegate;

/**
 * 委派模式, 执行标准是一样的, 但是执行结果是不一样的
 *
 * @author xmw.
 * @date 2018/3/9 22:56.
 */
public interface IExecutor {

    // 普通员工执行任务
    // 在公司中, 员工执行任务
    // 规定在一周之内必须完成
    void doing();
}
