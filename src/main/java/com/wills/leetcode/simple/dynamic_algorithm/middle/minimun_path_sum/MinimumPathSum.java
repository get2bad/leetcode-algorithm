package com.wills.leetcode.simple.dynamic_algorithm.middle.minimun_path_sum;

/**
 * @author 王帅
 * @date 2021-04-16 09:36:54
 * @description:
 * 最小路径和
 */
public class MinimumPathSum {

    public static void main(String[] args) {
        int[][] source = {{1,3,1},
                          {1,5,1},
                          {4,2,1}};
        System.out.println(minPathSum(source));
    }

    public static int minPathSum(int[][] grid) {
        int len = grid.length;
        int wid = grid[0].length;
        int[][] source = new int[len][wid];
        source[0][0] = grid[0][0];
        for (int i = 1; i < len; i++) {
            source[i][0] = source[i-1][0] + grid[i][0];
        }
        for (int i = 1; i < wid; i++) {
            source[0][i] = source[0][i -1] + grid[0][i];
        }
        for (int i = 1; i < len; i++) {
            for (int j = 1; j < wid; j++) {
                int first = source[i -1][j] + grid[i][j];
                int second = source[i][j - 1] + grid[i][j];
                source[i][j] = Math.min(first,second);
            }
        }
        return source[len - 1][wid - 1];
    }
}
