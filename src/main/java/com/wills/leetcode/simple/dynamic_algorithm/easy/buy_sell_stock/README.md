# [买卖股票的最佳时机](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/)

给定一个数组 `prices` ，它的第 `i` 个元素 `prices[i]` 表示一支给定股票第 `i` 天的价格。

你只能选择 **某一天** 买入这只股票，并选择在 **未来的某一个不同的日子** 卖出该股票。设计一个算法来计算你所能获取的最大利润。

返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 `0` 。

 

**示例 1：**

```
输入：[7,1,5,3,6,4]
输出：5
解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
```

**示例 2：**

```
输入：prices = [7,6,4,3,1]
输出：0
解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
```



典型的动态规划的问题

其实解决这个问题很简单，只需要一个公式：

res = 后半段的最大值 - 前半段的最小值

所以有以下两种解决方式

+ 暴力枚举法

```java
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
```

+ 单词循环，上一步的简化版

```java
public static int maxProfit1(int[] prices) {
    int res = 0, minPrice = Integer.MAX_VALUE;
    for (int i = 0; i < prices.length; i++) {
        if(prices[i] < minPrice){
            minPrice = prices[i];
        } else if(prices[i] - minPrice > res){
            res = prices[i] - minPrice;
        }
    }
    return res;
}
```



# [买卖股票的最佳时机 II](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/)

给定一个数组，它的第 *i* 个元素是一支给定股票第 *i* 天的价格。

设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。

**注意：**你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

 

**示例 1:**

```
输入: [7,1,5,3,6,4]
输出: 7
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
```

**示例 2:**

```
输入: [1,2,3,4,5]
输出: 4
解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
```

**示例 3:**

```
输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
```



## 分析

因为要多次买卖股票达到最大值，所以可能要进行递归或者多重遍历



可以这么想： 

买进的条件：如果前一天 比后一天的价格低，那么就可以买进了

卖出的条件： 如果买进一天的后一天比买进的价格低可以卖，但是如果买进后一天的后一天价格比买进一天的后一天价格还低那么就是 买进一天的后两天卖，一次类推



故有以下答案：

```java
public static int maxProfit(int[] prices) {
    if(prices == null || prices.length < 2) return 0;

    // 赋初始值
    int sum = 0;

    for (int i = 1; i < prices.length; i++) {
        int curr = prices[i];
        int pre = prices[i - 1];
        if(curr > pre) sum+= (curr - pre);
    }
    return sum;
}
```

