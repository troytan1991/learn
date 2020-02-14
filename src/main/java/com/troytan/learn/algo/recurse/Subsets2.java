package com.troytan.learn.algo.recurse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 求不重复的子集
 *
 * @Author troytan
 * @Date 2/11/2020
 */
public class Subsets2 {

    /**
     * 子集所有元素相同即为相同子集，即使下标位置不一样
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        //对数字排序，以便去重
        Arrays.sort(nums);
        List<Integer> item = new ArrayList<>();
        result.add(item);
        generateSubsets(0, nums, item, result);
        return result;
    }

    /**
     * 求子集的所有元素和为target的子集，且需要去重
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> subsetsWithTarget(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        //对数字排序，以便去重
        Arrays.sort(nums);
        List<Integer> item = new ArrayList<>();
        generateSubsetsWithTarget(0, nums, item, result, target);
        return result;
    }

    /**
     * 递归求子集
     *
     * @param i
     * @param nums
     * @param item
     * @param result
     */
    private void generateSubsets(int i, int[] nums, List<Integer> item, List<List<Integer>> result) {
        if (i >= nums.length) {
            return;
        }
        List<Integer> myItem = new ArrayList<>(item);
        //不加当前元素递归
        generateSubsets(i + 1, nums, myItem, result);
        myItem.add(nums[i]);
        if (!contains(result, myItem)) {
            //相同子集不存在，则加入结果集
            result.add(myItem);
        }
        //添加当前元素递归
        generateSubsets(i + 1, nums, myItem, result);
    }

    private void generateSubsetsWithTarget(int i, int[] nums, List<Integer> item, List<List<Integer>> result, int target) {
        int sum = item.stream().mapToInt(num -> num).sum();
        if (i >= nums.length || sum > target) {
            return;
        }
        List<Integer> myItem = new ArrayList<>(item);
        //不加当前元素递归
        generateSubsetsWithTarget(i + 1, nums, myItem, result, target);
        myItem.add(nums[i]);
        if (!contains(result, myItem) && (sum + nums[i]) == target) {
            //相同子集不存在，则加入结果集
            result.add(myItem);
        }
        //添加当前元素递归
        generateSubsetsWithTarget(i + 1, nums, myItem, result, target);
    }

    /**
     * 判断是否有重复的子集
     *
     * @param result
     * @param item
     * @return
     */
    private boolean contains(List<List<Integer>> result, List<Integer> item) {
        for (List<Integer> integers : result) {
            if (integers.size() == item.size()) {
                boolean flag = true;
                for (int i = 0; i < integers.size(); i++) {
                    if (!integers.get(i).equals(item.get(i))) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Subsets2 generateSubsets = new Subsets2();
        int[] nums = {2, 1, 3, 3};
//        List<List<Integer>> lists = generateSubsets.subsets(nums);

        List<List<Integer>> lists = generateSubsets.subsetsWithTarget(nums, 3);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }
}
