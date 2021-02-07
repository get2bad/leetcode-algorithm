package com.wills.leetcode;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
 * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 你可以假设数组中无重复元素。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,6], 5
 * 输出: 2
 *
 * 示例 2:
 * 输入: [1,3,5,6], 2
 * 输出: 1
 *
 * 示例 3:
 * 输入: [1,3,5,6], 7
 * 输出: 4
 *
 * 示例 4:
 * 输入: [1,3,5,6], 0
 * 输出: 0
 */
public class SearchIndex {

    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        int target = 5;
        System.out.println(searchInsert(nums, target));
    }

    /**
     * 使用二分查找法
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = nums.length -1, res = n;
        while(left <= right){
            // 确定中间数， 为什么要 +left? 因为要调整最新要遍历的数组到 1/2的位置
            int mid = ((right - left) >> 1) + left;
            if(target <= nums[mid]){
                // |___left____|___right____|
                //      ↑ 取目标数组1/2左边的作为新的要遍历的数组
                right = mid -1;
                res = mid;
            } else {
                // |___left____|___right____|
                //                  ↑ 取目标数组1/2右边的作为新的要遍历的数组
                left = mid + 1;
            }

        }
        return res;
    }

    /**
     * 笨方法解决，挨个遍历
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == target){
                return i;
            } else{
                if(nums[0]> target) return 0;
                if(i+1 >= nums.length) return nums.length;
                if(nums[i] < target && nums[i+1] > target){
                    return i +1;
                }
            }
        }
        return 0;
    }
}
