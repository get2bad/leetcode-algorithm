package com.wills.leetcode.waiting_to_sorted.sum_two_number;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 王帅
 * @date 2021-03-08 09:54:44
 * @description:
 * 两数之和
 */
public class SumOfTwoNum {

    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，
     * 请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     * 你可以按任意顺序返回答案。
     *
     * 示例 1：
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     *
     * 示例 2：
     * 输入：nums = [3,2,4], target = 6
     * 输出：[1,2]
     *
     * 示例 3：
     * 输入：nums = [3,3], target = 6
     * 输出：[0,1]
     */
    public static void main(String[] args) {
        int[] source = {2,7,11,15};
        Arrays.stream(twoSum1(source, 9)).forEach(System.out::println);
    }

    /**
     * 暴力枚举法
     */
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int res = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if(res == nums[j]){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    /**
     * 哈希表法
     */
    public static int[] twoSum1(int[] nums, int target) {
        Map<Integer,Integer> source = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(source.containsKey(target - nums[i])){
                return new int[]{source.get(target - nums[i]),i};
            }
            source.put(nums[i],i);
        }
        return null;
    }
}


