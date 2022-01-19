package com.wills.leetcode.other.middle.single_number;

/**
 * @author 王帅
 * @date 2021-03-08 10:10:40
 * @description:
 * 只出现一次的数字
 */
public interface SingleNumber {

    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。
     * 找出那个只出现了一次的元素。
     *
     * 说明：
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     *
     * 示例 1:
     * 输入: [2,2,1]
     * 输出: 1
     *
     * 示例 2:
     * 输入: [4,1,2,1,2]
     * 输出: 4
     */
    public static void main(String[] args) {
        int[] source = {4,1,2,1,2};
        System.out.println(singleNumber(source));
    }

    /**
     * 不使用额外的空间来实现：
     * 方案：
     * 1. 将数组排序，使之有序，然后遍历这个数组，如果当前遍历的数字和之前之后的不同，那么就是只出现一次的数字
     */
    public static int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }

}
