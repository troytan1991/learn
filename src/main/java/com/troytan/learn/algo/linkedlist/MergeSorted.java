package com.troytan.learn.algo.linkedlist;

import com.troytan.learn.support.ListNode;

import java.util.List;

/**
 * 合并排序链表
 *
 * @Author troytan
 * @Date 2/7/2020
 */
public class MergeSorted {

    /**
     * 合并两个有序链表
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoList(ListNode l1, ListNode l2) {
        //临时节点
        ListNode head = new ListNode(0);
        //当前位置
        ListNode cur = head;
        while (l1 != null && l2 != null) {
            if (l1.getVal() < l2.getVal()) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
        }
        if (l1 == null) {
            cur.next = l2;
        }
        if (l2 == null) {
            cur.next = l1;
        }

        return head.next;
    }

    /**
     * 合并n个有序链表：归并法
     *
     * @param lists
     * @return
     */
    public ListNode mergeNLists(List<ListNode> lists) {
        int size = lists.size();
        //长度<=2时，直接返回
        if (size == 0) {
            return null;
        } else if (size == 1) {
            return lists.get(0);
        } else if (size == 2) {
            return mergeTwoList(lists.get(0), lists.get(1));
        }
        //等分lists
        List<ListNode> lists1 = lists.subList(0, size / 2);
        List<ListNode> lists2 = lists.subList(size / 2, size);

        //迭代归并子集合
        ListNode l1 = mergeNLists(lists1);
        ListNode l2 = mergeNLists(lists2);

        //合并两子集的结果
        return mergeTwoList(l1, l2);
    }
}
