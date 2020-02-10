package com.troytan.learn.algo.greed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 射击气球
 * <p>
 * 贪心思想：满足当前可射击气球的前提下，尽可能多的射击到其它球
 *
 * @Author troytan
 * @Date 2/10/2020
 */
public class ShotBalloon {

    /**
     * 求最少需要多少支箭，才能将所有气球射爆
     * int[]表示气球宽度的起点和终点
     *
     * @param balloons
     * @return
     */
    public int findMinShots(List<int[]> balloons) {
        if (balloons.size() == 0) {
            return 0;
        }
        //以气球起点位置进行排序
        balloons.sort((a, b) -> Integer.compare(a[0], b[0]));
        int[] ball = balloons.get(0);
        int start = ball[0], end = ball[1], count = 1;
        for (int i = 1; i < balloons.size(); i++) {
            ball = balloons.get(i);
            //由于已排序，ball[0]>=start,（有一个交点也算重合）
            if (ball[0] <= end) {
                //更新区间起点
                start = ball[0];
                if (ball[1] < end) {
                    //更新区间终点
                    end = ball[1];
                }
            } else {
                //新增区间
                start = ball[0];
                end = ball[1];
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        ShotBalloon shotBalloon = new ShotBalloon();
        List<int[]> balloons = new ArrayList<>();
        balloons.add(new int[]{1, 3});
        balloons.add(new int[]{2, 4});
        balloons.add(new int[]{4, 5});
        balloons.add(new int[]{6, 7});
        System.out.println(shotBalloon.findMinShots(balloons));
    }
}
