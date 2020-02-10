package com.troytan.learn.algo.greed;

import javafx.util.Pair;

import java.util.*;

/**
 * 最少加油次数的问题
 * <p>
 * 贪心思想：一直开到没油时，再返回遍历经过的加油站，找到可以加最多油的站进行加油
 *
 * @Author troytan
 * @Date 2/10/2020
 */
public class AddOil {

    /**
     * 计算最少的加油次数，才能到达终点
     * 前提：可以存储无限量油，每个加油站最多加一次，每个单位油跑一单位的距离
     *
     * @param l        起点到终点的距离
     * @param p        起始油量
     * @param stations 路途中所有的加油站，key为距离终点距离,value为可加油量
     * @return
     */
    public int getMinStop(int l, int p, List<Pair<Integer, Integer>> stations) {
        //最大堆，存储加油站加油量
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> b.compareTo(a));
        // 按照距离进行排序
        stations.sort((a, b) -> Integer.compare(a.getKey(), b.getKey()));
        int result = 0;
        for (int i = 0; i < stations.size(); i++) {
            //当前要走的距离
            int dis = l - stations.get(i).getKey();
            //当前油量不够距离时，进行加油，可能需要在多个站点加油
            while (!queue.isEmpty() && dis > p) {
                p += queue.poll();
                result++;
            }
            if (queue.isEmpty() && dis > p) {
                return -1;
            }
            //不加油，继续前行
            p -= dis;  //更新当前油量
            l -= dis;  //更新离终点距离
            //加油量入队列
            queue.offer(stations.get(i).getValue());
        }

        return result;
    }
}
