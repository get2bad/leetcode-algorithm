package com.wills.leetcode.simple.dynamic_algorithm.easy.download_plugin;

import org.junit.Test;

/**
 * @ClassName DownloadPlugin
 * @Date 2022/7/25 13:44
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class DownloadPlugin {

    //    private static final int n = 2;
//    private static final int n = 3;
//    private static final int n = 4;
    private static final int n = 7;

    @Test
    public void test() {
        System.out.println(leastMinutes(n));
    }

    public int leastMinutes(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[(i + 1) / 2] + 1;
        }
        return dp[n];
    }
}
