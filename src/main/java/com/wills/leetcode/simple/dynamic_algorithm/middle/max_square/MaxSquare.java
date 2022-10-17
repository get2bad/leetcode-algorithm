package com.wills.leetcode.simple.dynamic_algorithm.middle.max_square;

import org.junit.Test;

/**
 * @ClassName MaxSquare
 * @Date 2022/7/28 10:47
 * @Author 王帅
 * @Version 1.0
 * @Description 最大正方形
 */
public class MaxSquare {

    private static final char[][] source = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};


    @Test
    public void test() {
        System.out.println(maximalSquare(source));
    }

    public int maximalSquare(char[][] matrix) {
        int max = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return max;
        }
        int width = matrix.length, length = matrix[0].length;
        int[][] dp = new int[width][length];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                // 剪枝，如果当前位不为1，那么直接跳过，进行下一次循环
                if (matrix[i][j] != '1') continue;
                // 边界置 1
                if (i == 0 || j == 0) dp[i][j] = 1;
                // 不是边界，就取 dp[i - 1][j] 左 dp[i][j - 1] 上 dp[i - 1][j - 1]左上的最小值 + 1
                else dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                max = Math.max(max, dp[i][j]);
            }
        }
        return (int) Math.pow(max, 2);
    }
}
