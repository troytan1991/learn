package com.troytan.learn.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 单例模式的双重检测：
 * 1）直接对getSingleton方法加synchronized修饰，可以保证线程安全，但每次只允许一个线程调用，会降低效率
 * 2）在创建对象的代码块上加synchronized，只会在第一次创建时调用，提高效率
 * 3）第二层校验，防止多个线程同时等待进入同步块，第一个线程完成实例创建，后续线程再校验一次，防止重复创建
 * 4) volatile修饰singleton原因，两种解释（网上流传第一种，未证实）
 * ★ 1）对象创建三个步骤:b,c重排序会导致对象还未创建完成，就返回给其它线程使用（单线程使用前一定会完成b,c,重排不会造成影响）
 * * * a)memory=allocate(); 分配对象空间
 * * * b)ctorInstance(memory); 空间上创建对象
 * * * c)instance=memory; instance指向空间地址
 * * 2) 变量可见性，由于线程进入同步块都会重新从主存获取值，此处未有体现
 *
 * @Author troytan
 * @Date 2/4/2020
 */
public class DoubleCheck {
    private static volatile Singleton singleton;
//    private static Singleton singleton;

    /**
     * 获取单例对象
     *
     * @return
     */
    public static Singleton getSingleton() {
        if (singleton == null) {
            synchronized (DoubleCheck.class) {
                //第二层校验，防止多个线程等待进入同步块，第一个线程完成实例创建，后续线程再校验一次，以防重复创建
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) throws InterruptedException {
        //测试1000次，
        for (int i = 0; i < 1000; i++) {
            List<Thread> list = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                //测试3个线程同时获取单例对象
                Thread t = new Thread(() -> {
                    //测试获取的实例能否正常使用，即测试volatile禁止创建对象三个步骤的重排序
                    //若发生重排序（概率较小），即先返回对象的指针，再在空间上创建对象，会造成使用的异常（此处还未测试出异常）
                    System.out.println(getSingleton().name);
                });
                t.start();
                list.add(t);
            }
            //等待子线程结束
            for (Thread thread : list) {
                thread.join();
            }
            //清空单例，以便下一个循环测试
            singleton = null;
        }
        //创建对象的次数与循环测试次数相同，则getSingleton()执行正常，没有多余创建对象
        System.out.println(Singleton.count.get());
    }
}

class Singleton {
    //记录构造方法调用次数
    public static AtomicInteger count = new AtomicInteger(0);
    //测试对象能否正常使用
    public String name = "troy";

    public Singleton() {
        count.incrementAndGet();
    }
}
