package com.wills.leetcode.dynamic_algorithm.middle.decode_ways;

import org.junit.Test;

/**
 * @ClassName DecodeWays
 * @Date 2022/7/20 11:23
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class DecodeWays {

    private static final String source = "11106";

    @Test
    public void test(){
        System.out.println(numDecodings(source));
    }


    public int numDecodings(String s) {
        int len = s.length();
        s = " " + s;
        char[] cs = s.toCharArray();
        int[] dp = new int[len + 1];
        dp[0] = 1;
        for (int i = 1; i <= len; i++) {
            // a : 代表「当前位置」单独形成 item
            // b : 代表「当前位置」与「前一位置」共同形成 item
            int a = cs[i] - '0', b = (cs[i - 1] - '0') * 10 + (cs[i] - '0');
            // 如果 a 属于有效值，那么 dp[i] 可以由 dp[i - 1] 转移过来
            if (1 <= a && a <= 9) dp[i] = dp[i - 1];
            // 如果 b 属于有效值，那么 dp[i] 可以由 dp[i - 2] 或者 dp[i - 1] & dp[i - 2] 转移过来
            if (10 <= b && b <= 26) dp[i] += dp[i - 2];
        }
        return dp[len];
    }
}
