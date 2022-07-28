package com.wills.leetcode.dynamic_algorithm.middle.interleaving_string;

import org.junit.Test;

/**
 * @ClassName nterleavingString
 * @Date 2022/7/28 13:43
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class nterleavingString {

    private static final String s1 = "aabcc";
    private static final String s2 = "dbbca";
    private static final String s3 = "aadbbcbcac";

    @Test
    public void test() {
        System.out.println(isInterleave(s1, s2, s3));
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        if (s3.length() != (m + n)) return false;
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        // 二维数组的 x 轴代表 s1 跟 s3的匹配程度(以单个字符来记) 不相符直接终止
        for (int i = 1; i <= m && s1.charAt(i - 1) == s3.charAt(i - 1); i++) dp[i][0] = true;
        // 二维数组的 y 轴代表 s1 跟 s3的匹配程度(以单个字符来记) 不相符直接终止
        for (int j = 1; j <= n && s2.charAt(j - 1) == s3.charAt(j - 1); j++) dp[0][j] = true;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char s1Curr = s1.charAt(i - 1);
                char s2Curr = s2.charAt(j - 1);
                // 为什么要 i + j - 1? 因为 s3是集合 s1 + s2 的，所以这个双重循环就是使用s1(s2)遍历的，所以要这样
                char s3Curr = s3.charAt(i + j - 1);
                dp[i][j] = (dp[i - 1][j] && s3Curr == s1Curr)
                        || (dp[i][j - 1] && s3Curr == s2Curr);
            }
        }
        return dp[m][n];
    }
}
