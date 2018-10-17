package com.xmw.factory.simple;

/**
 * 对这个工厂来说(太强大了?)
 * 为什么?
 * 这个工厂撒都能干, 产bmw, benz, audi(不符合显示)
 * 编码也是一种艺术(融会贯通), 艺术来源于生活, 回归到生活
 *
 * @author xmw.
 * @date 2018/3/7 23:08.
 */
public class SimpleFactory {

    // 实现统一管理, 专业化管理
    // 如果没有工厂模式, 小作坊, 没有执行标准
    // 如果买到上午产品(没有标准)
    // 卫生监督局工作难度会大大减轻

    // 中国制造(按人家的标准执行)
    // 中国制造向中国创造改变(思维能力)
    // 码农是执行标准的人
    // 架构师是制定标准的人

    // 不止做一个技术者, 更要做一个思考者
    public Car getCar(String name) {
        if ("bmw".equalsIgnoreCase(name)) {
            return new Bmw();
        }
        if ("audi".equalsIgnoreCase(name)) {
            return new Audi();
        }
        if ("benz".equalsIgnoreCase(name)) {
            return new Benz();
        }
        System.out.println("没有这个产品!!!");
        return null;
    }
}
