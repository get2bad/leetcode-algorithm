package com.wills.leetcode.simple.array.find_minimum_num_in_array;

import org.junit.Test;

/**
 * @ClassName FindMinimumNumInArray
 * @Date 2022/7/11 14:37
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class FindMinimumNumInArray {

    private static int[] source = {3, 1, 2};
//    private static int[] source = {3, 4, 5, 1, 2};
//    private static int[] source = {4, 5, 6, 7, 0, 1, 2};

    @Test
    public void test() {
        System.out.println(findMin(source));
    }

    public int findMin(int[] nums) {
        int len = nums.length, left = 0, right = len - 1;
        while (left < right) {
            int middle = (left + right) >>> 1;
            if (nums[middle] > nums[right]) {
                left = middle + 1;
            } else if (nums[middle] <= nums[right]) {
                right = middle;
            }
        }
        return nums[left];
    }
}
