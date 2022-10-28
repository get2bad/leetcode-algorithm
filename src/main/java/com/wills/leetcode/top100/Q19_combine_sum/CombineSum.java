package com.wills.leetcode.top100.Q19_combine_sum;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @ClassName CombineSum
 * @Date 2022/10/28 11:37
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class CombineSum {

    private static final int[] source = {2, 3, 6, 7};
    private static final int target = 7;

    @Test
    public void test() {
        System.out.println(combinationSum(source, target));
    }

    /**
     *
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        generate(res, candidates, target, 0, path);

        return res;
    }

    private void generate(List<List<Integer>> res, int[] candidates, int target, int begin, Deque<Integer> path) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 重点理解这里从 begin 开始搜索的语意
        for (int i = begin; i < candidates.length; i++) {
            path.addLast(candidates[i]);

            // 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i，这里非常容易弄错
            generate(res,candidates,target - candidates[i],begin,path);

            // 状态重置
            path.removeLast();
        }
    }
}
