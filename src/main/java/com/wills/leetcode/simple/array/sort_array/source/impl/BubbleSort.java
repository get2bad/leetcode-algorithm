package com.wills.leetcode.simple.array.sort_array.source.impl;

import com.wills.leetcode.simple.array.sort_array.source.Sort;

/**
 * @ClassName BubbleSort
 * @Date 2022/4/2 09:44
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class BubbleSort implements Sort {


    // 暴力法 冒泡排序 (超时)
    @Override
    public int[] sort(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if(nums[i] > nums[j]){
                    swap(nums,i,j);
                }
            }
        }
        return nums;
    }

    // 就是简单的交换值
    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
