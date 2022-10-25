package com.wills.leetcode.top100.Q7_max_container;

import org.junit.Test;

/**
 * @ClassName MaxContainer
 * @Date 2022/10/18 14:18
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class MaxContainer {

    private static final int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};

    @Test
    public void test() {
        System.out.println(maxArea(height));
    }

    /**
     * 给定一个长度为 n 的整数数组height。有n条垂线，第 i 条线的两个端点是(i, 0)和(i, height[i])。
     * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。返回容器可以储存的最大水量。
     *
     * 示例 1：
     * 输入：[1,8,6,2,5,4,8,3,7]
     * 输出：49
     * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为49。
     *
     * 示例 2：
     * 输入：height = [1,1]
     * 输出：1
     *
     * 本题使用快慢指针法，思路为：
     * 使用快慢指针分别从头 从尾 开始遍历整个数组，每次遍历计算一次最大值，然后跟上一次的最大值相比较，进行赋值
     * 指针移动的策略是本着 最小值优先的策略，如果 快指针位大于等于慢指针位，则 慢指针位++，否则快指针位++
     */
    public int maxArea(int[] height) {
        int max = 0, len = height.length, slow = 0, fast = len - 1;
        if (len < 2) return 0;
        while (slow < fast) {
            int total = Math.min(height[slow], height[fast]) * (fast - slow);
            if (total > max) max = total;
            // 快慢指针移动
            if(height[fast] >= height[slow]) slow++;
            else fast--;
        }
        return max;
    }
}
