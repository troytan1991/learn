package com.troytan.learn.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * 线程协同的几种方式:
 * 1）join: wait-notify
 * 2）futureTask
 * 3）CountDownLatch
 * 4) CyclicBarrier
 * 5) Semaphore
 *
 * @Author troytan
 * @Date 2/3/2020
 */
public class ThreadCoordinate {
    private Vector<String> result = new Vector<>();
    private CountDownLatch latch = new CountDownLatch(5);

    public void waitWithJoin() throws InterruptedException {
        result.clear();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(() -> {
//                result.put(Thread.currentThread().getName(), "");
                result.add(Thread.currentThread().getName());
            }, "t" + i);
            t.start();
            threads.add(t);
        }
        for (Thread t : threads) {
            t.join();
        }
        System.out.println("------join 主线程执行结束-----");
    }

}
