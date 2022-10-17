package com.wills.leetcode.simple.array.triangle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Triangle
 * @Date 2022/7/4 16:25
 * @Author 王帅
 * @Version 1.0
 * @Description 三角形最小路径之和
 */
public class Triangle {

    private static List<List<Integer>> triangle = new ArrayList<>();

    static {
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 3));
    }

    @Test
    public void test() {
        System.out.println(minimumTotalByDP(triangle));
    }

    // 动态规划， dp 大法
    public int minimumTotalByDP(List<List<Integer>> triangle) {
        int len = triangle.size();
        // dp[i][j] 表示从点 (i, j) 到底边的最小路径和。
        int[][] dp = new int[len + 1][len + 1];
        // 从三角形的最后一行开始递推。
        for (int i = len - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(
                        dp[i + 1][j],
                        dp[i + 1][j + 1])
                        + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }

    // 尝试使用回溯算法那种 DFS累死的算法
    public int minimumTotal(List<List<Integer>> triangle) {
        return dfs(triangle, 0, 0);
    }

    private int dfs(List<List<Integer>> triangle, int i, int j) {
        if (i == triangle.size()) {
            return 0;
        }
        return Math.min(
                // 下一层
                dfs(triangle, i + 1, j),
                // 下一层 + 1
                dfs(triangle, i + 1, j + 1))
                // 加上当前的值，取最小的
                + triangle.get(i).get(j);
    }

    // 做了下，贪心算法貌似不太行，因为贪心算法是把大问题化解为小问题，然后每一步求最优解，但是组合可能不是最优了
    public int minimumTotal1(List<List<Integer>> triangle) {
        int len = triangle.size(), sum = triangle.get(0).get(0), preSum = sum;
        for (int i = 1; i < len; i++) {
            List<Integer> list = triangle.get(i);
            for (int j = 0; j < list.size(); j++) {
                if (j == 0) sum = preSum + list.get(j);
                else {
                    int tmp = preSum + list.get(j);
                    sum = Math.min(tmp, sum);
                }
                // 最后一步确定上一步的最小值
                if (j == list.size() - 1) preSum = sum;
            }
        }
        return sum;
    }
}
