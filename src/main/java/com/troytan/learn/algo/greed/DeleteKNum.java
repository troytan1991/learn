package com.troytan.learn.algo.greed;

import java.util.Stack;

/**
 * 对于正整数num,删除k个数字，使得结果最小
 * <p>
 * 贪心思想：从高位开始遍历，尽可能删除大的高位
 *
 * @Author troytan
 * @Date 2/10/2020
 */
public class DeleteKNum {

    public int maxNumDeleteK(int num, int k) {
        Stack<Integer> stack = new Stack<>();
        int kIndex = 0;
        String s = num + "";
        for (int i = 0; i < s.length(); i++) {
            int cur = s.charAt(i) - '0';
            if (stack.isEmpty() || cur >= stack.peek()) {
                //当前数字>=栈顶，或者栈为空，直接入栈
                stack.push(cur);
            } else if (kIndex < k) {
                //当前数字<栈顶，且还未到删除个数，删除栈顶，当前数字入栈
                kIndex++;
                stack.pop();
                stack.push(cur);
            }
        }
        int result = 0, i = 0;
        //转化为数字
        while (!stack.isEmpty()) {
            result = result + (int) Math.pow(10, i++) * stack.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        DeleteKNum deleteKNum = new DeleteKNum();
        System.out.println(deleteKNum.maxNumDeleteK(142133, 2));
    }
}
