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

    /**
     * 计算+-/*()的四则运算
     * 1）利用栈，实现中缀表达式->后缀表达式的转化
     * 2）再使用一个栈，计算后缀表达式即可（遇数字入栈，与符号栈顶两元素出栈运算，将结果入栈）
     * 3）此处用一个queue存储后缀结果，以便观察原理，实际可以只用两个栈计算，不用存储中间的后缀表达式
     *
     * @param input
     * @return
     */
    public int calculate3(String input) {
        int length = input.length();
        Queue<Object> queue = new ArrayDeque<>();
        Stack<Character> operatorS = new Stack<>();
        Stack<Integer> dataStack = new Stack<>();
        for (int i = 0; i < length; i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                int num = c - '0';
                while (i + 1 < length && Character.isDigit(input.charAt(i + 1))) {
                    num = 10 * num + (input.charAt(++i) - '0');
                }
                queue.offer(num);
            } else if (c == '(' || operatorS.isEmpty() || comparePriority(c, operatorS.peek()) > 0) {
                //直接入栈的几种场景：
                //1)左括号 2)符号优先级大于栈顶--栈顶左括号直接入栈，优先级最低 3)栈为空
                operatorS.push(c);
            } else if (c == ')') {
                //右括号时，弹出所有符号，直到遇到左括号
                char temp;
                while ((temp = operatorS.pop()) != '(') {
                    queue.offer(temp);
                }
            } else {
                //c符号优先级<=栈顶，弹出栈顶，再入栈c
                queue.offer(operatorS.pop());
                operatorS.push(c);
            }
        }
        //符号栈全部出栈
        while (!operatorS.isEmpty()) {
            queue.offer(operatorS.pop());
        }
        //利用数据栈，计算queue中的后缀表达式
        while (!queue.isEmpty()) {
            Object e = queue.poll();
            if (e instanceof Integer) {
                dataStack.push((Integer) e);
            } else {
                dataStack.push(calc(dataStack.pop(), dataStack.pop(), (char) e));
            }
        }

        return dataStack.pop();
    }


    /**
     * 计算两者的运算
     *
     * @param a
     * @param b
     * @param operator
     * @return
     */
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

    /**
     * 比较符号优先级
     *
     * @param operator1
     * @param operator2
     * @return
     */
    private int comparePriority(char operator1, char operator2) {
        if (operator2 == '(') {
            //左括号，优先级最低
            return 1;
        }
        if (charIn(operator1, '*', '/')) {
            if (charIn(operator2, '*', '/')) {
                return 0;
            } else {
                return 1;
            }
        } else {
            if (charIn(operator2, '*', '/')) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    private boolean charIn(char c, char... chars) {
        for (char aChar : chars) {
            if (c == aChar) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        String input = "10 +( 2 - (5 + 16-(1+1)))";
        System.out.println(calculator.calculate1(input));
        System.out.println(calculator.calculate2("10*2 -9/3*3+5-7"));
        System.out.println(calculator.calculate3("1+((2/2+3)*4)-5+(1-2)"));
    }
}
