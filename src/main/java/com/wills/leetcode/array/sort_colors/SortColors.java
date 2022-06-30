package com.wills.leetcode.array.sort_colors;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName SortColors
 * @Date 2022/6/30 10:44
 * @Author 王帅
 * @Version 1.0
 * @Description 颜色分类
 */
public class SortColors {

    private static int[] source = {2, 0, 2, 1, 1, 0};

    @Test
    public void test() {
        sortColors(source);
        System.out.println(Arrays.toString(source));
    }

    public void sortColors(int[] nums) {
        int i = 0;  // 临时变量和，遍历位号
        int left = 0, right = nums.length - 1; // 左指针、右指针
        while (i < nums.length && i <= right) {
            if (nums[i] == 0) {
                swap(nums, left++, i++);
            } else if (nums[i] == 1) {  //
                i++;
            } else if (nums[i] == 2) {
                swap(nums, right--, i);
            }
        }
    }

    // 使用快速排序算法来搞定(最终以超时而潦草收场)
//    public void sortColors(int[] nums) {
//        sortColors(nums,0,nums.length - 1);
//    }

    public void sortColors(int[] nums, int left, int right) {
        if (left < right) {
            int partition = partition(nums, left, right);
            sortColors(nums, 0, partition - 1);
            sortColors(nums, partition + 1, right);
        }
    }

    // 快速排序的最终目的就是 将这一小部分比当前基准值小的往左放，比基准值大的往右放
    private int partition(int[] nums, int left, int right) {
        int privot = nums[right], index = left;
        for (int i = left; i < right; i++) {
            if (nums[i] < privot) {
                swap(nums, i, index);
                index++;
            }
        }
        swap(nums, right, index);
        return index;

    }

    public void swap(int[] nums, int index1, int index2) {
        int tmp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = tmp;
    }
}
