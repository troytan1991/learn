package com.troytan.learn.concurrent;

import java.util.*;
import java.util.concurrent.*;

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

    private Random random = new Random();

    /**
     * Thread.join()方式：
     * 1）主线程通过t.join（内部t.wait(0)实现），阻塞进入等待子线程对象t
     * 2）子线程执行完run方法，自动调用线程对象的notifyAll方法，唤醒主线程
     * 3）主线程继续等待下一线程执行完
     *
     * @return
     * @throws InterruptedException
     */
    public Vector<String> waitWithJoin() throws InterruptedException {
        Vector<String> result = new Vector<>();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(() -> {
                String name = Thread.currentThread().getName();
                result.add(name);
                try {
                    Thread.sleep(random.nextInt(5000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程" + name + "执行完成");
            }, "t" + i);
            t.start();
            threads.add(t);
        }
        for (Thread t : threads) {
            //1）若t已经结束（isAlive()=false）直接返回
            //2）否则进入t.wait(), 线程t在执行完run方法后，会自行调用t.notify()，主线程会被唤醒
            t.join();
        }
        return result;
    }

    /**
     * FutureTask方式
     * 1）生成FutureTask，新建线程执行task
     * 2）futureTask.get()阻塞获取结果
     *
     * @return
     */
    public Vector<String> waitWithFutureTask() throws ExecutionException, InterruptedException {
        Vector<String> result = new Vector<>();
        List<FutureTask<String>> tasks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            FutureTask<String> ft = new FutureTask<>(() -> Thread.currentThread().getName());
            new Thread(ft, "t" + i).start();
            tasks.add(ft);
        }
        for (FutureTask<String> ft : tasks) {
            //阻塞直到线程执行task完成
            result.add(ft.get());
        }
        return result;
    }

    /**
     * CountDownLatch方式
     * 1）每个线程执行完后，latch.countDown()
     * 2）主线程latch.await(),阻塞等待latch为0
     *
     * @return
     * @throws InterruptedException
     */
    public Vector<String> waitWithLatch() throws InterruptedException {
        Vector<String> result = new Vector<>();
        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                result.add(Thread.currentThread().getName());
                //latch减1
                latch.countDown();
            }, "t" + i).start();
        }
        //阻塞等待直到latch为0
        latch.await();

        return result;
    }

    /**
     * CyclicBarrier方式：
     * 1）所有线程执行完后，进入await等待
     * 2）当barrier的await数量达到预定值，所有线程同时唤醒
     *
     * @return
     * @throws BrokenBarrierException
     * @throws InterruptedException
     */
    public Vector<String> waitWithCyclicBarrier() throws BrokenBarrierException, InterruptedException {
        Vector<String> result = new Vector<>();
        CyclicBarrier barrier = new CyclicBarrier(6);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                result.add(Thread.currentThread().getName());
                try {
                    //barrier等待的数量加1
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "t" + i).start();
        }
        //barrier等待数量为6时，主线程和子线程同时唤醒
        barrier.await();
        return result;
    }

    /**
     * Semaphore信号量方式
     * 1）每个线程执行完后放入一个令牌
     * 2）主线程等待一次性获取5个令牌
     *
     * @return
     * @throws InterruptedException
     */
    public Vector<String> waitWithSemaphore() throws InterruptedException {
        Vector<String> result = new Vector<>();
        //初始化令牌池为0
        Semaphore semaphore = new Semaphore(0);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                result.add(Thread.currentThread().getName());
                //信号量加1
                semaphore.release();
            }, "t" + i).start();
        }
        //阻塞等待信号量为5
        semaphore.acquire(5);
        return result;
    }
}
