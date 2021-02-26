package com.wills.leetcode.reshape;

import java.util.Arrays;

/**
 * 重塑矩阵
 *
 * 给出一个由二维数组表示的矩阵，以及两个正整数r和c，分别表示想要的重构的矩阵的行数和列数。
 *
 * 重构后的矩阵需要将原始矩阵的所有元素以相同的行遍历顺序填充。
 *
 * 如果具有给定参数的reshape操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
 *
 * 示例 1:
 * 输入:
 * nums =[[1,2], [3,4]]
 * r = 1, c = 4
 * 输出:
 * [[1,2,3,4]]
 * 解释:
 * 行遍历nums的结果是 [1,2,3,4]。新的矩阵是 1 * 4 矩阵, 用之前的元素值一行一行填充新矩阵。
 *
 * 示例 2:
 * 输入:
 * nums = [[1,2], [3,4]]
 * r = 2, c = 4
 * 输出:
 * [[1,2],[3,4]]
 * 解释:
 * 没有办法将 2 * 2 矩阵转化为 2 * 4 矩阵。 所以输出原矩阵。
 */
public class ReShape {

    public static void main(String[] args) {
        int[][] source  = {{1,2},{3,4}};
        int[][] res = matrixReshape(source, 1, 4);
        Arrays.stream(res).forEach(System.out::println);
    }

    /**
     * 分析：x作为循环的因子
     * 1.对于原数组：
     *      nums[一维][二维]：
     *          一维： x/width
     *          二维： x%width
     * 2.对于新数组：
     *      res[一维][二维]：
     *          一维： x/c
     *          二维： x%c
     */
    public static int[][] matrixReshape(int[][] nums, int r, int c) {
        int length = nums.length;
        int width = nums[0].length;
        if(length * width != r * c){
            return nums;
        }
        int[][] res = new int[r][c];
        // 将数组进行转换处理
        for (int x = 0; x < length*width; x++) {
            res[x/c][x%c] = nums[x/width][x%width];
        }
        return res;
    }
}
