package com.wills.leetcode.dynamic_algorithm.easy.buy_sell_stock;

/**
 * @author 王帅
 * @date 2021-03-03 10:27:18
 * @description:
 * 最佳实践买卖股票1
 */
public class BuyAndSellStock1 {

    /**
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     *
     * 示例 1：
     * 输入：[7,1,5,3,6,4]
     * 输出：5
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
     *
     * 示例 2：
     * 输入：prices = [7,6,4,3,1]
     * 输出：0
     * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
     */
    public static void main(String[] args) {
        int[] source = {7,1,5,3,6,4};
        System.out.println(maxProfit1(source));
    }

    /**
     * 思路：
     * 暴力枚举方法
     * 计算公式： 前i天的最大收益 = max{前i-1天的最大收益，第i天的价格-前i-1天中的最小价格}
     *
     * 时间复杂度: O(n^2)
     */
    public static int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i+1; j < prices.length; j++) {
                int source = prices[j] - prices[i];
                if(source > res) res = source;
            }
        }
        return res;
    }

    /**
     * 思路：
     * 典型的动态规划问题
     * 计算公式： 前i天的最大收益 = max{前i-1天的最大收益，第i天的价格-前i-1天中的最小价格}
     *
     * 时间复杂度: O(n)
     */
    public static int maxProfit1(int[] prices) {
        int res=0,minPrice=Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if(minPrice > prices[i]){
                minPrice = prices[i];
            } else if (prices[i] - minPrice > res){
                res = prices[i] - minPrice;
            }
        }
        return res;
    }


}
