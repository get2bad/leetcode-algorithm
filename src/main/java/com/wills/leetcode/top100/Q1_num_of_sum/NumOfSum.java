package com.wills.leetcode.top100.Q1_num_of_sum;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName NumOfSum
 * @Date 2022/10/17 10:49
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class NumOfSum {

    private static final int[] source = {2, 7, 11, 15};
    private static final int target = 9;

    @Test
    public void test() {
        System.out.println(Arrays.toString(twoSum(source, target)));
    }

    /**
     * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，
     * 并返回它们的数组下标你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现
     * 你可以按任意顺序返回答案。
     * <p>
     * 示例 1：
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     * <p>
     * 示例 2：
     * 输入：nums = [3,2,4], target = 6
     * 输出：[1,2]
     * <p>
     * 示例 3：
     * 输入：nums = [3,3], target = 6
     * 输出：[0,1]
     *
     * 解题思路： 使用 哈希法，暂存
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i],diff = target - curr;
            if(map.containsKey(diff)){
                return new int[]{map.get(diff), i};
            }else{
                map.put(curr,i);
            }
        }

        return new int[2];
    }
}
