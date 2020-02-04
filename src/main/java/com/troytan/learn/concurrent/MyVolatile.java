package com.troytan.learn.concurrent;

import java.util.HashSet;
import java.util.Set;

/**
 * volatile两大功能
 * 1）可见性，线程修改的值会立马写回主存，其它线程该值会失效并更新
 * 2）禁止指令重排序
 * * * 1）两个操作无依赖关系（如两个变量的赋值），cpu可能根据指令优化，后者先执行
 * * * 2）volatile修饰的变量操作，会作为一道门，门的两侧指令不会被优化乱序
 *
 * @Author troytan
 * @Date 2/4/2020
 */
public class MyVolatile {
    //lambda表达式只能操作静态的成员变量
    private static volatile boolean flag = false;
    private static volatile long count = 1;
    /**
     * 指令重排序：数据操作之间没有依赖性，cpu会对其进行重排序
     * 1）对同一个变量的读写操作，为依赖操作（对同一变量两次读操作不是依赖操作）
     * 2）单线程不会出现指令重排序
     */
    private static int b = 0, x = 0, y = 0;
    private static volatile int a = 0;

    /**
     * 线程t1一直获取不到t2的赋值
     * 1）t1先从主存加载flag到工作内存，再循环从工作内存use到cpu判断
     * 2）t2再从主存加载flag到工作内存，并通过cpu赋值assign回工作内存，经过不固定时间写会到主存，但并不会触发其它线程该值的失效
     * 3）t3最后读取到主存的值为true，而t1一直为false
     *
     * @throws InterruptedException
     */
    public void visible01() throws InterruptedException {
        //t1最先加载flag到工作内存
        Thread t1 = new Thread(() -> {
            System.out.println("t1 进入循环判断");
            while (!flag) {
            }
            System.out.println("t1获取到flag为true");
        });
        //t2在100ms时对flag赋值，并在一段时间后写会主存，但未触发t1的flag失效
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag = true;
            System.out.println("t2赋值flag为true");
        });
        //t3在1000ms时读取主存flag值为true，说明t2的修改已写回主存
        Thread t3 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t3获取到flag为" + flag);
        });
        t1.start();
        t2.start();
        t3.start();
        //等待子线程结束
        t1.join();
        t2.join();
        t3.join();
    }


    /**
     * 当a未被volatile修饰时，set中出现四种结果：
     * （当a为volatile时，对于a的读写操作，会禁止重排序，第四种情况不纯在）：
     * 1）01: a=1->x=b(0)->b=1->y=a(1) t1先执行完
     * 2) 10: b=1->y=a(0)->a=1->x=b(1) t2先执行完
     * 3) 11: a=1->b=1->x=b->y=a       t1、t2交替执行
     * ★ 4）00: x=b(0)->b=1->y=a->a=1    t1两赋值操作重排序，先执行了x=b,最后执行a=1
     *
     * @throws InterruptedException
     */
    public Set<String> instructOrder() throws InterruptedException {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            Thread t1 = new Thread(() -> {
                //以下两赋值操作不能保证先后执行顺序
                a = 1;
                x = b;
            });
            Thread t2 = new Thread(() -> {
                //以下两赋值操作不能保证先后执行顺序
                b = 1;
                y = a;
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            set.add(String.format("%d%d", x, y));
            //重置操作，以便下一次测试
            a = 0;
            b = 0;
            x = 0;
            y = 0;
        }
        return set;
    }
}

