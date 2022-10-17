package com.wills.leetcode.simple.array.house_robber;

import org.junit.Test;

/**
 * @ClassName HouseRobber
 * @Date 2022/7/12 10:23
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class HouseRobber {

    //    private static int[] source = {1, 2, 3, 1};
    private static int[] source = {2, 7, 9, 3, 1};

    @Test
    public void test() {
        System.out.println(rob(source));
    }

    /**
     * 可能想到的算法：
     * 1. 贪心算法 (局部最优解，合并起来可能是最优解) ---》 但是目前为止貌似不是最佳
     * 2. 双指针法 --- 》 可能有效，直接计算完毕，取最大值即可，比较单调叠加，对于复杂情况无法求出正确值
     * 3. 动态规划 ---》 可行
     */
    public int rob(int[] nums) {
        int pre = 0, res = 0;
        for (int num : nums) {
            int tmp = res;
            res = Math.max(pre + num, res);
            pre = tmp;
        }
        return res;
    }

    // 自己做的 双指针解法，但是太单挑，对于复杂情况不符合条件，看看就好
    public int rob1(int[] nums) {
        int slow = 0, fast = 1, sum1 = 0, sum2 = 0, len = nums.length;
        while (slow < len) {
            sum1 += nums[slow];
            slow += 2;
        }

        while (fast < len) {
            sum2 += nums[fast];
            fast += 2;
        }
        return Math.max(sum1, sum2);
    }
}
