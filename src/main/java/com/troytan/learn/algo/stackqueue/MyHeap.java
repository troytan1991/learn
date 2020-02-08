package com.troytan.learn.algo.stackqueue;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 利用优先队列实现最大堆、最小堆
 *
 * @Author troytan
 * @Date 2/8/2020
 */
public class MyHeap {

    /**
     * 求数组中第k大的数:实现一个最小堆，堆顶元素最小
     *
     * @param a
     * @param k
     * @return
     */
    public int getMaxK(int[] a, int k) {
        PriorityQueue<Integer> maxKHeap = new PriorityQueue<>();
        for (int i : a) {
            if (maxKHeap.size() < k) {
                maxKHeap.offer(i);
            } else if (maxKHeap.peek() < i) {
                maxKHeap.poll();
                maxKHeap.offer(i);
            }
        }
        return maxKHeap.peek();
    }

    /**
     * 求数组中第k小的值，实现一个最小堆
     *
     * @param a
     * @param k
     * @return
     */
    public int getMinK(int[] a, int k) {
        Queue<Integer> minKHeap = new PriorityQueue<>((m, n) -> Integer.compare(n, m));
        for (int i : a) {
            if (minKHeap.size() < k) {
                minKHeap.offer(i);
            } else if (minKHeap.peek() > i) {
                //堆顶元素比新元素大，则弹出堆顶，新元素入堆
                minKHeap.poll();
                minKHeap.offer(i);
            }
        }
        return minKHeap.peek();
    }

}
