package com.troytan.learn.algo.recurse;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成所有合法的括号组合
 * <p>
 * 1）生成所有的组合
 * 2）加入限制条件
 * * *1）任意阶段，右括号数量不能大于左括号
 * * *2）左括号数不能大于预设数
 *
 * @Author troytan
 * @Date 2/13/2020
 */
public class Parentheses {

    /**
     * 有n组括号，求所有合法的括号
     *
     * @param n
     * @return
     */
    public List<String> generateParentheses(int n) {
        List<String> result = new ArrayList<>();
        generate(0, 0, n, "", result);
        return result;
    }

    /**
     * 递归求解括号
     *
     * @param left   左括号数
     * @param right  右括号数
     * @param n      目标括号对数
     * @param str    当前组合的字符串
     * @param result 结果集
     */
    private void generate(int left, int right, int n, String str, List<String> result) {
        //当右括号数为n时，递归结束
        if (right == n) {
            result.add(str);
            return;
        }
        //左括号小于n时，可以继续加入左括号递归
        if (left < n) {
            generate(left + 1, right, n, str + "(", result);
        }
        //右括号小于左括号数时，可以加入右括号递归
        if (right < left) {
            generate(left, right + 1, n, str + ")", result);
        }
    }

    public static void main(String[] args) {
        Parentheses parentheses = new Parentheses();
        parentheses.generateParentheses(3).stream().forEach(System.out::println);

    }
}
