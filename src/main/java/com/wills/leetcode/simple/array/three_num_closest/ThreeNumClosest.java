package com.wills.leetcode.simple.array.three_num_closest;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName ThreeNumClosest
 * @Date 2022/6/20 11:22
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class ThreeNumClosest {

    int[] source = new int[]{0, 2, 1, -3};
    int target = 1;

    @Test
    public void test() {
        System.out.println(threeSumClosest(source, target));
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        int res = nums[0] + nums[1] + nums[2];
        // 然后相关操作
        for (int i = 0; i < len; i++) {
            int left = i + 1, right = len - 1;
            while (left < right) {
                int tmp = nums[left] + nums[right] + nums[i];
                int lastDiff = Math.abs(target - res), currDiff = Math.abs(target - tmp);
                if (currDiff < lastDiff) {
                    res = tmp;
                } else if (tmp  < target) {
                    left++;
                } else right--;
            }
        }
        return res;
    }
}
