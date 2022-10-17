package com.wills.leetcode.simple.array.remove_repeatable_array;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName RemoveRepeatableArray
 * @Date 2022/7/1 11:35
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class RemoveRepeatableArray2 {

    //    private static int[] source = {1, 1, 1, 2, 2, 3};
    private static int[] source = {0, 0, 1, 1, 1, 1, 2, 3, 3};

    @Test
    public void test() {
        System.out.println(removeDuplicates(source));
        System.out.println(Arrays.toString(source));
    }

    /**
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，
     * 使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
     */
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int n : nums)
            if (i < 2 || n > nums[i - 2])
                nums[i++] = n;
        return i;
    }
}
