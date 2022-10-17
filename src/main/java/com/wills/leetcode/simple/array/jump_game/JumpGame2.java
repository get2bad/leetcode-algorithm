package com.wills.leetcode.simple.array.jump_game;

import org.junit.Test;

/**
 * @ClassName JumpGame2
 * @Date 2022/6/28 16:34
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class JumpGame2 {

    private static int[] source = {2, 3, 1, 1, 4};

    @Test
    public void test() {
        System.out.println(jump1(source));
    }

    public int jump(int[] nums) {
        int ans = 0, start = 0, end = 1;
        while (end < nums.length) {
            int maxPos = 0;
            for (int i = start; i < end; i++) {
                // 能跳到最远的距离
                maxPos = Math.max(maxPos, i + nums[i]);
            }
            start = end;      // 下一次起跳点范围开始的格子
            end = maxPos + 1; // 下一次起跳点范围结束的格子
            ans++;            // 跳跃次数
        }
        return ans;
    }

    public int jump1(int[] nums) {
        int position = nums.length - 1;
        int steps = 0;
        while (position > 0) {
            for (int i = 0; i < position; i++) {
                if (i + nums[i] >= position) {
                    position = i;
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }
}
