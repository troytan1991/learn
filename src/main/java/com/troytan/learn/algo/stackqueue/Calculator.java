package com.troytan.learn.algo.stackqueue;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Calculator {

    /**
     * 计算包含+-()的表达式，有空格
     *
     * @param input
     * @return
     */
    public int calculate1(String input) {
        //计算括号内的表达式时，保存当前结果和符号
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int length = input.length();
        //运算符，1为+，-1为-,默认值为正数
        int sign = 1;
        for (int i = 0; i < length; i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                //数字片段，则迭代取出整个数字
                int num = c - '0';
                while (i + 1 < length && Character.isDigit(input.charAt(i + 1))) {
                    num = 10 * num + (input.charAt(++i) - '0');
                }
                //当前结果与数字进行计算
                result += sign * num;
            } else if (c == '+') {
                sign = 1;
            } else if (c == '-') {
                sign = -1;
            } else if (c == '(') {
                //遇到(,则将当前结果与符号入栈，再重置result与sign，以便迭代计算()内的结果
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (c == ')') {
                //遇到),则将当前结果与栈中的符号和数字进行合并
                result = stack.pop() * result + stack.pop();
            }

        }
        return result;
    }

    /**
     * 计算*+-/的表达式,包含空格
     *
     * @param input
     * @return
     */
    public int calculate2(String input) {
        Stack<Integer> dataS = new Stack<>();
        int length = input.length();
        char sign = '+';
        //保存数字
        int num = 0;
        for (int i = 0; i < length; i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                num = 10 * num + (c - '0');
            }
            //当前字符为符号时或者最后一个字符时，进行数字入栈
            if (i == (length - 1) || (c != ' ' && !Character.isDigit(c))) {
                if (sign == '+') {
                    dataS.push(num);
                } else if (sign == '-') {
                    dataS.push(-num);
                } else if (sign == '*') {
                    //当前为*/时，与栈顶数字与num进行*/运算再入栈
                    dataS.push(dataS.pop() * num);
                } else if (sign == '/') {
                    dataS.push(dataS.pop() / num);
                }
                //存储符号位
                sign = c;
                //复位数字
                num = 0;
            }
        }
        int result = 0;
        //栈中数字相加即可
        while (!dataS.isEmpty()) {
            result += dataS.pop();
        }
        return result;
    }

    private int calc(int a, int b, char operator) {
        switch (operator) {
            case '+':
                return b + a;
            case '-':
                return b - a;
            case '*':
                return b * a;
            case '/':
                return b / a;
        }
        return 0;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        String input = "10 +( 2 - (5 + 16-(1+1)))";
        System.out.println(calculator.calculate1(input));
        System.out.println(calculator.calculate2("10*2 -9/3*3+5-7"));
    }
}
