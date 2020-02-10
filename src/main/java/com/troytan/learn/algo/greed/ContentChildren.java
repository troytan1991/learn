package com.troytan.learn.algo.greed;

import java.util.Arrays;

/**
 * 分糖果
 * <p>
 * 贪心思想：尽可能用最接近期望的糖果大小，满足小孩
 *
 * @Author troytan
 * @Date 2/10/2020
 */
public class ContentChildren {
    /**
     * 求最多可以满足多少小孩
     *
     * @param sweets   糖果以及大小
     * @param children 小孩以及期望的大小
     * @return
     */
    public int findContentChildren(int[] sweets, int[] children) {
        int sIndex = 0, cIndex = 0;
        //从小到大排序
        Arrays.sort(sweets);
        Arrays.sort(children);

        while (sIndex < sweets.length && cIndex < children.length) {
            if (sweets[sIndex] >= children[cIndex]) {
                cIndex++;
            }
            sIndex++;
        }
        return cIndex;
    }

    public static void main(String[] args) {
        ContentChildren contentChildren = new ContentChildren();
        int[] sweets = new int[]{1, 3, 4, 6, 9};
        int[] children = new int[]{2, 2, 7, 8};
        System.out.println(contentChildren.findContentChildren(sweets, children));
    }
}
