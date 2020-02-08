package com.troytan.learn.algo.stackqueue;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 使用queue实现stack：存储数据时进行顺序交换
 *
 * @Author troytan
 * @Date 2/7/2020
 */
public class QueueStack<T> {
    //数据队列存储stack的顺序
    private Queue<T> dataQ = new ArrayDeque<>();
    //临时队列作为后进先出的关系转换
    private Queue<T> tempQ = new ArrayDeque<>();

    public T pop() {
        return dataQ.poll();
    }

    public T top() {
        return dataQ.peek();
    }

    public void push(T t) {
        Queue<T> temp = dataQ;
        tempQ.add(t);
        while (!dataQ.isEmpty()) {
            tempQ.add(dataQ.poll());
        }
        dataQ = tempQ;
        tempQ = temp;
    }

    public boolean isEmpty() {
        return dataQ.isEmpty();
    }
}
