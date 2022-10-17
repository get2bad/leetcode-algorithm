package com.wills.leetcode.simple.dynamic_algorithm.easy.nth_tribonacci_number;

import org.junit.Test;

/**
 * @ClassName NthTribonacciNumber
 * @Date 2022/7/21 10:27
 * @Author 王帅
 * @Version 1.0
 * @Description
 * 1137. 第 N 个泰波那契数
 */
public class NthTribonacciNumber {

    private static final Integer n = 4;

    @Test
    public void test(){
        System.out.println(tribonacci(25));
    }

    public int tribonacci(int n) {
        if(n == 0) return 0;
        if(n <= 2) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i -3];
        }
        return dp[n];
    }
}
