package com.wills.leetcode.transpose_matrix;

import java.util.Arrays;

/**
 * @author 王帅
 * @date 2021-02-25 09:13:16
 * @description:
 * 转置矩阵
 */
public class TransposeMatrix {

    /**
     * 给你一个二维整数数组 matrix， 返回 matrix 的 转置矩阵 。
     * 矩阵的 转置 是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。
     *
     * 示例 1：
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[[1,4,7],[2,5,8],[3,6,9]]
     *
     * 示例 2：
     * 输入：matrix = [[1,2,3],[4,5,6]]
     * 输出：[[1,4],[2,5],[3,6]]
     */
    public static void main(String[] args) {
        int[][] source = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] res = transpose(source);
        Arrays.stream(res).forEach(data -> {
            for (int i = 0; i < data.length; i++) {
                System.out.print(data[i] + ":");
            }
        });
    }

    /**
     * 本题的主题思路就是 横 转 竖
     * 我们来推断一下：
     * source[0][0] -> source[0][10
     * source[0][1] -> source[1][0] / source[1][0] -> source[0][1]
     * source[0][2] -> source[2][0] / source[2][0] -> source[0][2]
     *
     * 所以，双指针法 / 双遍历法可以解决本问题
     * 假设 二维数组总长度为 9 则横纵向 坐标应该为
     *  x = 9 / martix[0].length
     *  y = 9 % martix[0].length
     *
     *  横转竖应该为
     *  y = 9 / martix[0].length
     *  x = 9 % martix[0].length
     *
     *  双遍历法就是
     *  res[j][i] = matrix[i][j]
     */
    public static int[][] transpose(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return matrix;
        int length = matrix.length;
        int width = matrix[0].length;
        int[][] res = new int[width][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                res[j][i] = matrix[i][j];
            }
        }
        return res;
    }
}
