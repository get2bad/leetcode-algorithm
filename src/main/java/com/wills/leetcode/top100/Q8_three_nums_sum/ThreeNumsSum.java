package com.wills.leetcode.top100.Q8_three_nums_sum;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName ThreeNumsSum
 * @Date 2022/10/18 17:27
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class ThreeNumsSum {

    private static final int[] source = {-1, 0, 1, 2, -1, -4};

    @Test
    public void test() {
        System.out.println(threeSum(source));
    }

    /**
     * 思路：
     * 1. 先对传入的数组进行排序
     * 2. 遍历这个数组
     * 3. 如果 数组伊始是正数 >0 则 直接返回
     * 4. 否则就 使用 快慢指针法，分别从当前位置 + 1 到 结尾进行叠加，如果遇到等于0 就加入返回的集合中
     *      (一定要过滤重复的，因为题目要求)
     */
    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        if (len < 3) return Collections.EMPTY_LIST;
        List<List<Integer>> res = new ArrayList<>();
        // 排序，然后使用遍历 + 快慢指针来解决本题
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) return res;
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int curr = nums[i];
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
                } else if (tmp < 0) ++left; //  如果小于0，就，右移左指针，因为数组是有序的，要到大于0的元素来叠加
                else --right;
            }
        }


        return res;
    }
}
