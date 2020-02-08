package com.troytan.learn.algo.stackqueue;

import java.util.Stack;
import java.util.function.Function;

/**
 * 实现O(1)复杂度取最小值的栈
 *
 * @Author troytan
 * @Date 2/7/2020
 */
public class MinStack<T extends Comparable<T>> {
    //数据栈
    private Stack<T> stack = new Stack<>();
    //最小值栈，维护一个深度与数据栈一样的栈，存储每个数据状态的最小值
    private Stack<T> minStack = new Stack<>();


    public void push(T t) {
        stack.push(t);
        if (minStack.isEmpty() || minStack.peek().compareTo(t) > 0) {
            //当push进来的值小于最小值栈顶，则将t也如minStack
            minStack.push(t);
        } else {
            //否则，最小值依然是前一个状态的最小值
            minStack.push(minStack.peek());
        }
    }

    public T pop() {
        minStack.pop();
        return stack.pop();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public T getMin() {
        return minStack.isEmpty() ? null : minStack.peek();
    }
}
