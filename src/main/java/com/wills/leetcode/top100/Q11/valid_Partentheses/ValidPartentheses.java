package com.wills.leetcode.top100.Q11.valid_Partentheses;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ValidPartentheses
 * @Date 2022/10/20 10:47
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class ValidPartentheses {

    private static final String source = "()[]{}";

    @Test
    public void test(){
        System.out.println(isValid(source));
    }

    /**
     *  给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
     *
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 每个右括号都有一个对应的相同类型的左括号。
     *
     * 示例 1：
     * 输入：s = "()"
     * 输出：true
     *
     * 示例2：
     * 输入：s = "()[]{}"
     * 输出：true
     *
     * 示例3：
     * 输入：s = "(]"
     * 输出：false
     *
     * 本题可以使用栈的方式来解决
     */
    public boolean isValid(String s) {
        if(s.length() % 2 == 1) return false;
        Map<Character,Character> map = new HashMap<Character,Character>(){{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                // 如果栈为空 或者 栈首和对应的不相同 说明不是符合条件的括号
                if (stack.isEmpty() || stack.peek() != map.get(c)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
