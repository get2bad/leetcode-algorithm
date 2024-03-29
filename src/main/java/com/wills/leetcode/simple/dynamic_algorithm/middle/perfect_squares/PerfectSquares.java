package com.wills.leetcode.simple.dynamic_algorithm.middle.perfect_squares;

/**
 * @author 王帅
 * @date 2021-04-17 10:21:05
 * @description:
 */
public class PerfectSquares {

    public static void main(String[] args) {
        System.out.println(numSquares(13));
    }

    public static int numSquares(int n) {
        int[] dp = new int[n + 1]; // 默认初始化值都为0
        for (int i = 1; i <= n; i++) {
            dp[i] = i; // 最坏的情况就是每次+1
            for (int j = 1; i - j * j >= 0; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1); // 动态转移方程
            }
        }
        return dp[n];
    }
}
