package com.wills.leetcode.other.easy.contains_duplicate;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ContainsDuplicate2
 * @Date 2022/1/19 09:39
 * @Author 王帅
 * @Version 1.0
 * @Description
 * 存在重复的数字
 */
public class ContainsDuplicate2 {

    /**
     * 给你一个整数数组nums 和一个整数k ，判断数组中是否存在两个 不同的索引 i 和 j  ，满足 nums[i] == nums[j]
     * 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
     *
     * 示例1：
     * 输入：nums = [1,2,3,1], k = 3
     * 输出：true
     *
     * 示例 2：
     * 输入：nums = [1,0,1,1], k = 1
     * 输出：true
     *
     * 示例 3：
     * 输入：nums = [1,2,3,1,2,3], k = 2
     * 输出：false
     * @param args
     */
    public static void main(String[] args) {
//        int[] nums = {1,2,3,1};
//        int[] nums = {1,2,3,1,2,3};
        int[] nums = {1,0,1,1};
//        int k = 3;
//        int k = 2;
        int k = 1;
        System.out.println(containsNearbyDuplicate(nums, k));

    }

    /**
     * 思考🤔：
     * 我们是否可以使用哈希表来解决问题，key为当前遍历的数字 value为当前的索引，
     * 如果遇到了就计算两者差值的绝对值，如果满足条件，就返回true，否则就进行继续遍历
     * @param nums
     * @param k
     * @return
     */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer,Integer> source = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 如果不存在，就向hash表里面增加新元素
            if(!source.containsKey(nums[i])){
                source.put(nums[i],i);
            }else{
                Integer preIndex = source.get(nums[i]);
                int difference = Math.abs(i - preIndex);
                if(difference <= k){
                    return true;
                }else{
                    // 如果匹配不成功，就更新最新的索引值
                    source.put(nums[i],i);
                }
            }
        }
        return false;
    }

    public static boolean containsNearbyDuplicate1(int[] nums, int k) {
        Map<Integer,Integer> source = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 如果存在，就向判断一下
            if(source.containsKey(nums[i])){
                Integer preIndex = source.get(nums[i]);
                int difference = Math.abs(i - preIndex);
                if(difference <= k){
                    return true;
                }
            }
            // 如果匹配不成功，就更新最新的索引值
            source.put(nums[i],i);
        }
        return false;
    }
}
