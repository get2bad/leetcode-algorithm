package com.wills.leetcode.simple.prefix.middle.continous_subarray;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 王帅
 * @ClassName ContinousSubarray
 * @date 2021/6/2 09:35
 * @Version 1.0
 * @Description
 * 连续的子数组和
 */
public class ContinousSubarray {

    public static void main(String[] args) {
        int[] source = {23,2,6,4,7};
        int k = 9;
        System.out.println(checkSubarraySum(source, k));
    }

    public static boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if(sum % k == 0){
                    return true;
                }
            }
            sum = 0;
        }
        return false;
    }

    public static boolean checkSubarraySum1(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        int length = nums.length;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();    // 以 余数 为键，以 遍历到的下标 为值，存储的map
        map.put(0, -1);
        int remainder = 0;
        for (int i = 0; i < length; i++) {
            remainder = (remainder + nums[i]) % k;  // 计算 加上当前数后，跟 k的倍数 相差多少

            /*
                若 map中存在 当前余数，
                    且 两下标之间，相差距离大于2
                那么，表示 两下标之间 的 连续子数组，为 k的倍数
            */
            if (map.containsKey(remainder)) {
                if (i- map.get(remainder) >= 2) {
                    return true;
                }
            } else {
                map.put(remainder, i);
            }
        }

        return false;
    }
}
