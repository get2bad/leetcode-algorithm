package com.wills.leetcode.waiting_to_be_sorted.find_132_pattern;

import java.util.Stack;

/**
 * @author 王帅
 * @date 2021-03-24 09:18:03
 * @description:
 * 132 模式
 */
public class Find132Pattern {

    /**
     * 给定一个整数序列：a1, a2, ..., an，一个132模式的子序列 ai, aj,
     * ak 被定义为：当 i < j < k 时，ai < ak < aj。设计一个算法，当给定有 n 个数字的序列时，
     * 验证这个序列中是否含有132模式的子序列。
     *
     * 注意：n 的值小于15000。
     *
     * 示例1:
     * 输入: [1, 2, 3, 4]
     * 输出: False
     * 解释: 序列中不存在132模式的子序列。
     *
     * 示例 2:
     * 输入: [3, 1, 4, 2]
     * 输出: True
     * 解释: 序列中有 1 个132模式的子序列： [1, 4, 2].
     *
     * 示例 3:
     * 输入: [-1, 3, 2, 0]
     * 输出: True
     *
     * 解释: 序列中有 3 个132模式的的子序列: [-1, 3, 2], [-1, 3, 0] 和 [-1, 2, 0].
     */
    public static void main(String[] args) {
        int[] source = {1, 2, 3, 4};
        System.out.println(find132pattern(source));
    }

    public static boolean find132pattern(int[] nums) {
        int n = nums.length;
        int[] min = new int[n];
        min[0] = nums[0];
        for (int i = 1; i < n; i++) {
            min[i] = Math.min(min[i - 1],nums[i]);
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = n -1; i >= 0; i--) {
            // 遍历当前传入的数组，符合当前数组 > 当前最小数数组 就进行以下操作
            // 判断后一个是否小于当前数组元素，如果小于，就符合 低高低 的规则
            // 那么直接返回true即可
            if(nums[i] > min[i]){
                // 如果栈中的数字比 min[i] 还小或者相同，那么说明不可能是 ak，就弹出来
                while(!stack.isEmpty() && stack.peek() <= min[i]){
                    // 弹出这个元素
                    stack.pop();
                }
                // 检查一下栈顶元素是不是满足 ai<stack.peek()<aj，如果满足就说明找到了
                if(!stack.isEmpty() && stack.peek() < nums[i]){
                    // 符合 低高低 的规则，那么就返回true
                    return true;
                }
                // 不管怎样都要push进来当前的数，因为当前的数满足了大于 min[i]
                stack.push(nums[i]);
            }
        }
        return false;
    }
}
