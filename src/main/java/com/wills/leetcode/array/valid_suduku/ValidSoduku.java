package com.wills.leetcode.array.valid_suduku;

import org.junit.Test;

/**
 * @ClassName ValidSoduku
 * @Date 2022/6/22 11:45
 * @Author 王帅
 * @Version 1.0
 * @Description 有效的数独
 */
public class ValidSoduku {

    private static char[][] source = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}
            , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
            , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
            , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
            , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
            , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
            , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
            , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
            , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

    @Test
    public void test() {
        System.out.println(isValidSudoku(source));
    }

    public boolean isValidSudoku(char[][] board) {
        // 记录某行，某位数字是否已经被摆放
        boolean[][] row = new boolean[9][9];
        // 记录某列，某位数字是否已经被摆放
        boolean[][] col = new boolean[9][9];
        // 记录某 3x3 宫格内，某位数字是否已经被摆放
        boolean[][] block = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    // 当前 行 / 列 的位置
                    int num = board[i][j] - '1';
                    // 确定是哪个九宫格的位置 不过是以竖为排序的
                    int blockIndex = i / 3 * 3 + j / 3;
                    // 如果某个位置值为true 说明冲突
                    if (row[i][num] || col[j][num] || block[blockIndex][num]) {
                        return false;
                    } else {
                        // 类似于前缀和，将相关的地方置为true
                        row[i][num] = true;
                        col[j][num] = true;
                        block[blockIndex][num] = true;
                    }
                }
            }
        }
        return true;
    }
}
