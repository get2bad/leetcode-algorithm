package com.wills.leetcode.array.sum_of_num;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @ClassName SumOfNum
 * @Date 2022/6/22 15:26
 * @Author 王帅
 * @Version 1.0
 * @Description
 * 组合总和 - 回溯算法的经典案例
 */
public class SumOfNum {

    private static int[] source = {2,3,6,7};
    private static int target = 7;

    @Test
    public void test(){
        System.out.println(combinationSum(source,target));
    }

    // 使用回溯算法(主要是二叉树 + 深度优先搜索遍历)
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

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
     *
     * 使用回溯算法(主要是二叉树 + 深度优先搜索遍历)
     *
     *  产生重复的原因是：在每一个结点，做减法，展开分支的时候，由于题目中说 每一个元素可以重复使用，
     *  我们考虑了 所有的 候选数，因此出现了重复的列表。
     *
     * 一种简单的去重方案是借助哈希表的天然去重的功能，但实际操作一下，就会发现并没有那么容易。
     *
     * 可不可以在搜索的时候就去重呢？答案是可以的。遇到这一类相同元素不计算顺序的问题，我们在搜索的时候就需要 按某种顺序搜索。
     * 具体的做法是：每一次搜索的时候设置 下一轮搜索的起点 begin，begin确保了只会搜索索引为begin后的数据
     */
    private void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
        // target 为负数和 0 的时候不再产生新的孩子结点
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 重点理解这里从 begin 开始搜索的语意
        for (int i = begin; i < len; i++) {
            path.addLast(candidates[i]);

            // 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i，这里非常容易弄错
            dfs(candidates, i, len, target - candidates[i], path, res);

            // 状态重置
            path.removeLast();
        }
    }
}