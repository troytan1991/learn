package com.troytan.learn.support;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 单例类
 *
 * @Author troytan
 * @Date 2/15/2020
 */
public class Singleton {
    //记录构造方法调用次数
    public static AtomicInteger count = new AtomicInteger(0);
    //测试对象能否正常使用
    public String name = "troy";

    public Singleton() {
        count.incrementAndGet();
    }
}

