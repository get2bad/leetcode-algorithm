package com.wills.leetcode.simple.dynamic_algorithm.middle.ugly_number;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * @ClassName UglyNumber
 * @Date 2022/8/8 11:21
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class UglyNumber {

    private static final int[] primes = {2, 7, 13, 19};
    private static final int n = 12;

//    private static final int[] primes = {2,3, 5};
//    private static final int n = 1;

    @Test
    public void test() {
        System.out.println(nthSuperUglyNumber(n, primes));
        System.out.println("==========================================");
        System.out.println(nthSuperUglyNumber1(n, primes));
    }

    public int nthSuperUglyNumber1(int n, int[] primes) {
        int pLen = primes.length;
        int[] indexes = new int[pLen], dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            // 因为选最小值，先假设一个最大值
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < pLen; j++) {
                dp[i] = Math.min(dp[i], dp[indexes[j]] * primes[j]);
            }

            // dp[i] 是之前的哪个丑数乘以对应的 primes[j] 选出来的，给它加 1，当作下一次相乘的因数
            for (int j = 0; j < pLen; j++) {
                if (dp[i] == dp[indexes[j]] * primes[j]) {
                    // 注意：这里不止执行一次，例如选出 14 的时候，2 和 7 对应的最小丑数下标都要加 1，
                    // 大家可以打印 indexes 和 dp 的值加以验证
                    indexes[j]++;
                }
            }
        }
        return dp[n - 1];
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        long res = 1;
        PriorityQueue<Long> stack = new PriorityQueue<>();
        for (int i = 1; i < n; i++) {
            for (int prime : primes) {
                stack.offer(res * prime);
            }
            res = stack.poll();
            // 去重
            while (!stack.isEmpty() && res == stack.peek()) {
                stack.poll();
            }
        }
        return (int) res;
    }
}
