package com.wills.leetcode.simple.other.middle.range_sum_query;

/**
 * @author 王帅
 * @date 2021-03-01 09:51:36
 * @description:
 * 区域和检索 - 数组不可变
 */
class NumArray {

    int[] sums;

//    public NumArray(int[] nums) {
//        sums = nums;
//    }

//    public int sumRange(int i, int j) {
//        int sum = 0;
//        while(i <=j){
//            sum += sums[i++];
//        }
//        return sum;
//    }

    /**
     * 区域和检索：
     * 在类构造方法在注入 数组时 就进行叠加操作
     * 步骤：
     * 1.先获取数组长度，创建一个新的数组用来存储数组的每一步的总和
     * 2.然后遍历传入的长度，向 新数组依次注入 sums[i + 1] = sums[i] + nums[i];
     * 3. 调用 sumRange 直接调动新的数组相减相关的索引值即可
     */
    public NumArray(int[] nums) {
        int n = nums.length;
        sums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        return sums[j + 1] - sums[i];
    }
}

public class RangeSumQuery {

    /**
     * 给定一个整数数组  nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点。=
     *
     * 实现 NumArray 类：
     * NumArray(int[] nums) 使用数组 nums 初始化对象=====
     * int sumRange(int i, int j) 返回数组 nums 从索引 i 到 j（i ≤ j）范围内元素的总和，
     * 包含 i、j 两点（也就是 sum(nums[i], nums[i + 1], ... , nums[j])）
     *
     *
     * 示例：
     * 输入：
     * ["NumArray", "sumRange", "sumRange", "sumRange"]
     * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
     * 输出：
     * [null, 1, -1, -3]
     * 解释：
     * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
     * numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
     * numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
     * numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
     */
    public static void main(String[] args) {
        int[] source = {-2, 0, 3, -5, 2, -1};
        NumArray numArray = new NumArray(source);
        System.out.println(numArray.sumRange(0, 2));
    }
}
