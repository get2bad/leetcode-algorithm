package com.wills.leetcode.other.easy.base_7;

/**
 * @ClassName Base7
 * @Date 2022/3/7 09:43
 * @Author 王帅
 * @Version 1.0
 * @Description
 * 七进制数转换
 */
public class Base7 {

    public static void main(String[] args) {
        String res = convertToBase7_1(-7);
        System.out.println(res);
    }

    // 直接调用版
    public static String convertToBase7_1(int num) {
        return Integer.toString(num,7);
    }

    // 手动转换
    public static String convertToBase7(int num) {
        if(num == 0) return "0";
        int tmp = Math.abs(num);
        StringBuilder sb = new StringBuilder();
        while(tmp != 0){
            int diff = tmp % 7;
            sb.append(diff);
            tmp /= 7;
        }
        if(num < 0) sb.append("-");
        return sb.reverse().toString();
    }
}
