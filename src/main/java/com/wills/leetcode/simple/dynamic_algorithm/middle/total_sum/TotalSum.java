package com.wills.leetcode.simple.dynamic_algorithm.middle.total_sum;

/**
 * @author 王帅
 * @ClassName TotalSum
 * @date 2021/6/7 19:53
 * @Version 1.0
 * @Description
 */
public class TotalSum {

    static int count = 0;

    public static void main(String[] args) {
        int[] source = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.println(findTargetSumWays(source, target));
    }
    // 回溯算法
    public static int findTargetSumWays1(int[] nums, int target) {
        backTrack(nums,target,0,0);
        return count;
    }

    public static void backTrack(int nums[], int target, int index, int sum) {
        if (index == nums.length) {
            if(target == sum){
                count ++;
            }
        } else {
            backTrack(nums,target,index + 1, sum + nums[index]);
            backTrack(nums,target,index + 1, sum - nums[index]);
        }
    }

    // 2021-06-07 TODO 待整理动态规划的解题方法，在学校太烦躁了
    public static int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) sum += nums[i];
        // 绝对值范围超过了sum的绝对值范围则无法得到
        if (Math.abs(target) > Math.abs(sum)) return 0;

        int len = nums.length;
        // - 0 +
        int t = sum * 2 + 1;
        int[][] dp = new int[len][t];
        // 初始化
        if (nums[0] == 0) {
            dp[0][sum] = 2;
        } else {
            dp[0][sum + nums[0]] = 1;
            dp[0][sum - nums[0]] = 1;
        }

        for (int i = 1; i < len; i++) {
            for (int j = 0; j < t; j++) {
                // 边界
                int l = (j - nums[i]) >= 0 ? j - nums[i] : 0;
                int r = (j + nums[i]) < t ? j + nums[i] : 0;
                dp[i][j] = dp[i - 1][l] + dp[i - 1][r];
            }
        }
        return dp[len - 1][sum + target];
    }
}
