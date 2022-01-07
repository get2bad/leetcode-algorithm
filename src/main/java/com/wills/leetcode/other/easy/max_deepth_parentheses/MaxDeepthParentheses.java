package com.wills.leetcode.other.easy.max_deepth_parentheses;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @ClassName MaxDeepthParentheses
 * @Date 2022/1/7 09:46
 * @Author 王帅
 * @Version 1.0
 * @Description
 * 括号的最大深度
 */
public class MaxDeepthParentheses {

    public static void main(String[] args) {
//        String source = "(1)+((2))+(((3)))";
//        String source = "(1)+((2))+(((3)))";
        String source = "(1+(2*3)+((8)/4))+1";
        int res = maxDepth(source);
        System.out.println(res);
    }

    // 使用栈来解决
    public static int maxDepth(String s) {
        int res = 0,flag = 0;
//        Deque<Character> stack = new ArrayDeque<>();
        char[] chars = s.toCharArray();
        for (char data : chars) {
            if('(' == data){
                // 如果栈内为空，就直接清除最终返回的元素即可
                flag++;
                res = Math.max(res,flag);
            }
            if(')'== data){
                flag--;
            }
        }
        return res;
    }
}
