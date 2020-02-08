package com.troytan.learn.algo.linkedlist;

import com.troytan.learn.support.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 求成环链表的环起始点
 *
 * @Author troytan
 * @Date 2/6/2020
 */
public class DetectCycle {

    /**
     * 快慢指针解决法，相遇节点
     *
     * @param head
     * @return
     */
    public ListNode detectCycle1(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        //快慢指针相遇时，快指针多走一个环的长度
        while (fast != slow) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //假设:头部距离环起点距离为a, 环长度为b,快慢指针交点距离环起点为x
        //由2*slow=fast,得到2(a+b-x) = a+b+b-x, a=x
        while (head != slow) {
            //head与相遇指针距离环起点相等，求交点即为环的起点
            head = head.next;
            slow = slow.next;
        }
        return head;
    }

    /**
     * 通过辅助空间set,当集合中发现相同元素时，即为环起点
     *
     * @param head
     * @return
     */
    public ListNode detectCycle2(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (!set.contains(head)) {
            set.add(head);
            head = head.next;
        }
        return head;
    }
}
