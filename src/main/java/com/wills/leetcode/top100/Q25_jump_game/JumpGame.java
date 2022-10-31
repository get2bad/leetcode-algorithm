package com.wills.leetcode.top100.Q25_jump_game;

import org.junit.Test;

/**
 * @ClassName JumpGame
 * @Date 2022/10/31 17:48
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class JumpGame {

        private static final int[] source = {2, 3, 1, 1, 4};
//    private static final int[] source = {3, 2, 1, 0, 4};

    @Test
    public void test() {
        System.out.println(canJump(source));
    }

    /**
     * 给定一个非负整数数组nums ，你最初位于数组的 第一个下标 。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 判断你是否能够到达最后一个下标。
     * <p>
     * 示例1：
     * 输入：nums = [2,3,1,1,4]
     * 输出：true
     * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
     * <p>
     * 示例2：
     * 输入：nums = [3,2,1,0,4]
     * 输出：false
     * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
     *
     * 关键点，就是每次叠加能跳的最高值，如果当前索引 > 最高跳的步数，说明无法到达目标索引，直接返回false就可以了
     *
     */
    public boolean canJump(int[] nums) {
        int cur = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > cur) return false;
            cur = i + nums[i];
        }
        return true;
    }

}
