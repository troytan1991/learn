package com.troytan.learn.algo.recurse;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成子集
 * <p>
 * 递归思想：每个数字有两个选择，存在或不存在子集，对每个选择进行下个数字的递归调用
 *
 * @Author troytan
 * @Date 2/11/2020
 */
public class Subsets1 {

    /**
     * 找出所有可能的子集
     *
     * @param nums 输入数字序列，数字不重复
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        //空子集先入队列[]
        result.add(item);
        generateSubsets(0, nums, item, result);
        return result;
    }

    /**
     * 利用位运算
     * <p>
     * 将子集总数计算出来，0-max每个整数表示一个子集
     * 每个整数的二进制表示，其中1表示对应下标数字应该出现
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets2(int[] nums) {
        //求出子集的总数，每个元素的出现与不出现，都会产生一个新的子集
        int max = 1 << nums.length;
        List<List<Integer>> result = new ArrayList<>();
        //计算出具体的子集
        for (int i = 0; i < max; i++) {
            //每个i表示一个子集，转化二进制，位下标为1表示该下标的数字应该出现
            List<Integer> item = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                //下标j转化为对应的二进制高位第j位为1，结果与i相与，大于0表示应该出现
                if ((i & 1 << j) > 0) {
                    item.add(nums[j]);
                }
            }
            result.add(item);
        }
        return result;
    }

    /**
     * 递归函数
     *
     * @param i      当前元素索引
     * @param nums   输入序列
     * @param item   当前子集
     * @param result 存储子集的集合
     */
    private void generateSubsets(int i, int[] nums, List<Integer> item, List<List<Integer>> result) {
        //当所有元素都遍历完，递归结束
        if (i >= nums.length) {
            return;
        }
        //复制一份item，避免操作的item是同一个对象
        List<Integer> myItem = new ArrayList<>(item);
        //第i个位置不加入子集
        generateSubsets(i + 1, nums, myItem, result);
        //第i个位置加入子集
        myItem.add(nums[i]);
        result.add(myItem);
        generateSubsets(i + 1, nums, myItem, result);
    }


    public static void main(String[] args) {
        Subsets1 generateSubsets = new Subsets1();
        int[] nums = {1, 2, 3, 4};
        generateSubsets.subsets(nums).forEach(
                item -> System.out.println(item)
        );
    }

}
