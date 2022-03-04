package com.wills.leetcode.other.middle.sum_of_subarray_ranges;

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
        int[] source = {4,-2,-3,4,1};
        long res = subArrayRanges(source);
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
}
