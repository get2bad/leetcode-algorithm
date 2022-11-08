package com.wills.leetcode.top100.Q36_maximal_rectangle;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ClassName MaximalRectangle
 * @Date 2022/11/8 10:28
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class MaximalRectangle {

    private static final char[][] source = {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
    };

    @Test
    public void test() {
        System.out.println(maximalRectangleByStack(source));
    }

    /**
     * 给定一个仅包含0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
     * <p>
     * 示例 1：
     * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
     * 输出：6
     * 解释：最大矩形如上图所示。
     * <p>
     * 示例 2：
     * 输入：matrix = []
     * 输出：0
     * <p>
     * 示例 3：
     * 输入：matrix = [["0"]]
     * 输出：0
     * <p>
     * 示例 4：
     * 输入：matrix = [["1"]]
     * 输出：1
     * <p>
     * 示例 5：
     * 输入：matrix = [["0","0"]]
     * 输出：0
     * <p>
     * 必定会用动态规划
     */
    public int maximalRectangleByStack(char[][] matrix) {
        int hLen = matrix.length, wLen = matrix[0].length, res = 0;
        if (hLen == 0) return 0;

        int[][] left = new int[hLen][wLen];

        // 求出类似于前缀和
        for (int i = 0; i < hLen; i++) {
            for (int j = 0; j < wLen; j++) {
                if (matrix[i][j] == '1') left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
            }
        }

        for (int j = 0; j < wLen; j++) { // 对于每一列，使用基于柱状图的方法
            int[] up = new int[hLen], down = new int[hLen];

            Deque<Integer> stack = new LinkedList<>();
            for (int i = 0; i < hLen; i++) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) stack.pop();

                up[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }
            stack.clear();
            for (int i = hLen - 1; i >= 0; i--) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) stack.pop();

                down[i] = stack.isEmpty() ? hLen : stack.peek();
                stack.push(i);
            }

            for (int i = 0; i < hLen; i++) {
                int height = down[i] - up[i] - 1;
                int area = height * left[i][j];
                res = Math.max(res, area);
            }
        }
        return res;
    }
}
