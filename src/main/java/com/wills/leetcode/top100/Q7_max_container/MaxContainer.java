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
