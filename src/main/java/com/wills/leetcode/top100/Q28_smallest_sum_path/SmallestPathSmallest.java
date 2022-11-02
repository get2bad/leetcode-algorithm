package com.wills.leetcode.top100.Q28_smallest_sum_path;

import org.junit.Test;

/**
 * @ClassName SmallestPathSmallest
 * @Date 2022/11/2 11:26
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class SmallestPathSmallest {

    private static final int[][] source = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};

    @Test
    public void test() {
        System.out.println(minPathSum(source));
    }

    /**
     * 给定一个包含非负整数的 mxn网格grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * 说明：每次只能向下或者向右移动一步。
     * <p>
     * 示例 1：
     * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
     * 输出：7
     * 解释：因为路径 1→3→1→1→1 的总和最小。
     * <p>
     * 示例 2：
     * 输入：grid = [[1,2,3],[4,5,6]]
     * 输出：12
     *
     * 跟 com.wills.leetcode.top100.Q27.another_path.AnotherPath 的基础动态规划一致
     * 只需要确定好 边界的值，然后后面进行叠加决策即可
     * 此题万万不可用 贪心算法，因为贪心算法只能求划分开的局部值，而不是之前的计算结果用作下一次计算
     */
    public int minPathSum(int[][] grid) {
        int height = grid.length;
        int width = grid[0].length;
        int[][] dp = new int[height][width];
        dp[0][0] = grid[0][0];
        // 初始化状态值
        for (int i = 1; i < height; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < width; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < height; i++) {
            for (int j = 1; j < width; j++) {
                int top = grid[i][j] + dp[i - 1][j];
                int left = grid[i][j] + dp[i][j - 1];
                dp[i][j] = Math.min(top,left);
            }
        }
        return dp[height - 1][width - 1];
    }
}
