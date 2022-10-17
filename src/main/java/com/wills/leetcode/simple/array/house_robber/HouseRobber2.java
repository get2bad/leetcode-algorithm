package com.wills.leetcode.simple.array.house_robber;

import org.junit.Test;

/**
 * @ClassName HouseRobber
 * @Date 2022/7/12 10:23
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class HouseRobber2 {

    //    private static int[] source = {1, 2, 3, 1};
//    private static int[] source = {2, 7, 9, 3, 1};
    private static int[] source = {1, 2, 3};
//    private static int[] source = {1, 2, 1, 1};

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
        int len = nums.length;
        if(len == 0) return 0;
        if(len == 1) return nums[0];
        return Math.max(
                getRob(nums,0,len - 1),
                getRob(nums,1,len)
        );
    }

    public int getRob(int[] nums,int start,int end){
        int pre = 0, res = 0;
        for (int i = start; i < end; i++) {
            int tmp = res, num = nums[i];
            res = Math.max(pre + num, res);
            pre = tmp;
        }
        return res;
    }
}
