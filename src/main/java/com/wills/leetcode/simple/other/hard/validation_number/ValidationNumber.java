package com.wills.leetcode.simple.other.hard.validation_number;

/**
 * @author 王帅
 * @ClassName ValidationNumber
 * @date 2021/6/17 09:49
 * @Version 1.0
 * @Description 有效的数字
 */
public class ValidationNumber {

    public static void main(String[] args) {
        String[] source = {"2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"};
        for (String s : source) {
            System.out.print(isNumber(s) + ",");
        }
    }

//    public static boolean isNumber(String s){
//        String regex = "[+-]?(\\d+)?\\.?(?(1)\\d*|\\d+)([eE][+-]?\\d+)?";
//        return Pattern.matches(regex,s);
//    }

    public static boolean isNumber(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'E' || c == 'e') {
                return (isDeci(0, i, s) || isInt(0, i, s)) && isInt(i + 1, s.length(), s);
            }
        }
        return isDeci(0, s.length(), s) || isInt(0, s.length(), s);
    }

    public static boolean isDeci(int l, int r, String s) {//判断是否为小数
        int flag = 0;
        boolean res = false;
        for (int i = l; i < r; i++) {
            char c = s.charAt(i);
            if (c == '+' || c == '-') {
                if (i != l) {
                    return false;
                }
            } else if (c == '.') {
                if (flag == 0) {
                    flag++;
                } else {
                    return false;
                }
            } else if (c >= '0' && c <= '9') {
                res = true;
            } else {
                return false;
            }
        }
        return res;
    }

    public static boolean isInt(int l, int r, String s) {//判断是否为整数
        boolean res = false;
        for (int i = l; i < r; i++) {
            char c = s.charAt(i);
            if (c == '+' || c == '-') {
                if (i != l) {
                    return false;
                }
            } else if (c >= '0' && c <= '9') {
                res = true;
            } else {
                return false;
            }
        }
        return res;
    }
}
