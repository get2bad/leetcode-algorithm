package com.wills.leetcode.simple.array.sort_array.source.impl;

import com.wills.leetcode.simple.array.sort_array.source.Sort;

import java.util.Arrays;

/**
 * @ClassName MergeSort
 * @Date 2022/4/2 09:42
 * @Author 王帅
 * @Version 1.0
 * @Description
 * 助记理解动图:
 * https://www.runoob.com/wp-content/uploads/2019/03/mergeSort.gif
 */
public class MergeSort implements Sort {

    // 归并排序
    @Override
    public int[] sort(int[] nums) {
        int len = nums.length;
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(nums, nums.length);
        if (len < 2) {
            return arr;
        }
        int middle = (int) Math.floor(len / 2);

        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, len);

        return merge(sort(left), sort(right));
    }

    protected int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        while (left.length > 0 && right.length > 0) {
            if (left[0] <= right[0]) {
                result[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                result[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }

        while (left.length > 0) {
            result[i++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        }

        while (right.length > 0) {
            result[i++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }

        return result;
    }
}
