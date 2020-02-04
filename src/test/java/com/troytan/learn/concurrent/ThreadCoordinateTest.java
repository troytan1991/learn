package com.troytan.learn.concurrent;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ExecutionException;

/**
 * 线程协同测试
 *
 * @Author troytan
 * @Date 2/4/2020
 */
public class ThreadCoordinateTest {

    private ThreadCoordinate tc = new ThreadCoordinate();

    @Test
    public void test01() throws InterruptedException {
        assert tc.waitWithLatch().size() == 5;
    }

    @Test
    public void test02() throws ExecutionException, InterruptedException {
        assert tc.waitWithFutureTask().size() == 5;
    }

    @Test
    public void test03() throws BrokenBarrierException, InterruptedException {
        assert tc.waitWithCyclicBarrier().size() == 5;
    }

    @Test
    public void test04() throws InterruptedException {
        assert tc.waitWithSemaphore().size() == 5;
    }

    @Test
    public void test05() throws InterruptedException {
        assert tc.waitWithJoin().size() == 5;
    }
}
