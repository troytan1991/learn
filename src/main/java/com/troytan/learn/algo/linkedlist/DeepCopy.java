package com.troytan.learn.algo.linkedlist;

import com.troytan.learn.support.ComplicateListNode;
import com.troytan.learn.support.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 深copy一个复杂链表
 *
 * @Author troytan
 * @Date 2/6/2020
 */
public class DeepCopy {

    /**
     * 通过标记索引位置建立新旧链表的关系
     *
     * @param head
     */
    public ComplicateListNode deepCopy(ComplicateListNode head) {
        //旧的链表集合映射，value为索引位置
        Map<ComplicateListNode, Integer> oldMap = new HashMap<>();
        //新的链表集合映射，value为索引位置
        Map<Integer, ComplicateListNode> newMap = new HashMap<>();
        //
        Map<Integer, Integer> keyMap = new HashMap<>();
        int i = 0;
        while (head != null) {
            oldMap.put(head, i);
            //复制节点
            newMap.put(i, new ComplicateListNode(head.getVal()));

            head = head.next;
            i++;
        }
        //建立用下标关联的random指针
        oldMap.forEach((k, v) -> {
            keyMap.put(v, oldMap.get(k.random));
        });
        newMap.forEach((k, v) -> {
            //建立两个指针关系
            v.next = newMap.get(k + 1);
            v.random = newMap.get(keyMap.get(k));
        });

        return newMap.get(0);
    }
}
