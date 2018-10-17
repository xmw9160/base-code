package com.xmw.factory.func;

import com.xmw.factory.simple.Car;

/**
 * 工厂接口, 定义了所有工厂的执行标准
 *
 * @author xmw.
 * @date 2018/3/9 21:36.
 */
public interface Factory {

    // 服务汽车上路标准
    // 尾气排放标准
    // 安全标准...
    Car getCar();
}
