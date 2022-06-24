package com.wills.leetcode.array.spiral_matrix;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SpiralMatrix
 * @Date 2022/6/24 11:44
 * @Author 王帅
 * @Version 1.0
 * @Description 螺旋矩阵
 */
public class SpiralMatrix {

    private static int[][] source = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

    @Test
    public void test() {
        System.out.println(spiralOrder(source));
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return res;
        int width = matrix.length;
        int length = matrix[0].length;
        // 当前遍历的层数
        int currentLevel = 0;
        //统计矩阵从外向内的层数，如果矩阵非空，那么它的层数至少为1层
        // 找出有几层
        int totalLevel = (Math.min(width, length) + 1) / 2;
        //从外部向内部遍历，逐层打印数据
        while (currentLevel < totalLevel) {
            // 第 1 个：从左向右
            for (int i = currentLevel; i < length - currentLevel; i++) {
                res.add(matrix[currentLevel][i]);
            }
            // 第 2 个：从上往下
            for (int i = currentLevel + 1; i < width - currentLevel; i++) {
                res.add(matrix[i][(length - 1) - currentLevel]);
            }
            // 第 3 个：从右往左，如果这一层只有1行，那么第一个循环已经将该行打印了，这里就不需要打印了，
            // 即 （width-1-currentLevel ）!= currentLevel
            for (int i = (length - 1) - (currentLevel + 1);
                 i >= currentLevel && (width - 1 - currentLevel != currentLevel);
                 i--) {
                res.add(matrix[(width - 1) - currentLevel][i]);
            }
            // 第4个：从下往上，如果这一层只有1列，那么第2个循环已经将该列打印了，这里不需要打印，
            // 即(length-1-currentLevel) != currentLevel
            for (int i = (width - 1) - (currentLevel + 1);
                 i >= currentLevel + 1 && (length - 1 - currentLevel) != currentLevel;
                 i--) {
                res.add(matrix[i][currentLevel]);
            }
            currentLevel++;
        }
        return res;
    }
}
