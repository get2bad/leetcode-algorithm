package com.wills.leetcode.simple.array.minimum_subarray;

import org.junit.Test;

/**
 * @ClassName MinimumSubArray
 * @Date 2022/7/14 13:20
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class MinimumSubArray {

    private static final int[] source= {2,3,1,2,4,3};
    private static final int target = 7;

    @Test
    public void test(){
        System.out.println(minSubArrayLen(target, source));
    }

    // 因为题目中说明了 可不可以实现一个 O(log(n)) 复杂度的算法
    // 这不就是摆明了要用 前缀和 + 二分查询的方法
    public int minSubArrayLen(int target, int[] nums) {
        int len = nums.length, ans = Integer.MAX_VALUE;
        int[] sum = new int[len + 10];
        // 求出前缀和
        for (int i = 1; i <= len; i++) sum[i] = sum[i - 1] + nums[i - 1];
        // 使用 二分法来进行查询最少的总和
        for (int i = 1; i <= len; i++) {
            int curr = sum[i], diff = curr - target;
            int left = 0, right = i;
            while (left < right) {
                int mid = left + right + 1 >> 1;
                if (sum[mid] <= diff) left = mid;
                else right = mid - 1;
            }
            if (sum[right] <= diff) ans = Math.min(ans, i - right);
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
