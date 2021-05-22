package com.wills.leetcode.dynamic_algorithm.three_step;

public class ThreeStep {

    public static void main(String[] args) {
        int source = 5;

        System.out.println(waysToStep(source));
    }

    public static int waysToStep(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        if (n == 3) {
            return 4;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        //取模运算,得分步取
        for (int i = 4; i <= n; i++) {
            dp[i] = ((dp[i - 1] + dp[i - 2]) % 1000000007 + dp[i - 3]) % 1000000007;
        }
        return dp[n];
    }
}
