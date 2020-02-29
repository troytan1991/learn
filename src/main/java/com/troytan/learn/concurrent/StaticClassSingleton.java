package com.troytan.learn.concurrent;

/**
 * 以静态内部形式实现单例模式
 * <p>
 * 1)MySingleton类加载时，不会加载静态内部类
 * 2)调用静态内部类的属性时，触发内部类的加载，进一步触发静态内部类静态属性的初始化
 * 3)利用虚拟机，同一个类只会被初始化一次（在多线程下会同步阻塞），保证实例初始化一次
 *
 * @Author troytan
 * @Date 2/15/2020
 */
public class StaticClassSingleton {

    //私有化构造方法
    private StaticClassSingleton() {
    }

    public static StaticClassSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static StaticClassSingleton INSTANCE = new StaticClassSingleton();
    }

}
