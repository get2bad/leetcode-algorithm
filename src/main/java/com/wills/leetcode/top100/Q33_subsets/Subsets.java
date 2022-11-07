package com.wills.leetcode.top100.Q33_subsets;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @ClassName Subsets
 * @Date 2022/11/7 10:22
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class Subsets {

    private static final int[] source = {1, 2, 3};

    @Test
    public void test() {
        System.out.println(subsets(source));
    }

    /**
     * 给你一个整数数组nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     * <p>
     * 示例 1：
     * 输入：nums = [1,2,3]
     * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     * <p>
     * 示例 2：
     * 输入：nums = [0]
     * 输出：[[],[0]]
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();

        generate(res, path, 0, nums);
        return res;
    }

    public void generate(List<List<Integer>> res, Deque<Integer> path, int begin, int[] nums) {
        res.add(new ArrayList<>(path));

        for (int i = begin; i < nums.length; i++) {
            path.add(nums[i]);
            generate(res, path, i + 1, nums);
            path.removeLast();
        }
    }
}
