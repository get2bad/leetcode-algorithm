package com.wills.leetcode.simple.other.middle.additive_number;

/**
 * @ClassName AdditiveNumber
 * @Date 2022/1/10 09:49
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class AdditiveNumber {

    public static void main(String[] args) {
        String source = "";
        boolean res = isAdditiveNumber(source);
        System.out.println(res);
    }

    public static boolean isAdditiveNumber(String num) {
        if(num.length()<3) return false;
        char[] numArray = num.toCharArray();
        for (int i = 0; i < numArray.length; i++) {

        }
        return false;
    }
}
