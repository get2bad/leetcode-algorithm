package com.wills.leetcode.array.spiral_matrix;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName SpiralMatrix
 * @Date 2022/6/24 11:44
 * @Author 王帅
 * @Version 1.0
 * @Description 螺旋矩阵2
 */
public class SpiralMatrix2 {

        private static int source = 4;
    //    private static int source = 3;
//    private static int source = 1;

    @Test
    public void test() {
        for (int[] matrix : generateMatrix(source)) {
            System.out.println(Arrays.toString(matrix));
        }
    }

    public int[][] generateMatrix(int n) {
        int l = 0, r = n - 1, t = 0, b = n - 1;
        int[][] res = new int[n][n];
        int num = 1;
        while(num <= (n * n)){
            for(int i = l; i <= r; i++) res[t][i] = num++; // 从左到右
            t++;
            for(int i = t; i <= b; i++) res[i][r] = num++; // 从上到下
            r--;
            for(int i = r; i >= l; i--) res[b][i] = num++; // 从右到左
            b--;
            for(int i = b; i >= t; i--) res[i][l] = num++; // 从下到上
            l++;
        }
        return res;
    }
}
