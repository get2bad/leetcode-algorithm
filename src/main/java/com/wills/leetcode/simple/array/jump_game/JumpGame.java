package com.wills.leetcode.simple.array.jump_game;

import org.junit.Test;

/**
 * @ClassName JumpGame
 * @Date 2022/6/24 14:26
 * @Author 王帅
 * @Version 1.0
 * @Description 跳跃游戏
 */
public class JumpGame {

    private static int[] source = {0, 1};
//    private static int[] source = {2, 3, 1, 1, 4};
//    private static int[] source = {3, 2, 1, 0, 4};

    @Test
    public void test() {
        System.out.println(canJump(source));
    }

    /**
     * 解题思路：
     * 1. 如果某一个作为 起跳点 的格子可以跳跃的距离是 3，那么表示后面 3 个格子都可以作为 起跳点
     * 2. 可以对每一个能作为 起跳点 的格子都尝试跳一次，把 能跳到最远的距离 不断更新
     * 3. 如果可以一直跳到最后，就成功了
     */
    public boolean canJump(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if(i > k) return false;
            k = Math.max(k,nums[i] + i);
        }
        return true;
    }

    public boolean canJump1(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }

//    public boolean canJump(int[] nums) {
//        if(nums.length == 1) return true;
//        if(nums[0] == 0) return false;
//        List<List<Integer>> res = new ArrayList<>();
//        Deque<Integer> path = new ArrayDeque<>();
//        path.add(nums[0]);
//        canJump(res, path, nums[0], nums);
//        return res.size() != 0;
//    }
//
//    // 感觉可以使用回溯算法来解决
//    private void canJump(List<List<Integer>> res, Deque<Integer> path, int begin, int[] nums) {
//        if (begin > nums.length - 1 || path.size() > nums.length) return;
//
//        if (begin == nums.length - 1) res.add(new ArrayList<>(path));
//
//        for (int i = begin; i < nums.length; i++) {
//            int curr = nums[i], next = begin + curr;
//            path.add(curr);
//            if(nums[next] != 0) canJump(res, path, next, nums);
//            path.removeLast();
//        }
//    }

}
