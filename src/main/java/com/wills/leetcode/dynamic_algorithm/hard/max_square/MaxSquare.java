package com.wills.leetcode.dynamic_algorithm.hard.max_square;

/**
 * @author 王帅
 * @date 2021-04-09 10:00:02
 * @description:
 * 最大正方形
 */
public class MaxSquare {


    public static void main(String[] args) {
        int[][] source = {{1,0,1,0,0},
                          {1,0,1,1,1},
                          {1,1,1,1,1},
                          {1,0,1,1,1}} ;
        System.out.println(maximalSquare(source));
    }

    public static int maximalSquare(int[][] martix){
        // 长
        int len = martix.length;
        // 宽
        int wid = martix[0].length;
        // 存储结果的数组
        int[][] dp = new int[len+1][wid+1];
        // 最大长方形的宽
        int maxSize = 0;
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= wid; j++) {
                if(martix[i - 1][j - 1] == 1){
                    // 取 最小值(上面，最小值(左上角，左边)) + 1
                    dp[i][j] = Math.min(dp[i - 1][j],Math.min(dp[i-1][j - 1],dp[i][j - 1])) + 1;

                    maxSize = Math.max(maxSize,dp[i][j]);
                }
            }
        }
        return maxSize * maxSize;
    }
}
