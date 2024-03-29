package com.wills.leetcode.simple.array.sort_array.source.impl;

import com.wills.leetcode.simple.array.sort_array.source.Sort;
import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName QuickSort
 * @Date 2022/4/2 09:33
 * @Author 王帅
 * @Version 1.0
 * @Description
 * 助记动图：
 * https://www.runoob.com/wp-content/uploads/2019/03/quickSort.gif
 */
public class QuickSort implements Sort {

    @Test
    public void test() {
        int[] source = {4, 6, 78, 2, 1, 6, 7, 7, 8, 342, 1};
        System.out.println(Arrays.toString(source));
        quickSort(source, 0, source.length - 1);
        System.out.println("=======================================");
        System.out.println(Arrays.toString(source));
        System.out.println("=======================================");
    }

    // 快速排序
    @Override
    public int[] sort(int[] nums) {
        return quickSort(nums, 0, nums.length - 1);
    }

    public int[] quickSort(int[] array, int left, int right) {
        if (left < right) {
            // 获取划分子数组的位置
            int position = partition(array, left, right);
            // 左子数组递归调用
            quickSort(array, left, position - 1);
            // 右子数组递归调用
            quickSort(array, position + 1, right);
        }
        return array;
    }

    public int partition(int[] array, int left, int right) {
        // 取最后一个元素作为中心元素
        int pivot = array[right];
        // 定义指向比中心元素大的指针，首先指向第一个元素
        int pointer = left;
        // 遍历数组中的所有元素，将比中心元素大的放在右边，比中心元素小的放在左边
        for (int i = left; i < right; i++) {
            if (pivot >= array[i]) {
                // 将比中心元素小的元素和指针指向的元素交换位置
                // 如果第一个元素比中心元素小，这里就是自己和自己交换位置，指针和索引都向下一位移动
                // 如果元素比中心元素大，索引向下移动，指针指向这个较大的元素，直到找到比中心元素小的元素，并交换位置，指针向下移动
                swap(array,i,pointer++);
            }
            System.out.println(Arrays.toString(array));
        }
        // 将中心元素和指针指向的元素交换位置
        // 将基准值放到中间，用来区分 左边是比基准值小的，右边是比基准值大的
        swap(array,pointer,right);
        return pointer;
    }

    public void swap(int[] nums, int index1, int index2) {
        int tmp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = tmp;
    }
}
