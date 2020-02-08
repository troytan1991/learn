package com.troytan.learn.algo.stackqueue;

import java.util.Stack;

/**
 * 栈实现队列：利用临时栈，将新加的元素置于数据栈底
 *
 * @Author troytan
 * @Date 2/7/2020
 */
public class StackQueue<T> {

    private Stack<T> tempS = new Stack<>();
    private Stack<T> dataS = new Stack<>();

    public void add(T t) {
        while (!dataS.isEmpty()) {
            tempS.add(dataS.pop());
        }
        tempS.add(t);
        while (!tempS.isEmpty()) {
            dataS.add(tempS.pop());
        }
    }

    public T poll() {
        return dataS.pop();
    }

    public boolean isEmpty() {
        return tempS.isEmpty() && dataS.isEmpty();
    }

    public T peek() {
        return dataS.peek();
    }

}
