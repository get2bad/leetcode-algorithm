package com.wills.leetcode.simple.other.easy.length_lastword;

/**
 * @author 王帅
 * @date 2021-02-12 18:48:51
 * @description:
 */
public class LengthOfLastWord {

    /**
     *
     */
    public static void main(String[] args) {

    }

    public static int lengthOfLastWord(String s) {
        if(s.trim().length() == 0) return 0;
        String[] split = s.split(" ");
        return split[split.length - 1].trim().length();
    }
}
