package com.wills.leetcode.simple.dynamic_algorithm.middle.best_time_to_buy_stock;

import org.junit.Test;

/**
 * @ClassName BestTimeToBuyStock
 * @Date 2022/8/11 11:42
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class BestTimeToBuyStock {

    private static final int[] prices = {1,2,3,0,2};

    @Test
    public void test(){

    }

    public int maxProfit(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len + 1][len + 1];


        return dp[len][len];
    }
}
