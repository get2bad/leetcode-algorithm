package com.wills.leetcode.simple.dynamic_algorithm.easy.count_bit;

/**
 * @author 王帅
 * @ClassName CountBit
 * @date 2021-2021/5/25-10:29
 * @Version 1.0
 * @Description
 */
public class CountBit {

    public static void main(String[] args) {
        int source = 5;
        int[] res = countBits1(source);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }

    public static int[] countBits1(int n) {
        int[] dp = new int[n + 1];
        int height = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) {
                height = i;
            }
            dp[i] = dp[i - height] + 1;
        }
        return dp;
    }

    public static int[] countBits(int n) {
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = countOne(i);
        }
        return dp;
    }

    public static int countOne(int num){
        int res = 0;
        while(num>0){
            num &= (num - 1);
            res++;
        }

        return res;
    }
}
