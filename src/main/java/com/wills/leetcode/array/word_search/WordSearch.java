package com.wills.leetcode.array.word_search;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @ClassName WordSearch
 * @Date 2022/6/30 14:32
 * @Author 王帅
 * @Version 1.0
 * @Description 单词搜索
 */
public class WordSearch {

    private static char[][] source = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
    private static String word = "ABCCED";

    @Test
    public void test() {
        System.out.println(exist(source, word));
    }

    // 尝试使用回溯算法
    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean flag = check(board, visited, i, j, word, 0);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check(char[][] board, boolean[][] visited, int i, int j, String word, int index) {
        if (board[i][j] != word.charAt(index)) {
            return false;
        } else if (index == word.length() - 1) {
            return true;
        }
        visited[i][j] = true;
        // 分别是 左、右、下、上
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean result = false;
        for (int[] dir : directions) {
            int newi = i + dir[0], newj = j + dir[1];
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                // 如果没有访问过
                if (!visited[newi][newj]) {
                    // 递归查看后面是否符合要求
                    boolean flag = check(board, visited, newi, newj, word, index + 1);
                    if (flag) {
                        result = true;
                        break;
                    }
                }
            }
        }
        visited[i][j] = false;
        return result;
    }
}