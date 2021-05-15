package com.wills.leetcode.waiting_to_sorted.valid_parentheses;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 王帅
 * @date 2021-02-02 09:22:30
 * @description:
 */
public class ValidParentheses {

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 示例 1：
     *
     * 输入：s = "()"
     * 输出：true
     * 示例 2：
     *
     * 输入：s = "()[]{}"
     * 输出：true
     * 示例 3：
     *
     * 输入：s = "(]"
     * 输出：false
     * 示例 4：
     *
     * 输入：s = "([)]"
     * 输出：false
     * 示例 5：
     *
     * 输入：s = "{[]}"
     * 输出：true
     * @param args
     */
    public static void main(String[] args) {
        String source = "([)]";
        System.out.println(isValid(source));
    }

    public static boolean isValid(String s) {
        if(s.length() == 0 || s == null || s.length() %2 != 0) return false;
        Deque<Character> deque = new ArrayDeque<>();
        Map<Character,Character> source = new HashMap<>();
        source.put(')','(');
        source.put('}','{');
        source.put(']','[');
        for (char c : s.toCharArray()) {
            if(c == '(' || c== '{' || c== '['){
                deque.push(c);
            } else {
                // 如果 出栈匹配不上现在的遍历元素 就直接返回flase
                if(deque.size() == 0 || source.get(c) != deque.pop()){
                    return false;
                }
            }
        }
        return deque.size() == 0;
    }
}
