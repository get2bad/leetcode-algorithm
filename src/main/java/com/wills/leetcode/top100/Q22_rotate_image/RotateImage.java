package com.wills.leetcode.top100.Q22_rotate_image;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName RotateImage
 * @Date 2022/10/31 13:41
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class RotateImage {

    private static final int[][] source = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

    @Test
    public void test() {
        rotate(source);
        for (int i = 0; i < source.length; i++) {
            System.out.println(Arrays.toString(source[i]));
        }
    }

    /**
     *  给定一个 n×n 的二维矩阵matrix 表示一个图像。请你将图像顺时针旋转 90 度。
     *
     * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
     *
     * 示例 1：
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[[7,4,1],[8,5,2],[9,6,3]]
     *
     * 示例 2：
     * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
     * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
     *
     * 问题的本质就是将数组进行旋转：
     * 1. 先行转置
     * 2. 然后进行列转置
     */
    public void rotate(int[][] matrix) {
        int len = matrix.length;
        // 先进行转置操作
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        // 然后进行水平翻转的操作 因为要水平翻转，所以要 除以 2
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][len - 1 - j];
                matrix[i][len - 1 - j] = tmp;
            }
        }
    }
}
