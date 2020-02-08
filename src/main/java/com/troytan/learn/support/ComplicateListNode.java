package com.troytan.learn.support;

import lombok.Data;

import java.util.Objects;

/**
 * 复杂链表的copy
 *
 * @Author troytan
 * @Date 2/6/2020
 */
@Data
public class ComplicateListNode {
    //任意指向的指针
    public ComplicateListNode random;
    public ComplicateListNode next;
    private Integer val;

    public ComplicateListNode(Integer val) {
        this.val = val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComplicateListNode)) return false;
        ComplicateListNode that = (ComplicateListNode) o;
        return val.equals(that.val);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }
}
