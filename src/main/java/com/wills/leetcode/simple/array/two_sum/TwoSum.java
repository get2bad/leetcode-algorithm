package com.wills.leetcode.simple.array.two_sum;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TwoSum
 * @Date 2022/9/27 15:25
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class TwoSum {

//    private static final int[] source = {2, 7, 11, 15};
//    private static final int target = 9;

//    private static final int[] source = {3,2,4};
//    private static final int target = 6;

    private static final int[] source = {3,3};
    private static final int target = 6;

    @Test
    public void test() {
        System.out.println(Arrays.toString(twoSum(source, target)));
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (map.containsKey(diff)) {
                return new int[]{map.get(diff), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[2];
    }
}
