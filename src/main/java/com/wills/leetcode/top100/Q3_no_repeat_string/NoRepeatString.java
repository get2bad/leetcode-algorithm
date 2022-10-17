package com.wills.leetcode.top100.Q3_no_repeat_string;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName NoRepeatString
 * @Date 2022/10/17 13:38
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class NoRepeatString {

    private static final String source = "abcabcbb";

    @Test
    public void test() {
        System.out.println(lengthOfLongestSubstring(source));
    }

    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。
     * <p>
     * 示例1:
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * <p>
     * 示例 2:
     * 输入: s = "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * <p>
     * 示例 3:
     * 输入: s = "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
     * 请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        Map<Character, Integer> source = new HashMap<>();
        int max = 0, slow = 0;
        for (int fast = 0; fast < s.length(); fast++) {
            if (source.containsKey(s.charAt(fast))) {
                // 如果有，就调整慢指针
                slow = Math.max(slow, source.get(s.charAt(fast)) + 1);
            }
            source.put(s.charAt(fast), fast);
            max = Math.max(max, fast - slow + 1);
        }
        return max;
    }
}
