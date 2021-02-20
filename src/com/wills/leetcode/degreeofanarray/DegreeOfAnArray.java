package com.wills.leetcode.degreeofanarray;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 王帅
 * @date 2021-02-20 09:16:07
 * @description:
 * 数组的度
 */
public class DegreeOfAnArray {

    /**
     * 给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。
     * 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
     *
     * 示例 1：
     * 输入：[1, 2, 2, 3, 1]
     * 输出：2
     * 解释：
     * 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
     * 连续子数组里面拥有相同度的有如下所示:
     * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
     * 最短连续子数组[2, 2]的长度为2，所以返回2.
     *
     * 示例 2：
     * 输入：[1,2,2,3,1,4,2]
     * 输出：6
     *
     * 提示：
     * nums.length 在1到 50,000 区间范围内。
     * nums[i] 是一个在 0 到 49,999 范围内的整数。
     */
    public static void main(String[] args) {
        // 1,2,2,3,1
        // 1,2,2,3,1,4,2
        int[] source = {1,2,2,3,1};
        System.out.println(findShortestSubArray(source));
    }

    /**
     * 使用哈希表来解决本问题
     * 1. 声明一个哈希表，用来存储相关数据 数据结构为 Map<Integer, int[]>：
     *      其中 key: 数组的元素
     *          value: int[当前出现的次数，出现的索引，结束的索引]
     * 2. 遍历这个数组，然后将相关的元素赋值，按照第1步的规则
     * 3、声明两个变量 ：
     *      maxNum: 出现的最大次数，可能两个有相同的出现次数，但是度不相同的状况 需要单独判断一次
     *      minLen: 我们要取的度(最小数组) 计算公式应该为： arr[2] - arr[1] + 1
     * 4. 遍历这个哈希表，如果当前出现的次数小于之前遍历的，就进行minLen 和 maxNum 重新赋值，因为如果小于，
     *      肯定是当前元素的度比上一次的值大
     * 5. 最后再判断一下 如果 maxNum相同的状态下，可能存在的度不同的问题
     * 6. 返回这个minLen就是我们要的结果
     */
    public static int findShortestSubArray(int[] nums) {
        Map<Integer, int[]> res = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(res.containsKey(nums[i])){
                // 如果存在，就get，然后把最后一个数组元素置位最后的索引
                // int[当前循环的数组元素个数，开始的索引，结束的索引]
                // 度 = 结束索引 - 开始索引 + 1
                res.get(nums[i])[0]++;
                res.get(nums[i])[2] = i;
            } else {
                // 如果不存在 就直接 Put 进去
                res.put(nums[i], new int[]{1, i, i});
            }
        }

        int maxNum = 0, minLen = 0;
        for (Map.Entry<Integer, int[]> entry : res.entrySet()) {
            int[] arr = entry.getValue();
            // 度
            int degree = arr[2] - arr[1] + 1;
            if (maxNum < arr[0]) {
                maxNum = arr[0];
                minLen = degree;
            } else if (maxNum == arr[0]) {
                if (minLen > degree) {
                    minLen = degree;
                }
            }
        }
        return minLen;
    }
}
