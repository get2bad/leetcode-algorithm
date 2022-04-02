package com.wills.leetcode.array.sort_array;

/**
 * @ClassName ChooseSort
 * @Date 2022/4/2 09:43
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class ChooseSort {

    // 选择排序：每一轮选择最小元素交换到未排定部分的开头（类似于 冒泡排序 时间复杂度 n^2）
    public int[] sortArrayByChoose(int[] nums) {
        int len = nums.length;
        // 循环不变量：[0, i) 有序，且该区间里所有元素就是最终排定的样子
        for (int i = 0; i < len - 1; i++) {
            // 选择区间 [i, len - 1] 里最小的元素的索引，交换到下标 i
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            swap(nums, i, minIndex);
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
