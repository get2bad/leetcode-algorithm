package com.wills.leetcode.other.reverse_string;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author 王帅
 * @ClassName ReverseString
 * @date 2021/5/26 10:14
 * @Version 1.0
 * @Description
 */
public class ReverseString {

    public static void main(String[] args) {
        String source = "(u(love)i)";
        System.out.println(reverseParentheses1(source));
    }

    public static String reverseParentheses1(String s) {
        Deque<String> stack = new LinkedList<String>();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(sb.toString());
                sb.setLength(0);
            } else if (ch == ')') {
                sb.reverse();
                sb.insert(0, stack.pop());
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static String reverseParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        char[] arr = s.toCharArray();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(')
                stack.push(i);
            if (arr[i] == ')')
                reverse(arr, stack.pop() + 1, i - 1);
        }

        for (int i = 0; i < arr.length; i++)
            if (arr[i] != ')' && arr[i] != '(')
                sb.append(arr[i]);

        return sb.toString();
    }

    public static void reverse(char[] arr, int left, int right) {
        while (right > left) {
            char tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
            right--;
            left++;
        }
    }
}
