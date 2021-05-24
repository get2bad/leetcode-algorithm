package com.wills.leetcode.dynamic_algorithm.hard.printer;

public class StrangePrinter {

    public static void main(String[] args) {
        String source = "abaa";
        System.out.println(strangePrinter(source));
    }

    public static int strangePrinter(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];

        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    int minn = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        // k = i, i+ 1, i + 2, i+ 3 以此类推
                        // j = i + 1, i+ 2, i + 3, i + 4 一次类推
                        minn = Math.min(minn, dp[i][k] + dp[k + 1][j]);
                    }
                    dp[i][j] = minn;
                }
            }
        }

        return dp[0][len - 1];
    }
}
