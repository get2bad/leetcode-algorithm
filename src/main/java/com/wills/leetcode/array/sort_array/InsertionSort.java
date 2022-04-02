package com.wills.leetcode.array.sort_array;

/**
 * @ClassName InsertionSort
 * @Date 2022/4/2 09:43
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class InsertionSort {

    // 插入排序 （时间复杂度： N^2 ）
    // 动图地址
    // https://pic.leetcode-cn.com/710dd138492c0da4324657033971f3bee0355514f2ab2834756c988a90398cbb-file_1585624920301
    public int[] sortArrayByInsert(int[] nums) {
        int len = nums.length;
        // 循环不变量：将 nums[i] 插入到区间 [0, i) 使之成为有序数组
        for (int i = 1; i < len; i++) {
            // 先暂存这个元素，然后之前元素逐个后移，留出空位
            int temp = nums[i];
            int j = i;
            // 注意边界 j > 0 依次右移
            while (j > 0 && nums[j - 1] > temp) {
                nums[j] = nums[j - 1];
                j--;
            }
            // 然后交换至目标的位置
            nums[j] = temp;
        }
        return nums;
    }
}
