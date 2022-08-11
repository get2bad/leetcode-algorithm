package com.wills.leetcode.dynamic_algorithm.middle.cash_exchange;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName CashExchange
 * @Date 2022/8/9 13:43
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class CashExchange {

//    private static final int[] coins = {1,2,5};
//    private static final int amount = 11;

//    private static final int[] coins = {2};
//    private static final int amount = 3;

//    private static final int[] coins = {1};
//    private static final int amount = 0;

//    private static final int[] coins = {2,5,10,1};
//    private static final int amount = 27;

    private static final int[] coins = {186, 419, 83, 408};
    private static final int amount = 6249;

    @Test
    public void test() {
        System.out.println(coinChange(coins, amount));
        System.out.println("=============================");
        System.out.println(coinChangeBySelf(coins, amount));
    }

    public int coinChangeBySelf(int[] coins, int amount) {
        int len = coins.length, max = amount + 1;
        int[][] dp = new int[len + 1][amount + 1];
        Arrays.fill(dp[0], 1, amount + 1, max);
        for (int row = 1; row < dp.length; row++) {
            int coin = coins[row - 1];
            for (int col = 1; col < dp[row].length; col++) {
                if (coin > col) dp[row][col] = dp[row - 1][col];
                else dp[row][col] = Math.min(dp[row - 1][col], dp[row][col - coin] + 1);
            }
        }

        int res = dp[len][amount];

        return res == max ? -1 : res;
    }

    public int coinChange(int[] coins, int amount) {
        if (coins.length == 0)
            return -1;

        //声明一个amount+1长度的数组dp，代表各个价值的钱包，第0个钱包可以容纳的总价值为0，其它全部初始化为无穷大
        //dp[j]代表当钱包的总价值为j时，所需要的最少硬币的个数
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, 1, dp.length, Integer.MAX_VALUE);

        //i代表可以使用的硬币索引，i=2代表只在第0个，第1个，第2个这三个硬币中选择硬币
        for (int i = 0; i < coins.length; i++) {
            /**
             * 	当外层循环执行一次以后，说明在只使用前i-1个硬币的情况下，各个钱包的最少硬币个数已经得到，
             * 		有些钱包的值还是无穷大，说明在仅使用前i-1个硬币的情况下，不能凑出钱包的价值
             * 	现在开始再放入第i个硬币，要想放如w[i]，钱包的价值必须满足j>=w[i]，所以在开始放入第i个硬币时，j从w[i]开始
             */
            for (int j = coins[i]; j <= amount; j++) {
                /**
                 * 	如果钱包当前的价值j仅能允许放入一个w[i]，那么就要进行权衡，以获得更少的硬币数
                 * 		如果放入0个：此时钱包里面硬币的个数保持不变： v0=dp[j]
                 * 		如果放入1个：此时钱包里面硬币的个数为：		v1=dp[j-coins[i]]+1
                 * 		 【前提是dp[j-coins[i]]必须有值，如果dp[j-coins[i]]是无穷大，说明无法凑出j-coins[i]价值的钱包，
                 * 	              那么把w[i]放进去以后，自然也凑不出dp[j]的钱包】
                 * 	所以，此时当钱包价值为j时，里面的硬币数目为 dp[j]=min{v0,v1}
                 * 	如果钱包当前价值j能够放入2个w[i]，就要再进行一次权衡
                 * 		如果不放人第2个w[i]，此时钱包里面硬币数目为，v1=dp[j]=min{v0,v1}
                 * 		如果放入第2个w[i],  此时钱包里面硬币数目为，v2=dp[j-coins[i]]+1
                 * 	所以，当钱包的价值为j时，里面的硬币数目为dp[j]=min{v1,v2}=min{v0,v1,v2}
                 * 	钱包价值j能允许放入3个，4个.........w[i]，
                 * 	不断更新dp[j]，最后得到在仅使用前i个硬币的时候，每个钱包里的最少硬币数目
                 */
                int index = j - coins[i];
                int tmp = dp[index];
                if (tmp != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], tmp + 1);
                }
            }
        }
        if (dp[amount] != Integer.MAX_VALUE)
            return dp[amount];
        return -1;
    }

    // 贪心算法（不是最优解）
    public int coinChangeByFaild(int[] coins, int amount) {
        int res = 0;
        Arrays.sort(coins);
        for (int i = coins.length - 1; i >= 0; i--) {
            int currCoins = coins[i];
            if (amount < currCoins) continue;
            int coinCnt = amount / currCoins;
            amount -= (currCoins * coinCnt);
            res += coinCnt;
            if (amount == 0) break;
        }
        if (amount != 0) return -1;
        return res;
    }
}
