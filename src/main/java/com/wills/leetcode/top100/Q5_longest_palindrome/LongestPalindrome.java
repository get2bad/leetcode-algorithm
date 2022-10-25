package com.wills.leetcode.top100.Q5_longest_palindrome;

import org.junit.Test;

/**
 * @ClassName LongestPalindrome
 * @Date 2022/10/18 10:28
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class LongestPalindrome {

//    private static final String s = "babad";
    private static final String s = "bbbbbbbbbbbbbaba";

    @Test
    public void test() {
        System.out.println(longestPalindrome(s));
    }

    /**
     * 中心扩散法
     * 规则就是 分别找
     * 1. 左边是否和当前相同
     * 若相同，则 left-- len++
     * 2. 右边是否和当前相同
     * 若相同，则 right++ len++
     * 3. 左右是否相同
     * 若相同，则right++ left-- len+2
     * 每个位置向两边扩散都会出现一个窗口大小（len）。
     * 如果 len>maxLen(用来表示最长回文串的长度）。则更新 maxLen 的值
     * 图解请见：
     * https://leetcode.cn/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-fa-he-dong-tai-gui-hua-by-reedfa/
     */
    public String longestPalindrome1(String s) {
        if (s == null || s.length() == 0) return "";

        int strLen = s.length();
        int left, right, len = 1, maxStart = 0, maxLen = 0;

        for (int i = 0; i < strLen; i++) {
            left = i - 1;
            right = i + 1;
            while (left >= 0 && s.charAt(left) == s.charAt(i)) {
                len++;
                left--;
            }
            while (right < strLen && s.charAt(right) == s.charAt(i)) {
                len++;
                right++;
            }
            while (left >= 0 && right < strLen && s.charAt(right) == s.charAt(left)) {
                len = len + 2;
                left--;
                right++;
            }
            if (len > maxLen) {
                maxLen = len;
                maxStart = left;
            }
            len = 1;
        }
        return s.substring(maxStart + 1, maxStart + maxLen + 1);

    }

    /**
     * 上一版中心扩散的升级版 - 动态规划法
     * 用一个 boolean dp[l][r] 表示字符串从 i 到 j 这段是否为回文。试想如果 dp[l][r]=true，
     * 我们要判断 dp[l-1][r+1] 是否为回文。只需要判断字符串在(l-1)和（r+1)两个位置是否为相同的字符，减少了很多重复计算。
     * 动态规划关键是找到初始状态和状态转移方程。
     * 初始状态，l=r 时，此时 dp[l][r]=true。
     * 状态转移方程，dp[l][r]=true 并且(l-1)和（r+1)两个位置为相同的字符，此时 dp[l-1][r+1]=true。
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) return s;

        // maxStart 最长回文串的起点 maxEnd 最长回文串的终点 maxLen 最长回文串的长度
        int strLen = s.length(), maxStart = 0, maxEnd = 0, maxLen = 1;

        boolean[][] dp = new boolean[strLen][strLen];

        for (int right = 1; right < strLen; right++) {
            for (int left = 0; left < right; left++) {
                // dp[left + 1][right - 1] 代表前一次的最后一次遍历是否为回文字符串,因为 left 在外层循环时都会重置
                // 因为 left 和 right 代表的字符相同，并且 left 后一个字符和 right 前面的一个字符是回文字符串，所以dp[left][right] = true
                // right - left <= 2 表示的是 aa bb 这种情况，肯定就是回文字符串
                if (s.charAt(left) == s.charAt(right) && (right - left <= 2 || dp[left + 1][right - 1])) {
                    dp[left][right] = true;
                    if (right - left + 1 > maxLen) {
                        maxLen = right - left + 1;
                        maxStart = left;
                        maxEnd = right;
                    }
                }
            }
        }
        return s.substring(maxStart, maxEnd + 1);
    }
}
