package com.troytan.learn.concurrent;

import org.junit.Assert;
import org.junit.Test;

public class VolatileTest {

    private MyVolatile myVolatile = new MyVolatile();
    private MyNonVolatile nonVolatile = new MyNonVolatile();

    @Test
    public void test01() throws InterruptedException {
        //t1正常拿到t2的赋值，退出死循环
        myVolatile.visible01();
    }

    @Test
    public void test02() throws InterruptedException {
        //t1进入死循环，一直获取不到t2的赋值
        nonVolatile.visible01();
    }

    @Test
    public void test04() throws InterruptedException {
        //测试非volatile变量，被两个线程交替操作的结果
        //t1先操作变量，并不断成功写回主存
        // t2在某个时刻取得值并赋值为10，但此时主存值已被t1修改，写回失败
        nonVolatile.visible02();
    }

    @Test
    public void test05() throws InterruptedException {
        //volatile禁止指令重排序，结果中不会出现00
        Assert.assertTrue(!myVolatile.instructOrder().contains("00"));
    }


    @Test
    public void test06() throws InterruptedException {
        //指令重排序有一定概率，该断言可能失败，多试几次会成功即可
        Assert.assertTrue(nonVolatile.instructOrder().contains("00"));
    }
}
