package com.wills.leetcode.top100.Q4_median_two_arrays;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName MedianTwoArrays
 * @Date 2022/10/19 11:06
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class MedianTwoArrays {

//    private static final int[] nums1 = {1, 3};
//    private static final int[] nums2 = {2};

    private static final int[] nums1 = {1, 2};
    private static final int[] nums2 = {3, 4};

    @Test
    public void test() {
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    /**
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组nums1 和nums2。请你找出并返回这两个正序数组的 中位数
     * 算法的时间复杂度应该为 O(log (m+n)) 。
     *
     * 示例 1：
     * 输入：nums1 = [1,3], nums2 = [2]
     * 输出：2.00000
     * 解释：合并数组 = [1,2,3] ，中位数 2
     *
     * 示例 2：
     * 输入：nums1 = [1,2], nums2 = [3,4]
     * 输出：2.50000
     * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
     *
     * 很简单的一个求中间数字的问题，要解答出本题要先明确 "中位数" 的概念
     * 所谓中位数 就是指：
     *  对于有限的数集，可以通过把所有观察值高低排序后找出正中间的一个作为中位数。
     *  如果观察值有偶数个，通常取最中间的两个数值的平均数作为中位数
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length, totalLen = len1 + len2, middle = totalLen / 2;
        int[] nums = new int[len1 + len2];
        System.arraycopy(nums1, 0, nums, 0, len1);
        System.arraycopy(nums2, 0, nums, len1, len2);
        Arrays.sort(nums);
        // 如果等于1 说明可以直接取到中位数
        if (totalLen % 2 == 1) return nums[middle];
        // 如果等于0 ，就要取两个数的中位数了
        return (double)(nums[middle - 1] + nums[middle]) / 2;
    }
}
