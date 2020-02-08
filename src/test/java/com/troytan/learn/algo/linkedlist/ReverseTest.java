package com.troytan.learn.algo.linkedlist;

import com.troytan.learn.support.ListNode;
import org.junit.Assert;
import org.junit.Test;

public class ReverseTest {

    private Reverse reverse = new Reverse();

    @Test
    public void test01() {
        ListNode head = generateList(1, 0, 6, 7, 3, 2);
        ListNode result = reverse.reverse1(head);
        Assert.assertEquals(result.toString(), "2,3,7,6,0,1");
    }

    @Test
    public void test02() {
        ListNode head = generateList(1, 0, 6, 7, 3, 2);
        ListNode result = reverse.reverse2(head);
        Assert.assertEquals(result.toString(), "2,3,7,6,0,1");
    }

    @Test
    public void test03() {
        ListNode head = generateList(1, 0, 6, 7, 3, 2);
        ListNode result = reverse.reverseBetween(head, 1, 4);
        Assert.assertEquals(result.toString(), "1,3,7,6,0,2");
    }

    private ListNode generateList(int... vals) {
        if (vals.length == 0) {
            return null;
        }
        ListNode head = new ListNode(vals[0]);
        ListNode cur = head;
        for (int i = 1; i < vals.length; i++) {
            cur.next = new ListNode(vals[i]);
            cur = cur.next;
        }
        return head;
    }


}
