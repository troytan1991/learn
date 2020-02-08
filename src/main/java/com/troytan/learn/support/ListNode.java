package com.troytan.learn.support;

import lombok.Data;

/**
 * 单向链表
 *
 * @Author troytan
 * @Date 2/5/2020
 */
@Data
public class ListNode {
    private Integer val;
    public ListNode next;

    public ListNode(Integer val) {
        this.val = val;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode cur = this;
        while (cur != null) {
            sb.append(cur.getVal() + ",");
            cur = cur.next;
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
