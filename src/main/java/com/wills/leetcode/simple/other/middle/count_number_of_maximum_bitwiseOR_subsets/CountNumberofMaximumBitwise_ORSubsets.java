package com.wills.leetcode.simple.other.middle.count_number_of_maximum_bitwiseOR_subsets;

/**
 * @ClassName CountNumberofMaximumBitwise_ORSubsets
 * @Date 2022/3/15 09:22
 * @Author 王帅
 * @Version 1.0
 * @Description 统计按位或能得到最大值的子集数目
 */
public class CountNumberofMaximumBitwise_ORSubsets {

    public static void main(String[] args) {
        CountNumberofMaximumBitwise_ORSubsets target = new CountNumberofMaximumBitwise_ORSubsets();
        int[] source = {1, 2, 3};
        int res = target.countMaxOrSubsets(source);
        System.out.println(res);
    }

    /**
     * 给你一个整数数组 nums ，请你找出 nums 子集 按位或 可能得到的 最大值 ，并返回按位或能得到最大值的 不同非空子集的数目 。
     * 如果数组 a 可以由数组 b 删除一些元素（或不删除）得到，则认为数组 a 是数组 b 的一个 子集 。
     * 如果选中的元素下标位置不一样，则认为两个子集 不同 。
     * 对数组 a 执行 按位或 ，结果等于 a[0] OR a[1] OR ... OR a[a.length - 1]（下标从 0 开始）。
     *
     * 示例 1：
     * 输入：nums = [3,1]
     * 输出：2
     * 解释：子集按位或能得到的最大值是 3 。有 2 个子集按位或可以得到 3 ：
     * - [3]
     * - [3,1]
     *
     * 示例 2：
     * 输入：nums = [2,2,2]
     * 输出：7
     * 解释：[2,2,2] 的所有非空子集的按位或都可以得到 2 。总共有 2^3 - 1 = 7 个子集。
     *
     * 示例 3：
     * 输入：nums = [3,2,1,5]
     * 输出：6
     * 解释：子集按位或可能的最大值是 7 。有 6 个子集按位或可以得到 7 ：
     * - [3,5]
     * - [3,1,5]
     * - [3,2,5]
     * - [3,2,1,5]
     * - [2,5]
     * - [2,1,5]
     */
    /**
     * 令 len 为 nums 的长度，利用 len 不超过 16，我们可以使用一个 mask 数值来代指 nums 的使用情况（子集状态）。
     *
     * 假设当前子集状态为 state，state 为一个仅考虑低 len 位的二进制数，
     * 当第 k 位为 1，代表 nums[k] 参与到当前的按位或运算，当第 k 位为 0
     * 代表 nums[i] 不参与到当前的按位或运算。
     */
    public int countMaxOrSubsets(int[] nums) {
        // mask 代表最大的可能种类 有 2^len 的可能性
        int len = nums.length, mask = 1 << len;
        int max = Integer.MIN_VALUE, res = 0;
        for (int i = 0; i < mask; i++) {
            int cur = 0;
            for (int j = 0; j < len; j++) {
                int source = i >> j;
                if ((source & 1) == 1) cur |= nums[j];
            }
            if (cur > max) {
                max = cur;
                res = 1;
            } else if (cur == max) {
                res++;
            }
        }
        return res;
    }
}
