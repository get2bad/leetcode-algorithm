package com.wills.leetcode.simple.dynamic_algorithm.middle.arithmetic_slices;

import org.junit.Test;

/**
 * @ClassName ArithmeticSlices
 * @Date 2022/8/1 13:27
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class ArithmeticSlices {

    private static final int[] source = {1, 2, 3, 4};

    @Test
    public void test() {
        System.out.println(numberOfArithmeticSlices(source));
    }

    public int numberOfArithmeticSlices(int[] nums) {
        int len = nums.length;
        if (len < 3) return 0;

        int diff = nums[0] - nums[1], temp = 0, res = 0;
        // 因为等差数列的长度至少为 3，所以可以从 i=2 开始枚举
        for (int i = 2; i < len; ++i) {
            if (nums[i - 1] - nums[i] == diff) {
                ++temp;
            } else {
                diff = nums[i - 1] - nums[i];
                temp = 0;
            }
            res += temp;
        }
        return res;
    }
}
