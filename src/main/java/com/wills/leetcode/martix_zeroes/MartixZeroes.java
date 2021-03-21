package com.wills.leetcode.martix_zeroes;

/**
 * @author 王帅
 * @date 2021-03-21 17:32:59
 * @description:
 * 矩阵置零
 */
public class MartixZeroes {

    /**
     * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
     *
     * 进阶：
     * 一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
     * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
     * 你能想出一个仅使用常量空间的解决方案吗？
     *
     * 示例 1：
     * 输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
     * 输出：[[1,0,1],[0,0,0],[1,0,1]]
     *
     * 示例 2：
     * 输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
     * 输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
     */
    public static void main(String[] args) {
        int[][] source = {{0,2,1,0},{4,2,5,3},{6,7,8,5}};
        setZeroes1(source);
    }

    /**
     * 空间复杂度 O(m + n)的解法
     * 因为要将行和列周围的数字都置位0
     * 所以：
     * 假设 source[1][1]的元素为0
     * 那么置位0的元素应该是
     *          source[1][x]
     *          source[x][1]
     *  那么我们可以用标记数组法，进行两次for循环
     *  时间复杂度应该为O(2n)
     */
    public static void setZeroes(int[][] matrix) {
        int length = matrix.length;
        int width = matrix[0].length;
        // 表示行
        boolean[] lon = new boolean[length];
        // 表示列
        boolean[] wid = new boolean[width];
        // 找到元素为0 的位置
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if(matrix[i][j] == 0){
                    lon[i] = wid[j] = true;
                }
            }
        }
        // 遍历这个标记数组，然后遇到0 那么我们就将其置位0
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if( lon[i] || wid[j] ){
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void setZeroes1(int[][] matrix) {
        int length = matrix.length;
        int width = matrix[0].length;
        boolean flag = false;
        for (int i = 0; i < length; i++) {
            if (matrix[i][0] == 0) flag = true;
            for (int j = 1; j < width; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        for (int i = length - 1; i >= 0; i--) {
            for (int j = 1; j < width; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (flag) matrix[i][0] = 0;
        }
    }
}
