package com.wills.leetcode.dynamic_algorithm.easy.get_max_num;

import org.junit.Test;

/**
 * @ClassName GetMaxNum
 * @Date 2022/7/21 10:46
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class GetMaxNum {

//    private static final int n = 7;
//    private static final int n = 2;
    private static final int n = 3;

    @Test
    public void test(){
        System.out.println(getMaximumGenerated(n));
    }

    public int getMaximumGenerated(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        int max = 0;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int tmp = i % 2, div = i / 2;
            if(tmp == 1) dp[i] = dp[div] + dp[div + 1];
            else dp[i] = dp[div];
            max = Math.max(max,dp[i]);
        }

        return max;
    }
}
