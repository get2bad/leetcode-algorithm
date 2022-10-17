package com.wills.leetcode.simple.dynamic_algorithm.easy.masseuse;

/**
 * @author 王帅
 * @date 2021-04-06 10:11:11
 * @description: 按摩师
 */
public class Masseuse {

    /**
     * 一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，
     * 因此她不能接受相邻的预约。给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。
     * <p>
     * 示例 1：
     * 输入： [1,2,3,1]
     * 输出： 4
     * 解释： 选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。
     * <p>
     * 示例 2：
     * 输入： [2,7,9,3,1]
     * 输出： 12
     * 解释： 选择 1 号预约、 3 号预约和 5 号预约，总时长 = 2 + 9 + 1 = 12。
     * <p>
     * 示例 3：
     * 输入： [2,1,4,5,3,1,1,3]
     * 输出： 12
     * 解释： 选择 1 号预约、 3 号预约、 5 号预约和 8 号预约，总时长 = 2 + 4 + 3 + 3 = 12。
     */
    public static void main(String[] args) {
        int[] source = {2, 1, 4, 5, 3, 1, 1, 3};
        System.out.println(massage(source));
    }

    /**
     * before: 用来保存上一次加减的结果
     * after： 现在遍历叠加后的结果
     */
    public static int massage(int[] nums) {
        int before = 0, after = 0;
        for (int i : nums) {
            int temp = after;
            after = Math.max(after, before + i);
            before = temp;
        }
        return after;
    }
}
