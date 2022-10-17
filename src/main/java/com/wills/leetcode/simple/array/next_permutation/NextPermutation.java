package com.wills.leetcode.simple.array.next_permutation;

import org.junit.Test;

import java.util.Arrays;


/**
 * @ClassName NextPermutation
 * @Date 2022/6/21 16:35
 * @Author 王帅
 * @Version 1.0
 * @Description
 * 下一个最小数字
 */
public class NextPermutation {

    private static int[] source = new int[]{1,2,3};

    @Test
    public void test(){
        nextPermutation(source);
        System.out.println(Arrays.toString(source));
    }

    /**
     * 主要思想：
     * 关键两个参数 i 和 j
     * i 决定值是 从结尾到头部单调递增的临界值
     * j 决定值是 从结尾到头部比 nums[i] 大的元素
     * 找到这两个值后，进行值交换，然后翻转这个单调递增的序列(从 i + 1 开始，因为之前就是找的单调递增序列(从队尾开始))
     *
     *  注意： 思想最重要！但是涉及到交换位置的数组几乎是离不开 双指针 的
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        int i = len - 2;
        // 寻找非降序的 a[i - 1] (当前 i 比 后面的小为止)
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        // 如果是顺序的数组 那么 i 就是1（len = 3）
        if (i >= 0) {
            int j = len - 1;
            // 当前值跟倒序元素比较大小，如果 i 元素比 倒序指针元素大，倒序指针减1
            // 寻找 a[j]
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
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
