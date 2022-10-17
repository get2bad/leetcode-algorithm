package com.wills.leetcode.simple.dynamic_algorithm.middle.unique_path;

/**
 * @author 王帅
 * @date 2021-04-14 10:17:43
 * @description: 不同的路径2
 */
public class UniquePath2 {

    public static void main(String[] args) {
        int[][] source = {{0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}};
//        int[][] source = {{0,1},
//                          {0,0}};
        System.out.println(uniquePathsWithObstacles(source));
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int column = obstacleGrid[0].length;

        //dp[i][j] = -1   不可达
        int[][] dp = new int[row][column];
        for (int i = 0; i < row; i++)
            for (int j = 0; j < column; j++)
                if (obstacleGrid[i][j] == 1)
                    dp[i][j] = -1;
        dp[0][0] = obstacleGrid[0][0] == 1 ? -1 : 1;

        for (int i = 1; i < row; i++)
            dp[i][0] = obstacleGrid[i][0] == 1 ? -1 : dp[i - 1][0] == -1 ? -1 : 1;
        for (int j = 1; j < column; j++)
            dp[0][j] = obstacleGrid[0][j] == 1 ? -1 : dp[0][j - 1] == -1 ? -1 : 1;


        for (int i = 1; i < row; i++)
            for (int j = 1; j < column; j++)
                if (dp[i][j] == -1)
                    dp[i][j] = -1;
                else if (dp[i - 1][j] != -1 && dp[i][j - 1] != -1)
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                else if (dp[i - 1][j] != -1)
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = dp[i][j - 1];

        return dp[row - 1][column - 1] == -1 ? 0 : dp[row - 1][column - 1];
    }
}
