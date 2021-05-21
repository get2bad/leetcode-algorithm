package com.wills.leetcode.waiting_to_be_sorted.toeplitzmatrix;

/**
 * @author 王帅
 * @date 2021-02-22 09:24:56
 * @description:
 * 托普利茨矩阵
 */
public class ToeplitzMatrix {

    /**
     * 给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。
     *
     * 如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。
     *
     * 输入：matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
     * 输出：true
     * 解释：
     * 在上述矩阵中, 其对角线为:
     * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]"。
     * 各条对角线上的所有元素均相同, 因此答案是 True 。
     *
     * 输入：matrix = [[1,2],[2,2]]
     * 输出：false
     * 解释：
     * 对角线 "[1, 2]" 上的元素不同。
     *
     * 进阶：
     * 如果矩阵存储在磁盘上，并且内存有限，以至于一次最多只能将矩阵的一行加载到内存中，该怎么办？
     * 如果矩阵太大，以至于一次只能将不完整的一行加载到内存中，该怎么办？
     */
    public static void main(String[] args) {
        int[][] matrix = {{1,2,3,4},{5,1,2,3},{9,5,1,2}};
        System.out.println(isToeplitzMatrix(matrix));
    }

    /**
     * 本题的关键点就是在查看对角线这条线上的数字是否和第一个相同？如果不同就返回false
     * 案例1 可以知道，从 左下角开始(因为就一个，所以绝对是对角线相等，那么就从
     * matrix[0][0] -> martix[1][1] -> martix[2][2] -> ....)
     *
     * 时间复杂度：O(mn) 其中 mm 为矩阵的行数，nn 为矩阵的列数。矩阵中每个元素至多被访问两次。
     */
    public static boolean isToeplitzMatrix(int[][] matrix) {
        if(matrix.length == 0) return true;
        int length = matrix.length;
        int width = matrix[0].length;
        for (int i = 1; i < length; i++) {
            for (int j = 1; j < width; j++) {
                if(matrix[i][j] != matrix[i-1][j-1]){
                    return false;
                }
            }
        }

        return true;
    }
}
