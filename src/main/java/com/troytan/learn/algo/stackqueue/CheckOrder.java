package com.troytan.learn.algo.stackqueue;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class CheckOrder {

    /**
     * inputs为入栈顺序，元素可以在栈内停留，也可以直接弹出
     * 检查outputs是否是合法的输出
     *
     * @param inputs
     * @param outputs
     * @return
     */
    public boolean checkOrder(int[] inputs, int[] outputs) {
        Stack<Integer> inStack = new Stack<>();
        //结果人队列
        Queue<Integer> outQueue = new ArrayDeque<>();
        for (int out : outputs) {
            outQueue.add(out);
        }
        for (int in : inputs) {
            //输入按顺序入栈
            inStack.push(in);
            while (!inStack.isEmpty() && inStack.peek() == outQueue.peek()) {
                //当栈顶与队列头部相等，同时弹出
                inStack.pop();
                outQueue.poll();
            }
        }

        return outQueue.isEmpty();
    }

}
