package com.wills.leetcode.top100.Q30_edit_distance;

import org.junit.Test;

/**
 * @ClassName EditDistance
 * @Date 2022/11/3 10:13
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class EditDistance {

    private static final String source = "horse";
    private static final String source2 = "execution";

    @Test
    public void test(){
        System.out.println(minDistance(source, source2));
    }

    /**
     *  给你两个单词word1 和word2， 请返回将word1转换成word2 所使用的最少操作数
     * 你可以对一个单词进行如下三种操作：
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     *
     * 示例1：
     * 输入：word1 = "horse", word2 = "ros"
     * 输出：3
     * 解释：
     * horse -> rorse (将 'h' 替换为 'r')
     * rorse -> rose (删除 'r')
     * rose -> ros (删除 'e')
     *
     * 示例2：
     * 输入：word1 = "intention", word2 = "execution"
     * 输出：5
     * 解释：
     * intention -> inention (删除 't')
     * inention -> enention (将 'i' 替换为 'e')
     * enention -> exention (将 'n' 替换为 'x')
     * exention -> exection (将 'n' 替换为 'c')
     * exection -> execution (插入 'u')
     *
     * 动态规划，值得仔细品味
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length(),n = word2.length();
        int[][] dp = new int[m+1][n+1];
        //赋边界值 空串就是当前的索引值才能替换完成的
        for(int i = 1;i <= m ;i++){
            dp[i][0] = i;
        }
        //赋边界值 空串就是当前的索引值才能替换完成的
        for(int i = 1;i <= n ;i++){
            dp[0][i] = i;
        }
        // 状态转移
        for(int i = 1 ; i <= m; i++){
            for(int j = 1; j <= n; j++){
                // 从第一个字母开始，如果匹配
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    // 就与斜上角的值相同
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    // 如果两者的值不匹配，就取 dp[i-1][j-1] dp[i][j-1] dp[i-1][j] 的最小值 + 1
                    // 为什么？ 因为要取最优解
                    dp[i][j] = Math.min(dp[i-1][j-1],
                            Math.min(dp[i][j-1],dp[i-1][j]))+1;
                }
            }
        }
        return dp[m][n];
    }
}
