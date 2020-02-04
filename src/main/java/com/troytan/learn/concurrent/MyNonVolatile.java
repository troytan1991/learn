package com.troytan.learn.concurrent;

import java.util.HashSet;
import java.util.Set;

/**
 * 与MyVolatile的对比类，测试未添加volatile的结果
 *
 * @Author troytan
 * @Date 2/4/2020
 */
public class MyNonVolatile {
    //lambda表达式只能操作静态的成员变量
    private static boolean flag = false;
    private static long count = 1;
    /**
     * 指令重排序：数据操作之间没有依赖性，cpu会对其进行重排序
     * 1）对同一个变量的读写操作，为依赖操作（对同一变量两次读操作不是依赖操作）
     * 2）单线程不会出现指令重排序
     */
    private static int a = 0, b = 0, x = 0, y = 0;

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
        t1.join(2000);
        t2.join();
        t3.join();
    }

    /**
     * 1）t1对count的操作偶尔会写会主存，但不会从主存中值主动刷新count值
     * 2）线程sleep后，会重新从主存取值
     * 3) 线程写回主存时，若发现主存已被修改，则会丢弃当前值
     *
     * @throws InterruptedException
     */
    public void visible02() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (count == 10) {
                    //由于t1线程在不定时间更新了主存，导致t2赋值后，一直未成功写回主存10，此处始终不会进入
                    System.out.println("t1读取到10");
                    break;
                }
                //操作完数据，会不定期写回主存，此处会一直成功
                count += 10;
            }
        });
        Thread t2 = new Thread(() -> {
            while (true) {
                //当t2写回count=10时(失败)，从主存重新读取
                //当t2未触发写回操作，则从工作内存读取（10）
                System.out.println("t2读取到count:" + count);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //休眠后始终从主存读取
                System.out.println("t2休眠后读到count:" + count);
                //此处赋值到工作内存，但写回主存发现count值已被t1修改，则丢弃10
                count = 10;
            }
        });
        t1.start();
        t2.start();
        t1.join(3000);
        t2.join(3000);
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

