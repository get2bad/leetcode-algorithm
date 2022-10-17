package com.wills.leetcode.simple.most_element;

import java.util.Arrays;
import java.util.Random;

/**
 * @author 王帅
 * @date 2021-03-13 09:31:48
 * @description:
 */
public class MostElement {


    /**
     * 给定一个大小为 n 的数组，找到其中的多数元素。
     * 多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     *
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     *
     * 示例 1：
     * 输入：[3,2,3]
     * 输出：3
     *
     * 示例 2：
     * 输入：[2,2,1,1,1,2,2]
     * 输出：2
     */
    public static void main(String[] args) {
        int[] source = {2,2,1,1,1,2,2};
        System.out.println(majorityElement3(source));
    }

    /**
     * 排序输出算法
     */
    public static int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 随机算法
     */
    public static int majorityElement1(int[] nums) {
        Random random = new Random();
        int targetCount = nums.length / 2;
        while(true){
            int source = nums[random.nextInt(nums.length - 1)];
            if(targetCount < random(nums,source)){
                return source;
            }
        }
    }

    public static int random(int[] nums,int num){
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == num){
                count++;
            }
        }
        return count;
    }

    /**
     * 分治 + 递归算法法
     */
    public static int majorityElement2(int[] nums) {
        return majorityElement2_1(nums,0,nums.length -1);
    }

    public static int majorityElement2_1(int[] nums,int low,int high) {
        if (low == high) {
            return nums[low];
        }

        // 分而治之
        int middle = (high - low) / 2 + low;
        int left = majorityElement2_1(nums,low,middle);
        int right = majorityElement2_1(nums,middle +1,high);

        if(left == right) return left;

        int leftCount = countInrange(nums,left,low,high);
        int rightCount = countInrange(nums,right,low,high);

        return leftCount > rightCount ? left : right;
    }

    public static int countInrange(int[] nums, int num, int low,int high){
        int count = 0;
        for (int i = low; i <= high; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    /**
     * 投票算法
     */
    public static int majorityElement3(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if(count == 0){
                candidate = num;
            }
            count += (num==candidate)? 1: -1;
        }

        return candidate;
    }

}
