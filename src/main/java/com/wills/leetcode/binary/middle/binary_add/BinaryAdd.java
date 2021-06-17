package com.wills.leetcode.binary.middle.binary_add;

/**
 * @author 王帅
 * @date 2021-02-12 19:07:13
 * @description:
 */
public class BinaryAdd {

    /**
     * 二进制相加
     */
    public static void main(String[] args) {
        String a = "11";
        String b = "1";
        System.out.println(addBinary(a, b));
    }

    /**
     * 使用末尾对齐的方式进行相加，保证了假如说 a b 字符串无法进行Integer.parseInt的时候的可行办法
     *
     * + '0' 啥意思？ 想一下大学学习的 ASCII，然后你懂得
     */
    public static String addBinary(String a,String b){
        StringBuffer sb = new StringBuffer();

        int n = Math.max(a.length(), b.length()), carry = 0;
        for (int i = 0; i < n; ++i) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            sb.append((char) (carry % 2 + '0'));
            carry /= 2;
        }

        if (carry > 0) {
            sb.append('1');
        }
        sb.reverse();

        return sb.toString();
    }

    public static String addBinary1(String a,String b){
        return Integer.toBinaryString(
                Integer.parseInt(a, 2) + Integer.parseInt(b, 2)
        );
    }
}
