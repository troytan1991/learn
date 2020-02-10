package com.troytan.learn.algo.greed;

/**
 * 跳跃游戏
 *
 * @Author troytan
 * @Date 2/10/2020
 */
public class JumpGame {

    /**
     * nums存储了非负正数，表示最远可以向后跳跃多少步，求解否从第一个跳跃到最后一个位置
     * 1）将数组转化为最远可以跳跃的位置
     * 2）遍历过程及时更新最远位置
     * <p>
     * 贪心思想：尽可能跳的更远
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            //数组转化为最远跳跃的位置
            nums[i] = i + nums[i];
        }
        //起始指针与结束指针
        int start = 0, end = nums[0];
        //两种场景结束循环：
        // 1）当起始位置与结束位置重合
        // 2）结束位置到达数组边界
        while (start < end && end < nums.length - 1) {
            if (nums[start] > end) {
                //遇到可以跳跃更远的节点，更新end
                end = nums[start];
            }
            start++;
        }
        return end >= nums.length - 1;
    }

    /**
     * 求解从第0个到最后一个的最少跳跃步数
     * <p>
     * 贪心思想：在当前位置与当前最远位置中选择一个最远进行跳跃
     *
     * @param nums
     * @return
     */
    public int jumpCount(int[] nums) throws Exception {
        int length = nums.length;
        if (length < 2) {
            return 0;
        }
        int count = 0;
        //当前最远可达位置
        int curMaxIndex = nums[0];
        //在start-curMaxIndex之间，最远可达位置
        int preMaxIndex = 0;      //
        for (int i = 0; i < length; i++) {
            if (nums[i] + i > preMaxIndex) {
                //更新当前最远可达位置
                preMaxIndex = nums[i] + i;
            }
            if (i >= curMaxIndex) {
                //无法向后遍历时，进行一次跳跃
                curMaxIndex = preMaxIndex;
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) throws Exception {
        JumpGame jumpGame = new JumpGame();
        int[] inputs = new int[]{4, 3, 1, 1, 5};
//        int[] inputs = new int[]{3, 2, 1, 0, 4};
//        System.out.println(jumpGame.canJump(inputs));
        System.out.println(jumpGame.jumpCount(inputs));
    }
}
