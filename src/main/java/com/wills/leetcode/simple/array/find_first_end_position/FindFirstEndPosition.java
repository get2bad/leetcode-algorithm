package com.wills.leetcode.simple.array.find_first_end_position;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName FindFirstEndPosition
 * @Date 2022/6/22 10:23
 * @Author 王帅
 * @Version 1.0
 * @Description 在排序数组中查找元素的第一个和最后一个位置
 */
public class FindFirstEndPosition {

    private static int[] source = {5, 7, 7, 8, 8, 10};
    private static int target = 8;

    @Test
    public void test() {
        System.out.println(Arrays.toString(searchRange(source, target)));
    }

    public int[] searchRange(int[] nums, int target) {
        int l = search(nums, target);
        int r = search(nums, target + 1);
        return l == nums.length || l >= r ? new int[]{-1, -1} : new int[]{l, r - 1};
    }

    private int search(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public int searchRight(int[] nums, int target) {
        int left = 0, right = nums.length - 1, rightBorder = -1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (target < nums[mid]) {
                right = mid - 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
                rightBorder = left;
            }
        }
        return rightBorder;
    }
}
