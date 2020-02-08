package com.troytan.learn.algo.linkedlist;

import com.troytan.learn.support.ListNode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 求两个链表的交点
 *
 * @Author troytan
 * @Date 2/6/2020
 */
public class GetIntersectionNode {

    /**
     * 不使用额外空间
     *
     * @param head1
     * @param head2
     * @return
     */
    public ListNode getIntersectionNode1(ListNode head1, ListNode head2) {
        int length1 = getLength(head1);
        int length2 = getLength(head2);
        //求长度差
        int length = Math.abs(length1 - length2);
        //两指针移动到同一位置
        if (length1 < length2) {
            head2 = forwardN(head2, length);
        } else {
            head1 = forwardN(head1, length);
        }
        //同时向后移动
        while (head1 != null && head2 != null) {
            //有相同节点返回
            if (head1 == head2) {
                return head1;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        //无相同节点返回null
        return null;
    }

    /**
     * 利用另外的空间，set
     *
     * @param head1
     * @param head2
     * @return
     */
    public ListNode getIntersectionNode2(ListNode head1, ListNode head2) {
        Set<ListNode> set = new HashSet<>();
        //将head1放入集合
        while (head1 != null) {
            set.add(head1);
            head1 = head1.next;
        }
        //遍历head2，直到set中出现相同的元素
        while (head2 != null) {
            if (set.contains(head2)) {
                return head2;
            }
            head2 = head2.next;
        }
        return null;
    }

    /**
     * 计算链表长度
     *
     * @param head
     * @return
     */
    private int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    /**
     * 指针前进长度n
     *
     * @param head
     * @param n
     * @return
     */
    private ListNode forwardN(ListNode head, int n) {
        while (n-- > 0) {
            head = head.next;
        }
        return head;
    }
}
