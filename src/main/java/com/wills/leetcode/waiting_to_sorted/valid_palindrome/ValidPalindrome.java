package com.wills.leetcode.waiting_to_sorted.valid_palindrome;

/**
 * @author 王帅
 * @date 2021-02-12 19:38:08
 * @description:
 * 验证是否是会问字符串
 */
public class ValidPalindrome {

    /**
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     * 说明：本题中，我们将空字符串定义为有效的回文串。
     *
     * 示例 1:
     * 输入: "A man, a plan, a canal: Panama"
     * 输出: true
     *
     * 示例 2:
     * 输入: "race a car"
     * 输出: false
     */
    public static void main(String[] args) {
        String source = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(source));
    }


    /**
     * 因为题目条件是 要忽略空格 + 标点符号
     * 思路：
     * 1. 将字符串转化为一个CharArray
     * 2.然后使用一个指针分别遍历开头和结尾的，但是要ignore大小写
     * 3.如果遇到 空格 / 标点符号 就进行跳过
     * 4.遇到一个对不上的直接返回false
     * 5.遍历到最后还是对上那么返回true即可
     */
    public static boolean isPalindrome(String s) {
        char[] source = s.toCharArray();
        int i = 0;
        int j = source.length -1;
        while(i < j){
            if(!Character.isLetterOrDigit(source[i]) || source[i] == 32){
                // 非数字或者字母 就将当前指针 + 1 / 32 是空格
                ++i;
                continue;
            }

            if(!Character.isLetterOrDigit(source[j]) || source[j] == 32){
                // 非数字或者字母 就将当前指针 - 1
                --j;
                continue;
            }
            if(Character.toLowerCase(source[i]) != Character.toLowerCase(source[j])){
                return false;
            }
            // 循环完了 将外部指针 自减
            --j;
            ++i;
        }
        return true;
    }
}
