package com.troytan.learn.algo.linkedlist;


import com.troytan.learn.support.ListNode;

public class Partition {

    /**
     * 将小于x的节点放到大于等于x节点前面，并保持相对位置不变
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        ListNode smaller = new ListNode(0); //比x小的临时链表
        ListNode bigger = new ListNode(0);  //比x大的临时链表
        ListNode smallCur = smaller;
        ListNode biggerCur = bigger;
        while (head != null) {
            if (head.getVal() < x) {
                //链接到小的链表
                smallCur.next = head;
                smallCur = smaller.next;
            } else {
                //链接到大的链表
                biggerCur.next = head;
                biggerCur = biggerCur.next;
            }
            head = head.next;
        }
        //链接大小链表
        smallCur.next = bigger.next;
        //注意尾部置空
        biggerCur.next = null;
        return smaller.next;
    }
}
