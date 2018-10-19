package com.xmw.singleton;

/**
 * 使用枚举实现单例
 *
 * @author mingwei.xia
 * @date 2018/5/2 13:50
 * @since V1.0
 */
public class SingletonWithEnum {

    // 私有化构造函数
    private SingletonWithEnum() {
    }

    // 获取单例
    public static SingletonWithEnum getInstance() {
        return SingletonEnum.INSTANCE.singleton;
    }

    public enum SingletonEnum {
        INSTANCE,
        INSTANCE2;
        private SingletonWithEnum singleton;

        SingletonEnum() {
            System.out.println("初始化实例对象");
            singleton = new SingletonWithEnum();
        }

        public SingletonWithEnum getSingleton() {
            return singleton;
        }
    }
}
