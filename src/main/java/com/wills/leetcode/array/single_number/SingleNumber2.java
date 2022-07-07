package com.wills.leetcode.array.single_number;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName SingleNumber
 * @Date 2022/7/7 11:21
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class SingleNumber2 {

    //    private static int[] source = {2, 2, 3, 2};
    private static int[] source = {0, 1, 0, 1, 0, 1, 99};

    @Test
    public void test() {
        System.out.println(singleNumber(source));
    }

    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int cnt = map.getOrDefault(nums[i], 0) + 1;
            if (i == len - 1 && cnt != 3) return nums[i];
            else {
                if (cnt != 3 && nums[i] != nums[i + 1]) return nums[i];
                map.put(nums[i], cnt);
            }
        }
        return -1;
    }
}
