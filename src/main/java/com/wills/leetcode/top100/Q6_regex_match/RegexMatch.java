package com.wills.leetcode.top100.Q6_regex_match;

import org.junit.Test;

/**
 * @ClassName RegexMatch
 * @Date 2022/10/19 11:56
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class RegexMatch {

    private static final String s = "aaaaab";
    //    private static final String p = "a*b";
    private static final String p = ".*b";

    @Test
    public void test() {
        System.out.println(isMatch(s, p));
    }

    /**
     * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
     * <p>
     * 示例 1：
     * 输入：s = "aa", p = "a"
     * 输出：false
     * 解释："a" 无法匹配 "aa" 整个字符串。
     * <p>
     * 示例 2:
     * 输入：s = "aa", p = "a*"
     * 输出：true
     * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
     * <p>
     * 示例3：
     * 输入：s = "ab", p = ".*"
     * 输出：true
     * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
     *
     * 教学视频地址： https://leetcode.cn/problems/regular-expression-matching/solution/shi-pin-tu-jie-dong-tai-gui-hua-zheng-ze-biao-da-s/
     * 练习地址： https://alchemist-al.com/algorithms/regular-expression
     */
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];

        // 初始条件赋值
        dp[0][0] = true;
        for (int col = 1; col < dp[0].length; col++) {
            char ch = p.charAt(col - 1);
            if (col > 1) {
                if (ch == '*') {
                    dp[0][col] = dp[0][col - 2];
                } else {
                    dp[0][col] = false;
                }
            } else {
                if (ch == '*') {
                    dp[0][col] = true;
                }
            }
        }

        // 进行遍历判断
        for (int row = 1; row < dp.length; row++) {
            char ch1 = s.charAt(row - 1);
            for (int col = 1; col < dp[row].length; col++) {
                char ch2 = p.charAt(col - 1);
                if (ch1 == ch2 || ch2 == '.') {
                    dp[row][col] = dp[row - 1][col - 1];
                } else if (ch2 == '*') {
                    if (col > 1) {
                        if (dp[row][col - 2]) {
                            dp[row][col] = true; // * 前面的字符出现0次
                        } else {
                            char prev = p.charAt(col - 2);
                            if (prev == ch1 || prev == '.') {
                                dp[row][col] = dp[row - 1][col]; // * 前面的字符出现多次
                            }
                        }
                    }
                }
            }
        }
        boolean[] lastRow = dp[dp.length-1];
        return lastRow[lastRow.length - 1];
    }
}
