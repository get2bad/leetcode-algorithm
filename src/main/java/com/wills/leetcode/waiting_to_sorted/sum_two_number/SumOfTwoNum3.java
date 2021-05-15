package com.wills.leetcode.waiting_to_sorted.sum_two_number;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 王帅
 * @date 2021-03-13 09:15:36
 * @description:
 * LeetCode 167 两数之和. 输入有序数组
 *
 * 同 两数之和的第一题，本题不做过多的介绍
 */
public class SumOfTwoNum3 {

    /**
     * 给定一个已按照 升序排列  的整数数组 numbers ，
     * 请你从数组中找出两个数满足相加之和等于目标数 target 。
     *
     * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，
     * 所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。
     *
     * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
     *
     * 示例 1：
     * 输入：numbers = [2,7,11,15], target = 9
     * 输出：[1,2]
     * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
     *
     * 示例 2：
     * 输入：numbers = [2,3,4], target = 6
     * 输出：[1,3]
     *
     * 示例 3：
     * 输入：numbers = [-1,0], target = -1
     * 输出：[1,2]
     */
    public static void main(String[] args) {
        int[] source = {2,7,11,15};
        int target = 9;
        Arrays.stream(twoSum(source, target)).forEach(System.out::println);
    }

    public static int[] twoSum(int[] numbers, int target) {
        int[] res = {0,0};
        Map<Integer,Integer> source = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if(source.containsKey(numbers[i])){
                return new int[]{source.get(numbers[i]) + 1,i + 1};
            }
            source.put(target - numbers[i],i);
        }

        return res;
    }
}
