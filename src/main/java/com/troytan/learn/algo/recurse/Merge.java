package com.troytan.learn.algo.recurse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 归并算法
 *
 * @Author troytan
 * @Date 2/14/2020
 */
public class Merge {

    /**
     * 归并排序
     *
     * @param src
     * @return
     */
    public List<Integer> mergeSort(List<Integer> src) {
        int size = src.size();
        if (size <= 1) {
            return src;
        }
        //排序左半序列
        List<Integer> l1 = mergeSort(src.subList(0, size / 2));
        //排序右半序列
        List<Integer> l2 = mergeSort((src.subList(size / 2, size)));
        //合并排好序的左右序列
        return mergeTwo(l1, l2);
    }

    /**
     * 合并两个有序的集合
     *
     * @param l1
     * @param l2
     * @return
     */
    private List<Integer> mergeTwo(List<Integer> l1, List<Integer> l2) {
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;
        while (i < l1.size() && j < l2.size()) {
            if (l1.get(i) <= l2.get(j)) {
                result.add(l1.get(i));
                i++;
            } else {
                result.add(l2.get(j));
                j++;
            }
        }
        //有剩余直接加入结果集
        while (i < l1.size()) {
            result.add(l1.get(i++));
        }
        while (j < l2.size()) {
            result.add(l2.get(j++));
        }

        return result;
    }

    public static void main(String[] args) {
        Merge merge = new Merge();
        List<Integer> src = Arrays.asList(2, 1, 6, 3, 22, 3, 3, 3, 1);


        System.out.println(merge.mergeSort(src));
    }
}
