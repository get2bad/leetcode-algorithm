package com.wills.leetcode.other.easy.two_sum;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TwoSum
 * @Date 2022/3/7 11:47
 * @Author 王帅
 * @Version 1.0
 * @Description
 * 两数之和
 */
public class TwoSum {

    public static void main(String[] args) {

    }


    // 巧用哈希表来解决
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer,Integer> source = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if(source.containsKey(diff)){
                res[0] = source.get(diff);
                res[1] = i;
            }else{
                source.put(nums[i], i);
            }
        }
        return res;
    }
}
