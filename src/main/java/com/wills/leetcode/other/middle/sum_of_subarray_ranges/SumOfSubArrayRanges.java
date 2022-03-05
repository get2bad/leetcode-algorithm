package com.wills.leetcode.other.middle.sum_of_subarray_ranges;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @ClassName SumOfSubArrayRanges
 * @Date 2022/3/4 17:45
 * @Author 王帅
 * @Version 1.0
 * @Description
 * 子数组范围和
 */
public class SumOfSubArrayRanges {

    public static void main(String[] args) {
//        int[] source = {4,-2,-3,4,1};
        int[] source = {1,2,3};
        long res = subArrayRanges2(source);
        System.out.println(res);
    }

    // 暴力法
    public static long subArrayRanges(int[] nums) {
        int len = nums.length, max = 0, min = 0;
        long res = 0;
        // 枚举子数组左边界
        for ( int i = 0; i < len; i++ ) {
            max = nums[i];
            min = nums[i];
            // 枚举有边界
            for ( int j = i+1; j < len; j++ ) {
                // 维护min max
                max = Math.max(max, nums[j]);
                min = Math.min(min, nums[j]);
                res += (max-min);
            }
        }
        return res;
    }

    /**
     * 区间 DP（预处理）
     * @param nums
     * @return
     */
    public static long subArrayRanges1(int[] nums) {
        int n = nums.length;
        int[][][] f = new int[n][n][2];
        for (int i = 0; i < n; i++) f[i][i][0] = f[i][i][1] = nums[i];
        for (int len = 2; len <= n; len++) {
            for (int l = 0; l + len - 1 < n; l++) {
                int r = l + len - 1;
                f[l][r][0] = Math.min(nums[r], f[l][r - 1][0]);
                f[l][r][1] = Math.max(nums[r], f[l][r - 1][1]);
            }
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                ans += f[i][j][1] - f[i][j][0];
            }
        }
        return ans;
    }

    /**
     * 单调栈
     */
    public static long subArrayRanges2(int[] nums) {
        int n = nums.length;
        // min[i] 为 nums[i] 作为区间最小值的次数；max[i] 为 nums[i] 作为区间最大值的次数
        long[] min = getCnt(nums, n, true), max = getCnt(nums, n, false);
        long ans = 0;
        for (int i = 0; i < n; i++) ans += (max[i] - min[i]) * nums[i];
        return ans;
    }
    static long[] getCnt(int[] nums,int n, boolean isMin) {
        int[] a = new int[n], b = new int[n];
        Deque<Integer> d = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            // isMin ? nums[d.peekLast()] >= nums[i] : nums[d.peekLast()] <= nums[i]) 表示是否取的是最大值或最小值
            while (!d.isEmpty() && (isMin ? nums[d.peekLast()] >= nums[i] : nums[d.peekLast()] <= nums[i])) d.pollLast();
            a[i] = d.isEmpty() ? -1 : d.peekLast();
            d.addLast(i);
        }
        d.clear();
        for (int i = n - 1; i >= 0; i--) {
            // isMin ? nums[d.peekLast()] >= nums[i] : nums[d.peekLast()] <= nums[i]) 表示是否取的是最大值或最小值
            while (!d.isEmpty() && (isMin ? nums[d.peekLast()] > nums[i] : nums[d.peekLast()] < nums[i])) d.pollLast();
            b[i] = d.isEmpty() ? n : d.peekLast();
            d.addLast(i);
        }
        long[] ans = new long[n];
        for (int i = 0; i < n; i++) ans[i] = (i - a[i]) * 1L * (b[i] - i);
        return ans;
    }
}
