package com.troytan.learn.algo.recurse;

import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.List;

/**
 * 排列组合类题目的递归常见模式：
 * <p>
 * recurse(index, input, item, result)
 * index: 当前递归的索引
 * input: 输入序列
 * item:  组合的一种可能，需要将所有的序列都递归完，才作为最终的合法结果，加入result
 * result：结果集，存储所有合法的组合
 *
 * @Author troytan
 * @Date 2/14/2020
 */
public class DecodeNums {

    /**
     * 输入一串数字,1-26对应A-Z，求所有可能的解码结果
     * 例如:123,可以解码为[1,2,3]对应ABC, [12,3]对应LC
     *
     * @param input 输入的数字串
     * @return
     */
    public List<List<String>> decode(String input) {
        List<List<String>> result = new ArrayList<>();
        List<String> item = new ArrayList<>();
        decodeRecurse(0, input, item, result);
        return result;
    }

    /**
     * 从第0个位置开始递归
     * <p>
     * 重点：进入递归前加入元素（合法性校验），而不是递归开始后加入元素
     *
     * @param i      当前索引
     * @param input  输入串
     * @param item   当前合法序列
     * @param result 结果集
     */
    private void decodeRecurse(int i, String input, List<String> item, List<List<String>> result) {
        //遍历到最后一个，则将item加入结果集
        if (i >= input.length()) {
            result.add(item);
            return;
        }
        //备份，迭代返回，回溯后恢复当前状态
        List<String> myItem = new ArrayList<>(item);
        //单个加入
        myItem.add(input.substring(i, i + 1));
        decodeRecurse(i + 1, input, myItem, result);

        //两个加入，两个条件:1）剩余个数要超过2个；2）两位数不能大于26
        if (i + 1 < input.length()) {
            String num = input.substring(i, i + 2);
            if (Integer.parseInt(num) <= 26) {
                myItem.add(num);
                decodeRecurse(i + 2, input, myItem, result);
            }
        }
/**********以下是进入递归后进行元素的校验、添加，写法逻辑不如以上解法*************/
//        if (cur != "") {
//            if (Integer.parseInt(cur) > 26) {
//                //有任意数字>26，则递归结束
//                return;
//            } else {
//                item.add(cur);
//            }
//        }
//        if (i >= input.length()) {
//            //递归到末尾，item为正确的结果之一
//            result.add(item);
//            return;
//        }
        //List<String> myItem = new ArrayList<>(item);
//        //单个加入
//        decodeRecurse(i + 1, input.substring(i, i + 1), input, myItem, result);
//        //两个加入
//        if (i + 1 < input.length()) {
//            decodeRecurse(i + 2, input.substring(i, i + 2), input, myItem, result);
//        }
    }

    public static void main(String[] args) {
        DecodeNums decodeNums = new DecodeNums();
        decodeNums.decode("1256").forEach(item -> {
            System.out.println(item);
        });
    }
}
