package com.wills.leetcode.array.rotate_image;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName RotateImage
 * @Date 2022/6/23 14:28
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class RotateImage {

    private static int[][] source = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//    private static int[][] source = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};

    @Test
    public void test() {
        rotate(source);
        for (int i = 0; i < source.length; i++) {
            System.out.println(Arrays.toString(source[i]));
        }
    }

    // 思路： 先转置 martix[i][j] = maxtix[j][i] 然后水平翻转
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
