package com.troytan.learn.algo.linkedlist;

import com.troytan.learn.support.ComplicateListNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeepCopyTest {

    DeepCopy deepCopy = new DeepCopy();

    @Test
    public void deepCopy() {
        ComplicateListNode head = generateList(5);
        ComplicateListNode newHead = deepCopy.deepCopy(head);
        while (head != null) {
            Assert.assertTrue(compare(head, newHead));
            head = head.next;
            newHead = newHead.next;
        }
    }

    private ComplicateListNode generateList(int n) {
        Random random = new Random();
        List<ComplicateListNode> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ComplicateListNode node = new ComplicateListNode(i);
            nodes.add(node);
        }
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.size() > i + 1) {
                nodes.get(i).next = nodes.get(i + 1);
            }
            nodes.get(i).random = nodes.get(random.nextInt(n));
        }
        return nodes.get(0);
    }

    private boolean compare(ComplicateListNode node1, ComplicateListNode node2) {

        return node1.getVal().equals(node2.getVal()) &&
                (node1.next == null || node1.next.getVal().equals(node2.next.getVal())) &&
                node1.random.getVal().equals(node2.random.getVal());

    }
}
