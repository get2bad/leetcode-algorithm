package com.wills.leetcode.dynamic_algorithm.simple.maximum_subarray;

/**
 * @author 王帅
 * @date 2021-02-09 10:44:33
 * @description: 最大子序和
 */
public class MaximumSubarray {

    /**
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * <p>
     * 示例 1：
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     * <p>
     * ****进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。***
     */
    public static void main(String[] args) {
        int[] source = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(source));
    }


    /**
     * 使用贪心算法来（动态规划）解决本题,思路：
     * 1. 声明一个res cur_num变量，res指向nums第一个元素，cur_num 初始值为0
     * 2. 遍历这个Nums数组
     * 3. 不使用if语句，我们使用Math.max找出两者的最大值
     * 4.分别进行两步取max
     * a. 当前遍历元素与 cur_num的sum 还有 当前元素的最大值。如果当前元素大于两者的加和，那么就相当于
     * 重置当前遍历的元素的加和，中断之前的遍历
     * b.取返回的 res 和 cur_nums的最大值，这一步的意义就是看当前 a 步的最大值与要返回的res相比的最大值，
     * 找出 最大值进行返回
     * 5. 返回结果 res
     * 时间复杂度 O(n)
     * 如果不懂，请看官方的解题思路：
     * <a href="https://leetcode-cn.com/problems/maximum-subarray/solution/zui-da-zi-xu-he-by-leetcode-solution/">最大子序和</a>
     */
    public static int maxSubArray(int[] nums) {
        if (nums.length == 1) return nums[0];
        int res = 0, cur_num = 0;
        for (int i = 0; i < nums.length; i++) {
            cur_num = Math.max(nums[i], cur_num + nums[i]);

            res = Math.max(cur_num, res);
        }
        return res;
    }

    /**
     * 思路:
     * 1.因为题目中条件是具有最大和的 **连续** 子数组，所以强调连续，那么我们可以使用for循环遍历
     * 2.说起for循环，那么我们可以使用类似于冒泡算法，来计算叠加计算每一项的值，叠加后如果比前一个数字大，就将返回的res重新赋值
     * 3.直到遍历完一个完整的数组位置
     * 时间复杂度为 O(n^2)
     */
    public static int maxSubArray1(int[] nums) {
        if (nums.length == 1) return nums[0];
        int res = nums[0];
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            res = Math.max(temp, res);
            for (int j = i + 1; j < nums.length; j++) {
                temp += nums[j];
                res = Math.max(temp, res);
            }
        }
        return res;
    }
}
