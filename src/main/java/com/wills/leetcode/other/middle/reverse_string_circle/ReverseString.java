package com.wills.leetcode.other.middle.reverse_string_circle;

/**
 * @author 王帅
 * @date 2021-01-30 17:39:52
 * @description:
 * 回文数
 */
public class ReverseString {

    public static void main(String[] args) {
        /**
         * 给你一个整数 x ，如果 x 是一个回文整数，返回 ture ；否则，返回 false 。
         *
         * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
         * 示例 1：
         *
         * 输入：x = 121
         * 输出：true
         * 示例 2：
         *
         * 输入：x = -121
         * 输出：false
         * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
         * 示例 3：
         *
         * 输入：x = 10
         * 输出：false
         * 解释：从右向左读, 为 01 。因此它不是一个回文数。
         * 示例 4：
         *
         * 输入：x = -101
         * 输出：false
         */
        System.out.println(isPalindrome(-12321));
    }

    public static boolean isPalindrome(int x) {
        // 先转换为字符串
        char[] source  = (x + "").toCharArray();

        int start = 0;
        int end = source.length - 1;
        boolean flag = true;
        while(start <= source.length / 2){
            if(source[start] != source[end]){
                flag = false;
                break;
            }
            start ++;
            end --;
        }
        return flag;
    }
}
