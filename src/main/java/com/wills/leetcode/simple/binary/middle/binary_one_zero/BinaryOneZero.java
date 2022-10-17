package com.wills.leetcode.simple.binary.middle.binary_one_zero;

/**
 * @author 王帅
 * @date 2022-03-28 21:39:00
 * @description:
 */
public class BinaryOneZero {

    public static void main(String[] args) {
        BinaryOneZero obj = new BinaryOneZero();
        int source = 5;
        System.out.println(obj.hasAlternatingBits(source));
    }

    public boolean hasAlternatingBits(int n) {
        String binaryString = Integer.toBinaryString(n);
        char[] source = binaryString.toCharArray();
        for (int i = 1; i < source.length; i++) {
            char pre = source[i - 1];
            char curr = source[i];
            if((pre - curr) == 0) return false;
        }
        return true;
    }
}
