package com.wills.leetcode.dynamic_algorithm.uncrossed_line;

public class UncrossedLines {

    public static void main(String[] args) {
        int[] source1 = {1,2,3,4,5,6};
        int[] source2 = {1,3,2,4,5,6};
        System.out.println(maxUncrossedLines(source1, source2));
    }

    /**
     * 动态规划问题
     */
    public static int maxUncrossedLines(int[] nums1, int[] nums2) {
        int len = nums1.length, wid = nums2.length;
        int[][] dp = new int[len + 1][wid + 1];
        for (int i = 1; i <= len; i++) {
            int num1 = nums1[i - 1];
            for (int j = 1; j <= wid; j++) {
                int num2 = nums2[j - 1];
                if (num1 == num2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[len][wid];
    }
}
