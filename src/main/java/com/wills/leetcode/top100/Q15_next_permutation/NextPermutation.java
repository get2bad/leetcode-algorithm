package com.wills.leetcode.top100.Q15_next_permutation;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName NextPermutation
 * @Date 2022/10/27 13:59
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class NextPermutation {

    private static int[] nums = {1, 2, 3};

    @Test
    public void test() {
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 整数数组的一个 排列 就是将其所有成员以序列或线性顺序排列。
     * <p>
     * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
     * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，
     * 如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。
     * 如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
     * <p>
     * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
     * 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
     * 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
     * 给你一个整数数组 nums ，找出 nums 的下一个排列。
     * <p>
     * 示例 1：
     * 输入：nums = [1,2,3]
     * 输出：[1,3,2]
     * <p>
     * 示例 2：
     * 输入：nums = [3,2,1]
     * 输出：[1,2,3]
     * <p>
     * 示例 3：
     * 输入：nums = [1,1,5]
     * 输出：[1,5,1]
     *
     * 主要思想，
     * 1. 分别求出 len - 2递减序列的头 以及 len - 1 递减序列的头(就是为了找到第一大和第二大的位置，然后调换)
     * 2. 最后reverse一下(防止是最大值，要输出最小的)
     */
    public void nextPermutation(int[] nums) {
        int len = nums.length, i = len - 2;
        // 寻找非降序的 a[i - 1] (当前 i 比 后面的小为止)
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;

        // 如果是顺序的数组 那么 i 就是1（len = 3）
        if (i >= 0) {
            int j = len - 1;
            // 当前值跟倒序元素比较大小，如果 i 元素比 倒序指针元素大，倒序指针减1
            // 寻找 a[j]
            while (j >= 0 && nums[i] >= nums[j]) j--;

            // 交换 a[i - 1] 和 a[j]
            swap(nums, i, j);
        }
        // 翻转从 i+1后的数组
        reverse(nums, i + 1);
    }

    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}