# [计算力扣银行的钱](https://leetcode-cn.com/problems/calculate-money-in-leetcode-bank/)<font color=green>简单</font>

Hercy 想要为购买第一辆车存钱。他 每天 都往力扣银行里存钱。

最开始，他在周一的时候存入 1 块钱。从周二到周日，他每天都比前一天多存入 1 块钱。在接下来每一个周一，他都会比 前一个周一 多存入 1 块钱。

给你 n ，请你返回在第 n 天结束的时候他在力扣银行总共存了多少块钱。

> 示例 1：
>
> 输入：n = 4
> 输出：10
> 解释：第 4 天后，总额为 1 + 2 + 3 + 4 = 10 。
>
> 示例 2：
>
> 输入：n = 10
> 输出：37
> 解释：第 10 天后，总额为 (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4) = 37 。注意到第二个星期一，Hercy 存入 2 块钱。
>
> 示例 3：
>
> 输入：n = 20
> 输出：96
> 解释：第 20 天后，总额为 (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4 + 5 + 6 + 7 + 8) + (3 + 4 + 5 + 6 + 7 + 8) = 96 。



超级简单的问题，直接秒杀(原本想用递归的方式进行解决的，不过处理起来有点麻烦，此处略，灵活度不如暴力遍历简单)

```java
public int totalMoney(int n) {
    int currentMoney = 1, day = 1;
    int total = 0;
    for (int i = 0; i < n; ++i) {
        total += currentMoney + day - 1;
        if (++day == 8) {
            day = 1;
            ++currentMoney;
        }
    }
    return total;
}
```

