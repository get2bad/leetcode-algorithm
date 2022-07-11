package com.wills.leetcode.array.rotate_array;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName RotateArray
 * @Date 2022/7/11 15:20
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class RotateArray {

//    private static int[] source = {1, 2, 3, 4, 5, 6, 7};
//    private static int k = 3;

    private static int[] source = {-1, -100, 3, 99};
    private static int k = 2;

    @Test
    public void test() {
        rotate(source, k);
        System.out.println(Arrays.toString(source));
    }

    public void rotate(int[] nums, int k) {
        int len = nums.length;
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            // 重新赋值
            res[(i + k) % len] = nums[i];
        }
        System.arraycopy(res, 0, nums, 0, len);
    }
}
