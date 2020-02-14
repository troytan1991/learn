package com.troytan.learn.algo.recurse;

import org.junit.Test;

import java.util.List;

public class QueueTest {

    private Queen queen = new Queen();

    @Test
    public void test01() {
        List<int[][]> result = queen.generateLocations(4);
        output(result);
    }

    private void output(List<int[][]> result) {
        for (int[][] ints : result) {
            int length = ints.length;
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    System.out.print(ints[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("------------");
        }
    }
}
