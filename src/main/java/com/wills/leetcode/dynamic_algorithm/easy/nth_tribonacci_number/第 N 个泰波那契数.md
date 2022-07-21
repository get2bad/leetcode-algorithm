# [1137. 第 N 个泰波那契数](https://leetcode.cn/problems/n-th-tribonacci-number/)

泰波那契序列 Tn 定义如下： 

T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2

给你整数 n，请返回第 n 个泰波那契数 Tn 的值。

> 示例 1：
>
> 输入：n = 4
> 输出：4
> 解释：
> T_3 = 0 + 1 + 1 = 2
> T_4 = 1 + 1 + 2 = 4
> 示例 2：
>
> 输入：n = 25
> 输出：1389537

没啥好说的，直接dp动态规划搞起来

```java
public int tribonacci(int n) {
    if(n == 0) return 0;
    if(n <= 2) return 1;
    int[] dp = new int[n + 1];
    dp[0] = 0;
    dp[1] = 1;
    dp[2] = 1;
    for (int i = 3; i <= n; i++) {
        dp[i] = dp[i - 1] + dp[i - 2] + dp[i -3];
    }
    return dp[n];
}
```