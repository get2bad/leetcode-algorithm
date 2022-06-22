package com.wills.leetcode.array.sorted_rotated_array;

import org.junit.Test;

/**
 * @author 王帅
 * @date 2022-06-21 20:26:57
 * @description:
 */
public class SortedRotatedArray {

    private static int[] source = {4, 5, 6, 7, 0, 1, 2};
    private static int target = 0;

    @Test
    public void test() {
        System.out.println(search(source, target));
    }

    // 我们使用二分查找法来搞定这道题
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            // 如果是递增的
            if(nums[mid] < nums[right]){
                if(nums[mid] < target && target <= nums[right])
                    left = mid+1;
                else
                    right = mid-1;
            }else if(nums[mid] > nums[right]){
                if(nums[left] <= target && target < nums[mid])
                    right = mid-1;
                else
                    left = mid+1;
            }else return mid;
        }
        return -1;
    }
}
