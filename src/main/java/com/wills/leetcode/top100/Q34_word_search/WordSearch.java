package com.wills.leetcode.top100.Q34_word_search;

import org.junit.Test;

/**
 * @ClassName WordSearch
 * @Date 2022/11/7 10:29
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class WordSearch {

    private static final char[][] source = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
    private static final String word = "ABCCED";

    @Test
    public void test() {
        System.out.println(exist(source, word));
    }

    /**
     *  给定一个m x n 二维字符网格board 和一个字符串单词word 。如果word 存在于网格中，返回 true ；否则，返回 false 。
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
     * 同一个单元格内的字母不允许被重复使用。
     *
     * 示例 1：
     * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
     * 输出：true
     *
     * 示例 2：
     * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
     * 输出：true
     *
     * 示例 3：
     * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
     * 输出：false
     */
    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (check(visited, i, j, 0, word, board)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check(boolean[][] visited, int i, int j, int index, String word, char[][] board) {
        if (board[i][j] != word.charAt(index)) return false;
        if (index == word.length() - 1) return true;

        visited[i][j] = true;
        // 分别是 上、下、左、右
        int[][] directions = {{-1, 0},{1, 0}, {0, -1}, {0, 1}};
        boolean result = false;
        for (int[] dir : directions) {
            int newi = i + dir[0], newj = j + dir[1];
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                // 如果没有访问过
                if (!visited[newi][newj]) {
                    // 递归查看后面是否符合要求
                    if (check(visited, newi, newj, index + 1, word, board)) {
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
