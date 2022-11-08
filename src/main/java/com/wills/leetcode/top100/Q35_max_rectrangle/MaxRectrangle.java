package com.wills.leetcode.top100.Q35_max_rectrangle;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @ClassName MaxTrangle
 * @Date 2022/11/7 10:52
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class MaxRectrangle {

        private static final int[] source = {2, 1, 5, 6, 2, 3};
//    private static final int[] source = {2, 4};

    @Test
    public void test() {
        System.out.println(largestRectangleArea(source));
    }

    /**
     * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
     * <p>
     * 示例 1:
     * 输入：heights = [2,1,5,6,2,3]
     * 输出：10
     * 解释：最大的矩形为图中红色区域，面积为 10
     * <p>
     * 示例 2：
     * 输入： heights = [2,4]
     * 输出： 4
     */
    // 单调栈
    public int largestRectangleArea(int[] heights) {
        if (heights.length == 0) return 0;
        int max = 0, len = heights.length, index = 1;
        int[] arr = new int[len + 2];
        System.arraycopy(heights,0,arr,1,heights.length);
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        while (index < arr.length) {
            stack.push(index++);
            // arr[index] < arr[stack.peek()] 如果 前一个比后一个大
            while (index < arr.length && arr[stack.peek()] > arr[index]) {
                int curHeight = arr[stack.pop()];
                max = Math.max(max, curHeight * (index - stack.peek() - 1));
            }
        }

        return max;
    }


    // 冒泡算法 超时！ 但是可以解答
    public int largestRectangleAreaByBubble(int[] heights) {
        int max = heights[0], len = heights.length;
        for (int i = 0; i < len; i++) {
            int cur = heights[i];
            max = Math.max(cur, max);
            for (int j = i + 1; j < len; j++) {
                cur = Math.min(cur, heights[j]);
                max = Math.max(cur * (j - i + 1), max);
            }
        }
        return max;
    }
}
