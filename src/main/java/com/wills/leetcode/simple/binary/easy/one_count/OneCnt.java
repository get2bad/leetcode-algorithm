package com.wills.leetcode.simple.binary.easy.one_count;

/**
 * @author 王帅
 * @ClassName OneCnt
 * @date 2021/6/23 18:37
 * @Version 1.0
 * @Description
 */
public class OneCnt {

    public static void main(String[] args) {
        int source = 10;
        System.out.println(hammingWeight1(source));
    }



    // 位运算法
    public static int hammingWeight2(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res += (n>> i & 1) == 1? 1:0;
        }
        return res;
    }

    public static int hammingWeight3(int n) {
        int ret = 0;
        while (n != 0) {
            n &= n - 1;
            ret++;
        }
        return ret;
    }

    // API 转换法
    public static int hammingWeight1(int n) {
        return Integer.bitCount(n);
    }

    public static int hammingWeight(int n) {
        int res = 0;
        for (char c : Integer.toBinaryString(n).toCharArray()) {
            if(c == '1'){
                res ++;
            }
        }
        return res;
    }
}
