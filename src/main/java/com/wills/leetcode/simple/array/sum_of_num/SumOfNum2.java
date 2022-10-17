package com.wills.leetcode.simple.array.sum_of_num;

import org.junit.Test;

import java.util.*;

/**
 * @ClassName SumOfNum
 * @Date 2022/6/22 15:26
 * @Author 王帅
 * @Version 1.0
 * @Description 组合总和 - 回溯算法的经典案例
 */
public class SumOfNum2 {

    private static int[] source = {10, 1, 2, 7, 6, 1, 5};
    private static int target = 8;

    @Test
    public void test() {
        System.out.println(combinationSum(source, target));
    }

    // 使用回溯算法(主要是二叉树 + 深度优先搜索遍历)
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }
        // 关键步骤 为去重做准备
        Arrays.sort(candidates);
        Deque<Integer> path = new ArrayDeque<>();
        dfs(candidates, 0, len, target, path, res);
        return res;
    }

    /**
     * @param candidates 候选数组
     * @param begin      搜索起点
     * @param len        冗余变量，是 candidates 里的属性，可以不传
     * @param target     每减去一个元素，目标值变小
     * @param path       从根结点到叶子结点的路径，是一个栈
     * @param res        结果集列表
     */
    private void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < len; i++) {
            // 大剪枝：减去 candidates[i] 小于 0，减去后面的 candidates[i + 1]、candidates[i + 2] 肯定也小于 0，因此用 break
            if (target - candidates[i] < 0) {
                break;
            }

            // 小剪枝：同一层相同数值的结点，从第 2 个开始，候选数更少，结果一定发生重复，因此跳过，用 continue
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }
            path.add(candidates[i]);
            System.out.println("回溯前的路径： " + path);
            dfs(candidates, i + 1, len, target - candidates[i], path, res);
            // 为什么要 removeLast? 因为要回溯之前一个的下一个结果
            path.removeLast();
            System.out.println("回溯后的路径： " + path);
        }
    }
}