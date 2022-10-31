package com.wills.leetcode.top100.Q21_permutations;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @ClassName Permutations
 * @Date 2022/10/31 13:35
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class Permutations {

    private static final int[] source = {1, 2, 3};

    @Test
    public void test() {
        System.out.println(permute(source));
    }

    /**
     *  给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     *
     * 示例 1：
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     *
     * 示例 2：
     * 输入：nums = [0,1]
     * 输出：[[0,1],[1,0]]
     *
     * 示例 3：
     * 输入：nums = [1]
     * 输出：[[1]]
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums.length == 0) return res;

        permute(res,new ArrayDeque<>(),nums,0, nums.length);

        return res;
    }

    public void permute(List<List<Integer>> res, Deque<Integer> path, int[] nums, int start, int length) {
        if (path.size() > length) return;

        if (path.size() == length) res.add(new ArrayList<>(path));

        for (int i = 0; i < length; i++) {
            if (path.contains(nums[i])) continue;
            path.add(nums[i]);
            permute(res, path, nums, i + 1, length);
            path.removeLast();
        }
    }
}
