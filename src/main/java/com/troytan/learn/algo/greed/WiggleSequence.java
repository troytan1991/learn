package com.troytan.learn.algo.greed;

/**
 * 摇摆序列
 * <p>
 * 贪心思想：当有连续上升或下降的序列时，选择与当前值差值最大的点，使得后面的点更可能摇摆
 *
 * @Author troytan
 * @Date 2/10/2020
 */
public class WiggleSequence {

    /**
     * 求最长的摇摆序列，即找出所有的拐点
     * 利用状态机：初始、上升、下降
     *
     * @param input
     * @return
     */
    public int getMaxWiggleLength(int[] input) {
        //状态：0起始，1上升，-1下降
        int state = 0, count = 0;
        if (input.length > 0) {
            count = 1;
        }
        //从第二个点开始
        for (int i = 1; i < input.length; i++) {
            switch (state) {
                case 0:
                    if (input[i] == input[i - 1]) {
                        //持平则保持初始状态
                        state = 0;
                    } else {
                        //初始状态与前一个点不相等即为拐点
                        count++;
                        //根据节点值，判断时上升还是下降状态
                        if (input[i] > input[i - 1]) {
                            state = 1;
                        } else {
                            state = -1;
                        }
                    }
                    break;
                case 1:
                    if (input[i] < input[i - 1]) {
                        //上升状态下，当前节点较前一节点小，才算拐点
                        count++;
                        state = -1;
                    }
                    break;
                case -1:
                    if (input[i] > input[i - 1]) {
                        //下降状态下，当前节点较前一节点大，才算拐点
                        count++;
                        state = 1;
                    }
                    break;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        WiggleSequence wiggleSequence = new WiggleSequence();
        int[] input = new int[]{1, 17, 5, 10, 13, 15, 10, 5, 16, 8};
        System.out.println(wiggleSequence.getMaxWiggleLength(input));
    }
}
