package com.troytan.learn.algo.stackqueue;

import com.troytan.learn.support.ListNode;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class StackQueueTest {

    @Test
    public void test01() {
        MinStack<Integer> minStack = new MinStack<>();
        minStack.push(1);
        minStack.push(2);
        minStack.push(-5);
        assertTrue(minStack.getMin() == -5);
        minStack.pop();
        assertTrue(minStack.getMin() == 1);
        minStack.pop();
        assertTrue(minStack.getMin() == 1);
        minStack.push(6);
        assertTrue(minStack.getMin() == 1);
    }

    @Test
    public void testCheckOrder() {
        CheckOrder checkOrder = new CheckOrder();
        int[] inputs = new int[]{1, 2, 3, 4, 5};
        int[] outputs = new int[]{3, 5, 2, 4, 1};
        int[] outputs2 = new int[]{3, 2, 5, 4, 1};
        Assert.assertFalse(checkOrder.checkOrder(inputs, outputs));
        assertTrue(checkOrder.checkOrder(inputs, outputs2));
    }

    @Test
    public void testHeapMidian() {
        HeapMidian heapMidian = new HeapMidian();
        heapMidian.add(1);
        heapMidian.add(5);
        heapMidian.add(3);
        assertTrue(heapMidian.findMidian() == 3.0);
        heapMidian.add(4);
        heapMidian.add(6);
        heapMidian.add(10);
        assertTrue(heapMidian.findMidian() == 4.5);
    }

    @Test
    public void testStackQueue() {
        StackQueue<Integer> queue = new StackQueue<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        assertTrue(queue.poll().equals(1));
        queue.add(4);
        assertTrue(queue.poll().equals(2));
        queue.poll();
        queue.poll();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testMyHeap() {
        MyHeap myHeap = new MyHeap();
        int[] a = new int[10000];
        for (int i = 0; i < 10000; i++) {
            a[i] = i;
        }
        assertTrue(myHeap.getMaxK(a, 100) == 9900);
        assertTrue(myHeap.getMinK(a, 100) == 99);
    }
}
