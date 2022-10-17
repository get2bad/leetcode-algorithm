package com.wills.leetcode.simple.other.easy.delete_reverse_str;

/**
 * @ClassName DeleteReverseStr
 * @Date 2022/1/22 09:27
 * @Author 王帅
 * @Version 1.0
 * @Description
 * 删除回文子序列
 */
public class DeleteReverseStr {

    /**
     * 给你一个字符串s，它仅由字母'a' 和 'b'组成。每一次删除操作都可以从 s 中删除一个回文 子序列。
     * 返回删除给定字符串中所有字符（字符串为空）的最小删除次数。
     * 「子序列」定义：如果一个字符串可以通过删除原字符串某些字符而不改变原字符顺序得到，
     * 那么这个字符串就是原字符串的一个子序列。
     * 「回文」定义：如果一个字符串向后和向前读是一致的，那么这个字符串就是一个回文。
     *
     * 示例 1：
     * 输入：s = "ababa"
     * 输出：1
     * 解释：字符串本身就是回文序列，只需要删除一次。
     *
     * 示例 2：
     * 输入：s = "abb"
     * 输出：2
     * 解释："abb" -> "bb" -> "".
     * 先删除回文子序列 "a"，然后再删除 "bb"。
     *
     * 示例 3：
     * 输入：s = "baabb"
     * 输出：2
     * 解释："baabb" -> "b" -> "".
     * 先删除回文子序列 "baab"，然后再删除 "b"。
     * @param args
     */
    public static void main(String[] args) {

    }

    /**
     * 分析，删除回文子序列：
     * 1. 首先满足回文子序列有两种(1) aba 这种算是一种子序列 (2) abba 这种也算是一种子序列
     * 题目要求，需要几次才能全部删除完毕这个字符串,首先我们先模拟几种字符串：
     * 抛开官方给的例证，我们再假设几种：
     * aababaaa => 2
     * aabbbbaaa => 2
     * aaaabbbb => 2
     * 发现结果除了2就是1，所以我们可以快速运用左右指针法解题
     * 步骤：
     * 1. 首先将这个字符串转换为 字符数组
     * 2. 遍历这个字符数组,运动左右指针法解题
     * @param s
     * @return
     */
    public static int removePalindromeSub(String s) {
        int len = s.length();
        char[] chars = s.toCharArray();
        // 快慢指针法
        int fast = len - 1,slow = 0;
        while (slow < fast){
            if(chars[slow] != chars[fast]) return 2;
            slow++;
            fast--;
        }
        return 1;
    }
}
