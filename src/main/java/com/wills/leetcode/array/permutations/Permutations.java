package com.wills.leetcode.array.permutations;

import org.junit.Test;

import java.util.*;

/**
 * @ClassName Permutations
 * @Date 2022/6/23 09:58
 * @Author 王帅
 * @Version 1.0
 * @Description LeetCode - 46.全排列
 */
public class Permutations {

    private static int[] source = {1, 3, 2};

    @Test
    public void test() {
        System.out.println(permute(source));
    }

    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) return Collections.EMPTY_LIST;
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();

        permute(res, path, nums, nums.length);
        return res;
    }

    public void permute(List<List<Integer>> res, Deque<Integer> path, int[] nums, int len) {
        if (path.size() > len) return;

        if (path.size() == len) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < len; i++) {
            if (path.contains(nums[i])) continue;
            path.add(nums[i]);
            permute(res, path, nums, len);
            path.removeLast();
        }
    }
}
