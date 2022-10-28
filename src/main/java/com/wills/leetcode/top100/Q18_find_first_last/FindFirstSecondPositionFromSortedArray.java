package com.wills.leetcode.top100.Q18_find_first_last;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName FindFirstSecondPositionFromSortedArray
 * @Date 2022/10/28 11:15
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class FindFirstSecondPositionFromSortedArray {

    private static final int[] source = {5, 7, 7, 8, 8, 10};
//    private static final int target = 8;
    private static final int target = 6;

    @Test
    public void test() {
        System.out.println(Arrays.toString(searchRange(source, target)));
    }

    /**
     * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
     * 如果数组中不存在目标值 target，返回[-1, -1]。
     * 你必须设计并实现时间复杂度为O(log n)的算法解决此问题。
     * <p>
     * 示例 1：
     * 输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     * <p>
     * 示例2：
     * 输入：nums = [5,7,7,8,8,10], target = 6
     * 输出：[-1,-1]
     * <p>
     * 示例 3：
     * 输入：nums = [], target = 0
     * 输出：[-1,-1]
     * 使用分治法 + 二分法 可以解决本题
     * 我们需要找到最大最小的索引，我们可以变通为 寻找 target / target + 1 即可
     */
    public int[] searchRange(int[] nums, int target) {
        // 分治
        int l = search(nums, target);
        int r = search(nums, target + 1);

        return l == nums.length || l >= r ? new int[]{-1, -1} : new int[]{l, r - 1};
    }

    // 由于不是查找目标值 所以不要直接返回 return mid
    // 查找范围就要使用如下模板
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] < target) left = mid + 1;
            else right = mid;
        }
        return left;
    }
}
