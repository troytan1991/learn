package com.troytan.learn.algo.stackqueue;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 实现带有求中位数的集合，最优复杂度
 * 利用两个堆：
 * 1）第一个堆为最大堆，维护前半数的元素
 * 2）第二个堆为最小堆，维护后半数的元素
 *
 * @Author troytan
 * @Date 2/8/2020
 */
public class HeapMidian {

    //前半数元素,实际为最大堆-左堆
    private Queue<Integer> smallHeap = new PriorityQueue<>((t1, t2) -> t2.compareTo(t1));
    //后半数元素,实际为最小堆-右堆
    private Queue<Integer> biggerHeap = new PriorityQueue<>();

    /**
     * 加入元素时有四种场景：
     * 1）新元素<=左堆顶,且左堆数量<=右堆数量 新元素入左堆
     * 2）新元素>右堆顶，且右堆数量<=左堆数量，新元素入右堆
     * 3）新元素<=左堆顶，且左堆数量>右堆数量，右堆顶元素出列入左堆，新元素再入左堆
     * 4）新元素>右堆顶，且右堆数量>左堆数量，左堆顶元素出列入右堆，新元素再入右堆
     *
     * @param t
     */
    public void add(Integer t) {
        if (!smallHeap.isEmpty() && smallHeap.peek().compareTo(t) >= 0) {
            if (smallHeap.size() > biggerHeap.size()) {
                biggerHeap.offer(smallHeap.poll());
            }
            smallHeap.offer(t);
        } else {
            if (biggerHeap.size() > smallHeap.size()) {
                smallHeap.offer(biggerHeap.poll());
            }
            biggerHeap.offer(t);
        }
    }

    public float findMidian() {
        if (biggerHeap.size() > smallHeap.size()) {
            return biggerHeap.peek();
        } else if (biggerHeap.size() == smallHeap.size()) {
            return (float) (biggerHeap.peek() + smallHeap.peek()) / 2;
        } else {
            return smallHeap.peek();
        }
    }

}
