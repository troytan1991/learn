package com.troytan.learn.concurrent;

/**
 * 线程状态流转，见Thread.State
 * 1) NEW： new Thread()的初始化状态
 * 2）RUNNABLE: Thread.start()后的状态（分配到到cpu为running, 等待cpu资源为ready）
 * 3）BLOCKED: 当前线程进入synchronized同步块，等待获取锁时的状态
 * 4）WAITING: 等待另一个线程的唤醒操作，
 * * * 1）Object.wait() --> 对应唤醒线程的Object.notify()、notifyAll()操作
 * * * 2）Thread.join() --> 对应唤醒线程的terminate
 * * * 3) LockSupport.park() --> 对应LockSupport.unpark()
 * 5) TIMED_WAITING: 带有时间的等待状态
 * * * 1) Object.wait(time)
 * * * 2) Thread.join(time)
 * * * 3) LockSupport.parkNanos(time)
 * * * 4) LockSupport.parkUtil(time)
 * * * 5）Thread.sleep(time)
 * 6) TERMINATED: 结束状态,run方法运行结束, isAlive()返回false
 *
 * @Author troytan
 * @Date 2/3/2020
 */
public class ThreadStatus {

    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        Thread t1 = new Thread(() -> {
            System.out.println("t1 start");
            System.out.println("t1 sleep for 1000");
            try {
                //线程sleep，进入TIME_WAITING状态
                Thread.sleep(1000);
                System.out.println("t1 sleep completed");
                synchronized (object) {
                    //通过synchronized才能拿到object的monitor，
                    System.out.println("t1 enter first synchronized block");
                    System.out.println("t1 notify object");
                    //持有monitor的线程才能调用对象的notify，唤醒t2，释放lock
                    object.notify();
                }
                System.out.println("t1 wait for lock");
                //lock被t2持有，等待lock时，进入BLOCK状态
                synchronized (object) {
                    System.out.println("t1 get lock");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1 end");
        });
        Thread t2 = new Thread(() -> {
            System.out.println("t2 start");
            //此时t1在休眠，t2直接获取到lock
            synchronized (object) {
                try {
                    System.out.println("t2 wait for t1, release lock");
                    //持有对象lock的线程才能调用wait(), 释放lock,等待被t1唤醒
                    object.wait();
                    //t2被唤醒,再次持有lock
                    System.out.println("t2 wake up, get lock");
                    System.out.println("t2 sleep 1000");
                    //t2休眠一段时间，此时t1处于等待lock阶段
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //t2进入死循环，等待interrupt中断
            while (!Thread.currentThread().isInterrupted()) {
            }
            try {
                //t2被中断后，此时标志未还在，调用sleep会直接抛出异常，并清除标志位
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2 end");
        });
        System.out.println("after new:");
        System.out.printf("t1 state:%s,isAlive:%s,isInterrupt:%s\n", t1.getState(), t1.isAlive(), t1.isInterrupted());

        t1.start();
        System.out.printf("t1 state:%s,isAlive:%s,isInterrupt:%s\n", t1.getState(), t1.isAlive(), t1.isInterrupted());

        //主线程休眠，确保t1进入sleep阶段
        Thread.sleep(500);
        System.out.printf("state:%s,isAlive:%s,isInterrupt:%s\n", t1.getState(), t1.isAlive(), t1.isInterrupted());

        //t2启动，主线程休眠100，确保t2进入wait
        t2.start();
        Thread.sleep(100);
        System.out.printf("t2 state:%s,isAlive:%s,isInterrupt:%s\n", t2.getState(), t2.isAlive(), t2.isInterrupted());

        //主线程休眠，确保t1进入第二个synchronized块，等待t2释放锁
        Thread.sleep(1000);
        System.out.printf("t1 state:%s,isAlive:%s,isInterrupt:%s\n", t1.getState(), t1.isAlive(), t1.isInterrupted());


        //主线程休眠，确保t2进入死循环，调用interrupt
        Thread.sleep(500);
        //interrupt()方法
        // 1）在WAITING, TIME_WAITING状态的线程，会收到一个InterruptedException，并且会清除中断标志
        // 2）正常运行的线程，不会有任何影响，仅仅中断标志为置为true
        // 3)当线程正常运行结束时，该标志位还会被清除
        t2.interrupt();
        //t2还在运行中，中断标志位未清除
        System.out.printf("t2 state:%s,isAlive:%s,isInterrupt:%s\n", t2.getState(), t2.isAlive(), t2.isInterrupted());

        //主线程休眠，确保两个线程执行完成
        Thread.sleep(500);
        //isInterrupted()单纯的获取中断状态，不会清除中断标志
        //interrupted() 获取中断状态，并清除中断标志
        System.out.printf("t1 state:%s,isAlive:%s,isInterrupt:%s\n", t1.getState(), t1.isAlive(), t1.isInterrupted());
        System.out.printf("t2 state:%s,isAlive:%s,isInterrupt:%s\n", t2.getState(), t2.isAlive(), t2.isInterrupted());


    }
}

