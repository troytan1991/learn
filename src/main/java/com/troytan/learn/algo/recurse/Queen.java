package com.troytan.learn.algo.recurse;

import com.troytan.learn.support.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * 棋盘皇后问题
 * 1）皇后可以攻击棋盘上八个方向上的敌人
 * 2）nxn棋盘上摆放n个皇后的方式，使得皇后之间不可互相攻击
 *
 * @Author troytan
 * @Date 2/13/2020
 */
public class Queen {

    /**
     * 在nxn的棋盘上放n个皇后，求所有的摆放可能
     *
     * @param n
     * @return
     */
    public List<int[][]> generateLocations(int n) {
        //存储最终的结果
        List<int[][]> result = new ArrayList<>();
        //标记数组
        int[][] mark = new int[n][n];
        //当前的存储的摆放位置
        int[][] location = new int[n][n];
        generate(0, n, mark, location, result);
        return result;
    }

    /**
     * 递归求解
     *
     * @param i        当前摆放的行
     * @param n        棋盘大小
     * @param mark     当前已经被标记的位置数组
     * @param location 存放已经放置的皇后位置
     * @param result   结果集合
     */
    private void generate(int i, int n, int[][] mark, int[][] location, List<int[][]> result) {
        //当所有的行都放置完，加入结果集，退出迭代
        if (i >= n) {
            result.add(location);
            return;
        }
        //每行从第0列开始尝试放置
        for (int j = 0; j < n; j++) {
            //满足放置条件时，放置皇后，进行下一行的放置迭代
            if (mark[i][j] == 0) {
                //迭代之后需要恢复当前的标记数组和位置数组
                //此处需要深copy两数组
                int[][] markCopy = cloneArray(mark);
                int[][] locationCopy = cloneArray(location);
                //放置皇后，标记数组
                putDownQueen(i, j, markCopy);
                locationCopy[i][j] = 1;
                //使用copy的数组进行内部迭代
                generate(i + 1, n, markCopy, locationCopy, result);
            }
        }
    }

    /**
     * 二维数组的深copy
     *
     * @param src
     * @return
     */
    private int[][] cloneArray(int[][] src) {
        int n = src.length;
        int[][] clone = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                clone[i][j] = src[i][j];
            }
        }
        return clone;
    }


    /**
     * 放置一个皇后，标记棋盘上所有可以攻击的点
     *
     * @param x    皇后坐标，x
     * @param y    皇后坐标，y
     * @param mark 标记数组
     */
    private void putDownQueen(int x, int y, int[][] mark) {
        //方向数组，以(-1,0)开始，右时针旋转8个点到(0,-1)
        int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
        //放置皇后
        mark[x][y] = 1;
        //方向深度，从1到n
        for (int i = 1; i < mark.length; i++) {
            //八个方向遍历
            for (int j = 0; j < 8; j++) {
                //计算当前遍历的坐标
                int myX = x + dx[j] * i;
                int myY = y + dy[j] * i;
                if (myX >= 0 && myX < mark.length
                        && myY >= 0 && myY < mark.length) {
                    //坐标合法，则标记
                    mark[myX][myY] = 1;
                }
            }
        }
    }
}
