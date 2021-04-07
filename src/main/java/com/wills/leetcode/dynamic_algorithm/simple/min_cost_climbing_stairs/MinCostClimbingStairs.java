package com.wills.leetcode.dynamic_algorithm.simple.min_cost_climbing_stairs;

/**
 * @author 王帅
 * @date 2021-04-07 10:43:14
 * @description:
 * 最小花费爬楼梯
 */
public class MinCostClimbingStairs {

    public static void main(String[] args) {
        int[] source = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(minCostClimbingStairs(source));
    }

    public static int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int[] up = new int[len + 1];
        up[0] = 0;
        up[1] = Math.min(cost[0], cost[1]);
        for (int i = 2; i < len; i++) {
            up[i] = Math.min(up[i - 1] + cost[i], up[i - 2] + cost[i - 1]);
        }
        return up[len - 1];
    }
}
