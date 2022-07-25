package com.wills.leetcode.dynamic_algorithm.easy.contiguous_sequence_lucci;

import org.junit.Test;

/**
 * @ClassName ContiguousSequenceLCCI
 * @Date 2022/7/25 11:19
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class ContiguousSequenceLCCI {

//    private static final int[] source = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    private static final int[] source = {-2};

    @Test
    public void test() {
        System.out.println(maxSubArray(source));
    }

    public int maxSubArray(int[] nums) {
        int len = nums.length, max = Integer.MIN_VALUE;
        int[] dp = new int[len + 1];
        dp[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            if(dp[i - 1] < 0) dp[i] = nums[i - 1];
            else dp[i] = dp[i -1] + nums[i - 1];
            max = Math.max(Math.max(max, nums[i - 1]),dp[i]);
        }

        return max;
    }
}
