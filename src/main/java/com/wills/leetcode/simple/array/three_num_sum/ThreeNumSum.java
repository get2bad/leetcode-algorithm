package com.wills.leetcode.simple.array.three_num_sum;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName ThreeNumSum
 * @Date 2022/6/20 10:35
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class ThreeNumSum {

    int[] source = new int[]{-1,0,1,2,-1,-4};

    @Test
    public void test(){
        System.out.println(threeSum(source));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        if (len < 3) return Collections.EMPTY_LIST;
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) return res;

            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int curr = nums[i];
            // 遍历 + 双指针
            int left = i + 1, right = len - 1;
            while (left < right) {
                int tmp = curr + nums[left] + nums[right];
                if (tmp == 0) {
                    res.add(Arrays.asList(curr, nums[left], nums[right]));
                    // 排除重复的元素 重点！因为题目要求
                    while (left < right && nums[left + 1] == nums[left]) ++left;
                    while (left < right && nums[right - 1] == nums[right]) --right;
                    // 过滤重复元素后，要将指针进行位移，因为目前左右指针依旧指向之前/重复的元素
                    ++left;
                    --right;
                } else if (tmp < 0) {
                    // 如果小于0，就，右移左指针，因为数组是有序的，要到大于0的元素来叠加
                    ++left;
                } else {
                    --right;
                }
            }
        }
        return res;
    }
}
