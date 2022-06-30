package com.wills.leetcode.array.subsets;

import org.junit.Test;

import java.util.*;

/**
 * @ClassName Subsets
 * @Date 2022/6/30 11:58
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class Subsets {

    private static int[] source = {1, 2, 3};

    @Test
    public void test() {
        System.out.println(subsets(source));
    }

    // 子集 直接使用回溯算法(直接不剪枝)
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        subsets(res, path, 0, nums);
        return res;
    }

    private void subsets(List<List<Integer>> res, Deque<Integer> path, int begin, int[] nums) {
        res.add(new ArrayList<>(path));

        for (int i = begin; i < nums.length; i++) {
            path.add(nums[i]);
            subsets(res, path, i + 1, nums);
            path.removeLast();
        }
    }
}
