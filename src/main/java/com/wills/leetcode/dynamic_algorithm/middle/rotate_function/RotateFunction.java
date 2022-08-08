package com.wills.leetcode.dynamic_algorithm.middle.rotate_function;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName RotateFunction
 * @Date 2022/8/2 10:16
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class RotateFunction {

    private static final int[] NUMS = {4, 3, 2, 6};

    @Test
    public void test() {
        System.out.println(maxRotateFunction(NUMS));
    }

    public int maxRotateFunction(int[] nums) {
        int len = nums.length, sum = Arrays.stream(nums).sum(), d = 0;
        int[] dp = new int[len];
        for (int i = 0; i < nums.length; i++) d += i * nums[i];

        int max = d;
        dp[len - 1] = d;
        for (int i = 0; i < len - 1; i++) {
            dp[i] = d + sum - (nums[len - 1 - i] * len);
            d = dp[i];
            max = Math.max(max, d);
        }
        return max;
    }

    public int maxRotateFunction1(int[] nums) {
        int n = nums.length;
        int[] sum = new int[n * 2 + 10];
        // 前缀和，用长度 2 * n 长度的数组
        for (int i = 1; i <= 2 * n; i++) sum[i] = sum[i - 1] + nums[(i - 1) % n];
        int ans = 0;
        for (int i = 1; i <= n; i++) ans += nums[i - 1] * (i - 1);
        for (int i = n + 1, cur = ans; i < 2 * n; i++) {
            cur += nums[(i - 1) % n] * (n - 1);
            cur -= sum[i - 1] - sum[i - n];
            if (cur > ans) ans = cur;
        }
        return ans;
    }
}
