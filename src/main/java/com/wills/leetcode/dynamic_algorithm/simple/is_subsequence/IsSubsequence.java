package com.wills.leetcode.dynamic_algorithm.simple.is_subsequence;

/**
 * @author 王帅
 * @date 2021-04-12 09:38:08
 * @description:
 * 判断子序列
 */
public class IsSubsequence {

    public static void main(String[] args) {
        String source = "abc";
        String target = "ahbgdc";
        System.out.println(isSubsequence2(source, target));
    }

    public static boolean isSubsequence2(String s, String t) {
        if(s.length()==0) return true;
        int n = s.length(), m = t.length();

        boolean[][] res = new boolean[n][m + 1];
        // 将空行置为 true，为了后续双重遍历的依赖
        for (int i = 0; i < m +1; i++) {
            res[0][i] = true;
        }

        /**
         * 核心思想就是，如果当前遍历的字符等于需要查找的字符串，那么就将他左上角的值给现在的值(为了保证有序性)，如果不等于，就将他之前的值赋予
         * 例如：
         *      a   s   d   f   g   结果
         * t    t   t   t   t   t   t
         * a    t   t   t   t   t   t
         * d    f   f   t(左上角)  t   t   t
         * g    f   f   f   f   t(左上角)  t
         *
         * 最终输出结果即可
         */
        for (int i = 1; i < res.length; i++) {
            char ch1 = s.charAt(i - 1);
            for (int j = 1; j < res[0].length; j++) {
                char ch2 = t.charAt(j - 1);
                if(ch1 == ch2){
                    res[i][j] = res[i - 1][j - 1];
                } else{
                    res[i][j] = res[i][j - 1];
                }
            }
        }
        // 输出最后一行的最后一个结果
        return res[res.length - 1][res[0].length - 1];
    }

    /**
     * 双指针法
     * 核心思想就是，使用while遍历，遍历这两个字符串，如果遇到相同的情况，就将i++，每次遍历都叠加j
     *
     * 最终输出 两者是否相同即可
     */
    public static boolean isSubsequence(String s, String t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == n;
    }
}
