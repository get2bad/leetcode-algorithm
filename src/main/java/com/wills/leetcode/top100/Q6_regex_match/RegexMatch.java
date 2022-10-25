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
     * <p>
     * 示例4：
     * 输入 s = "" , p = "a*"
     * 输出： true
     * 解释： 因为 *表示匹配0个或者多个之前的元素，所以匹配0个a 所以匹配
     * <p>
     * 教学视频地址：
     * https://leetcode.cn/problems/regular-expression-matching/solution/zheng-ze-biao-da-shi-pi-pei-by-chen-wei-6h9ap/
     * 情况图片地址：
     * https://pic.leetcode-cn.com/1637558528-PEDkgF-20211118130839.png
     * 练习地址： https://alchemist-al.com/algorithms/regular-expression
     */
    public boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();

        boolean[][] dp = new boolean[sLen + 1][pLen + 1];

        // 初始化值
        dp[0][0] = true;
        for (int j = 2; j < pLen + 1; j++) {
            if (p.charAt(j - 1) == '*') {
                // 示例4，表示匹配0个前值，所以要 看 前2个的值
                // 匹配图文的情况1 相当于 * 重复 0 次(表示前面的元素出现了0次)
                dp[0][j] = dp[0][j - 2];
            }
        }

        // i - 1 表示当前遍历的字符 / j - 1 表示当前遍历的正则表达式的字符
        for (int i = 1; i < sLen + 1; i++) {
            for (int j = 1; j < pLen + 1; j++) {
                // 如果相同 或者是 . 的情况，那么就与之前一个相匹配
                // 匹配情况2 表示匹配一个的情况（.）
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') { // 如果是 * 的情况，就要继续判断
                    // 如果 当前遍历与正则表达式的前一个字符匹配 或者 正则表达式前一个字符是 .
                    if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        // 匹配情况3 s在 i - 1位置 与 p 的 j - 2 位置相匹配
                        // dp[i - 1][j] 表示 s向前看一个位置，相当于 * 重复了 0 次
                        // dp[i][j - 1] 表示 p向前看一个位置，相当于 * 重复了 1 次
                        // dp[i][j - 2] 表示 p往前看两个位置，相当于 * 重复了 2 次
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1] || dp[i][j - 2];
                    } else {
                        // 匹配情况4 * 重复0次
                        dp[i][j] = dp[i][j - 2];
                    }
                } else { // 否则肯定就是不匹配了
                    dp[i][j] = false;
                }

            }
        }
        return dp[sLen][pLen];
    }
}
