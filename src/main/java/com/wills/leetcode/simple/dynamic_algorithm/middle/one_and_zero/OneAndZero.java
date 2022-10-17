package com.wills.leetcode.simple.dynamic_algorithm.middle.one_and_zero;

/**
 * @author 王帅
 * @ClassName OneAndZero
 * @date 2021/6/6 08:23
 * @Version 1.0
 * @Description
 */
public class OneAndZero {

    public static void main(String[] args) {
        String[] source = {"10", "0001", "111001", "1", "0"};
        int m = 5, n = 3;
        System.out.println(findMaxForm1(source, m, n));
    }

    public static int findMaxForm1(String[] strs, int m, int n) {
        int len = strs.length;
        int[][][] dp = new int[len + 1][m + 1][n + 1];

        for (int i = 1; i <= len; i++) {
            // 注意：有一位偏移
            int[] count = countZeroAndOne(strs[i - 1]);
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    // 先把上一行抄下来
                    dp[i][j][k] = dp[i - 1][j][k];
                    int zeros = count[0];
                    int ones = count[1];
                    if (j >= zeros && k >= ones) {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - zeros][k - ones] + 1);
                    }
                }
            }
        }
        return dp[len][m][n];
    }

    private static int[] countZeroAndOne(String str) {
        int[] cnt = new int[2];
        for (char c : str.toCharArray()) {
            cnt[c - '0']++;
        }
        return cnt;
    }

    public static int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[] oneSource = new int[len];
        int[] zeroSource = new int[len];
        // 循环这个
        for (int i = 0; i < len; i++) {
            int element = Integer.parseInt(strs[i]), eleCnt = strs[i].length();

            int oneCnt = 0, zeroCnt = 0;
            // 1的个数
            //假设是1010，第一次从0开始不动，1右移变成0101,与运算成立，count+1，
            // 第二次，0010，不符合，第三次，0001，符合，count++
            for (int j = 0; j < eleCnt; j++) {
                if (((element >> j) & 1) == 1) {
                    oneCnt++;
                }
            }
            // 0的个数
            zeroCnt += eleCnt - oneCnt;

            oneSource[i] = oneCnt;
            zeroSource[i] = zeroCnt;
        }
        return len;
    }
}
