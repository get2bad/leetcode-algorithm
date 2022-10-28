package com.wills.leetcode.top100.Q16_longest_valid_parentheses;

import org.junit.Test;

import java.util.*;

/**
 * @ClassName LongestValidParentheses
 * @Date 2022/10/27 17:38
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class LongestValidParentheses {

    private static final String source = ")()())";

    @Test
    public void test() {
        System.out.println(longestValidParenthes(source));
    }

    /**
     * 给你一个只包含 '('和 ')'的字符串，找出最长有效（格式正确且连续）括号子串的长度。
     * <p>
     * 示例 1：
     * 输入：s = "(()"
     * 输出：2
     * 解释：最长有效括号子串是 "()"
     * <p>
     * 示例 2：
     * 输入：s = ")()())"
     * 输出：4
     * 解释：最长有效括号子串是 "()()"
     * <p>
     * 示例 3：
     * 输入：s = ""
     * 输出：0
     */
    public int longestValidParenthes(String s) {
        if (s.length() <= 1) return 0;
        char[] chars = s.toCharArray();
        //dp[i] means from s[0...i] 有效括号, 包含s[i]
        int[] dp = new int[chars.length];
        dp[1] = chars[0] == '(' && chars[1] == ')' ? 2 : 0;

        int max = dp[1];
        for (int i = 2; i < chars.length; i++) {
            if (chars[i] == '(') dp[i] = 0;
            else {
                // .....() 这种情况就要在 dp[i - 2] 的基础上 + 2 表示这是一个有效的括号
                if (chars[i - 1] == '(') dp[i] = dp[i - 2] + 2;
                else {
                    // .....))     ......((.......))
                    // i - dp[i - 1] - 1 < 0 代表是这种情况 )..... 一开始就是个闭括号
                    // chars[i - dp[i - 1] - 1] == ')' 说明 ......) 前面遇到了 )......) 构不成一个有效括号
                    if (i - dp[i - 1] - 1 < 0 || chars[i - dp[i - 1] - 1] == ')') dp[i] = 0;
                    // i - dp[i - 1] - 1 >= 1 说明前面有元素
                    // 2 + dp[i - 1] 表示有效括号之前的有效括号数
                    // dp[i - dp[i - 1] - 2] 中的 i - dp[i - 1] - 2
                    // 表示 有效括号之前两个的括号数 可以理解为 2+dp[i - 1] + dp[i - 2]
                    // (因为前面还有东西 (()) 内层的括号仅仅是那个索引内的东西)
                    else dp[i] = i - dp[i - 1] - 1 >= 1 ? 2 + dp[i - 1] + dp[i - dp[i - 1] - 2] : 2 + dp[i - 1];
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;

    }

    public int longestValidParenthesByStack(String s) {
        int max = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            // 对于遇到的每个 ‘(’ ，我们将它的下标放入栈中
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                // 对于遇到的每个 ‘)’ ，我们先弹出栈顶元素表示匹配了当前右括号：
                stack.pop();
                // 如果栈为空，说明当前的右括号为没有被匹配的右括号，我们将其下标放入栈中来更新我们之前提到的「最后一个没有被匹配的右括号的下标」
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    // 如果栈不为空，当前右括号的下标减去栈顶元素即为「以该右括号为结尾的最长有效括号的长度」
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }
}
