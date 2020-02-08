package com.troytan.learn.algo.linkedlist;

import com.troytan.learn.support.ListNode;

/**
 * 逆序链表类题目
 *
 * @Author troytan
 * @Date 2/5/2020
 */
public class Reverse {

    /**
     * 通过创建新的链表头，依次指向新表头，遍历完成即完成逆序
     *
     * @param head
     * @return
     */
    public ListNode reverse1(ListNode head) {
        //新表头
        ListNode newHead = null;
        //当前遍历的指针位置
        ListNode cur = null;
        while (head != null) {
            cur = head.next;
            head.next = newHead;
            newHead = head;
            head = cur;
        }
        return newHead;
    }

    /**
     * 递归解法
     *
     * @param head
     * @return
     */
    public ListNode reverse2(ListNode head) {
        return reverse2Helper(head, null);
    }

    /**
     * 逆序第m到n个节点
     * 需要标记四个指针，逆转片段前，逆转片段新头部，当前遍历，逆转片段新尾部
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode preHead = head; //前序节点，并备份head
        ListNode cur = null;     //当前遍历节点
        ListNode newHead = null; //新头部
        //定位到前序节点
        for (int i = 0; i < m - 1; i++) {
            preHead = preHead.next;
        }
        //当前遍历节点
        cur = preHead.next;
        //标记为新的尾部
        ListNode newEnd = cur;
        for (int i = 0; i < n; i++) {
            //m-n片段的逆序
            ListNode next = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = next;
        }
        //前序指向新头部
        preHead.next = newHead;
        //新尾部指向当前遍历节点
        newEnd.next = cur;

        return head;
    }

    /**
     * 每次逆转一个节点
     *
     * @param head    当前遍历的节点
     * @param newHead 新节点的头部
     * @return
     */
    private ListNode reverse2Helper(ListNode head, ListNode newHead) {
        if (head == null) {
            return newHead;
        }
        ListNode next = head.next;
        head.next = newHead;
        return reverse2Helper(next, head);
    }

}
