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
public class Permutations2 {

    private static int[] source = {1, 1, 2};

    @Test
    public void test() {
        System.out.println(permute(source));
    }

    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) return Collections.EMPTY_LIST;
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();

        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        permute(res, path, used, 0, nums, nums.length);
        return res;
    }

    public void permute(List<List<Integer>> res, Deque<Integer> path, boolean[] used, int begin, int[] nums, int len) {
        if (path.size() == len) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < len; i++) {
            if(used[i]) continue;

            // 剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
            // 写 !used[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            path.addLast(nums[i]);
            used[i] = true;
            permute(res, path, used, begin + 1, nums, len);
            used[i] = false;
            path.removeLast();
        }
    }
}
