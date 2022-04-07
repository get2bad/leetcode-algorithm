package com.wills.leetcode.array.sort_array.source.impl;

import com.wills.leetcode.array.sort_array.source.Sort;

import java.util.Random;

/**
 * @ClassName QuickSort
 * @Date 2022/4/2 09:33
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class QuickSort implements Sort {

    // 列表大小等于或小于该大小，将优先于 mergeSort 使用插入排序
    private static final int INSERTION_SORT_THRESHOLD = 7;
    // 快速排序
    @Override
    public int[] sort(int[] nums) {
        int len = nums.length;
        quickSort(nums, 0, len - 1);
        return nums;
    }

    private void quickSort(int[] nums, int left, int right) {
        // 小区间使用插入排序
        if (right - left <= INSERTION_SORT_THRESHOLD) {
            insertionSort(nums, left, right);
            return;
        }

        int pIndex = partition(nums, left, right);
        quickSort(nums, left, pIndex - 1);
        quickSort(nums, pIndex + 1, right);
    }

    private static final Random RANDOM = new Random();
    private int partition(int[] nums, int left, int right) {
        int randomIndex = RANDOM.nextInt(right - left + 1) + left;
        swap(nums, left, randomIndex);

        // 基准值
        int pivot = nums[left];
        int lt = left;
        // 循环不变量：
        // all in [left + 1, lt] < pivot
        // all in [lt + 1, i) >= pivot
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < pivot) {
                lt++;
                swap(nums, i, lt);
            }
        }
        swap(nums, left, lt);
        return lt;
    }

    /**
     * 对数组 arr 的子区间 [left, right] 使用插入排序
     *
     * @param arr   给定数组
     * @param left  左边界，能取到
     * @param right 右边界，能取到
     */
    private void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int temp = arr[i];
            int j = i;
            while (j > left && arr[j - 1] > temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    // 就是简单的交换值
    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
