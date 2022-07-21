package com.wills.leetcode.dynamic_algorithm.easy.transfer_infomation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TransferInfomate
 * @Date 2022/7/21 11:32
 * @Author 王帅
 * @Version 1.0
 * @Description 传递信息
 */
public class TransferInfomate {

    private static final int n = 5;
    private static final int k = 3;
    private static final int[][] relation = {{0, 2}, {2, 1}, {3, 4}, {2, 3}, {1, 4}, {2, 0}, {0, 4}};


    @Test
    public void test() {
        System.out.println(numWays(n, relation, k));
    }

    public int numWaysByDynamicAlgorithm(int n, int[][] relation, int k){
        int[][] dp = new int[k + 1][n];
        dp[0][0] = 1;
        for (int i = 0; i < k; i++) {
            for (int[] edge : relation) {
                int index = edge[0], dist = edge[1];
                dp[i + 1][dist] += dp[i][index];
            }
        }
        return dp[k][n - 1];
    }

    int res = 0;
    List<List<Integer>> edges;

    public int numWays(int n, int[][] relation, int k) {
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int[] edge : relation) {
            int index = edge[0], dst = edge[1];
            edges.get(index).add(dst);
        }
        dfs(0, 0, n, k);
        return res;
    }

    public void dfs(int index, int steps, int n, int k) {
        // 如果达到了 k 要求的轮数，满足 index == n - 1时会res++，否则直接跳出这个递归
        if (steps == k) {
            if (index == n - 1) {
                res++;
            }
            return;
        }
        // 拿到本次传递的目标进行递归
        List<Integer> list = edges.get(index);
        for (Integer nextIndex : list) {
            dfs(nextIndex, steps++, n, k);
        }
    }
}
