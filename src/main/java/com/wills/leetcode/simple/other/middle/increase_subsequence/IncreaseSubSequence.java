package com.wills.leetcode.simple.other.middle.increase_subsequence;

/**
 * @ClassName IncreaseSubSequence
 * @Date 2022/1/12 09:45
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class IncreaseSubSequence {

    public static void main(String[] args) {
        int[] source = {20,100,10,12,5,13};
//        boolean triplet = increasingTriplet(source);
        boolean triplet = increasingTriplet1(source);
        System.out.println(triplet);
    }

    public static boolean increasingTriplet(int[] nums) {
        int len = nums.length;
        if(len < 3) return false;
        // 首先赋值 first为数组中第一个元素 second为整数最大值，为的是构造一个三连队列
        int first = nums[0], second = Integer.MAX_VALUE;
        // 从 index = 1开始遍历这个数组
        for (int i = 1; i < len; i++) {
            // 拿到当前遍历的这个数
            int num = nums[i];
            // 如果 当前数 大于 second()
            if (num > second) {
                // 说明是一个递增序列
                return true;
            } else if (num > first) { // 如果 当前值 大于 first
                // 那么 将当前值赋值给 second，因为这是构造递增序列的第二步(第二关键值)
                second = num;
            } else {
                // 否则 当前值赋值给first 因为重新构建一个递增序列，之前的已经被破坏(遇到了一个比起始值还小的数字)
                first = num;
            }
        }
        return false;
    }

    public static boolean increasingTriplet1(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        // 复制一个相同长度的数组
        int[] leftMin = new int[n];
        // 将新数组的第一个元素赋值为传入的数组第一个元素，找到最小值排序
        leftMin[0] = nums[0];
        // 比较当前遍历元素与现在遍历的元素的大小，将最小的放入新的数组
        for (int i = 1; i < n; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], nums[i]);
        }
        // 声明一个新的数组，从右边遍历的，找到最大值排序
        int[] rightMax = new int[n];
        // 找到传入数组的最后一个
        rightMax[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], nums[i]);
        }
        for (int i = 1; i < n - 1; i++) {
            int currentNum = nums[i];
            int leftMinNum = leftMin[i - 1];
            int rightMaxNum = rightMax[i + 1];
            if (currentNum > leftMinNum && currentNum < rightMaxNum) {
                return true;
            }
        }
        return false;
    }
}
