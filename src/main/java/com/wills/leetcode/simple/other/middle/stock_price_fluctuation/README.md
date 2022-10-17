# [股票价格波动](https://leetcode-cn.com/problems/stock-price-fluctuation/)<font color=orange>中等</font>

给你一支股票价格的数据流。数据流中每一条记录包含一个 时间戳 和该时间点股票对应的 价格 。

不巧的是，由于股票市场内在的波动性，股票价格记录可能不是按时间顺序到来的。某些情况下，有的记录可能是错的。如果两个有相同时间戳的记录出现在数据流中，前一条记录视为错误记录，后出现的记录 更正 前一条错误的记录。

请你设计一个算法，实现：

更新 股票在某一时间戳的股票价格，如果有之前同一时间戳的价格，这一操作将 更正 之前的错误价格。
找到当前记录里 最新股票价格 。最新股票价格 定义为时间戳最晚的股票价格。
找到当前记录里股票的 最高价格 。
找到当前记录里股票的 最低价格 。
请你实现 StockPrice 类：

StockPrice() 初始化对象，当前无股票价格记录。
void update(int timestamp, int price) 在时间点 timestamp 更新股票价格为 price 。
int current() 返回股票 最新价格 。
int maximum() 返回股票 最高价格 。
int minimum() 返回股票 最低价格 。
 

> 示例 1：
> 
> 输入：
> ["StockPrice", "update", "update", "current", "maximum", "update", "maximum", "update", "minimum"]
> [[], [1, 10], [2, 5], [], [], [1, 3], [], [4, 2], []]
> 输出：
> [null, null, null, 5, 10, null, 5, null, 2]
> 
> 解释：
> StockPrice stockPrice = new StockPrice();
> stockPrice.update(1, 10); // 时间戳为 [1] ，对应的股票价格为 [10] 。
> stockPrice.update(2, 5); // 时间戳为 [1,2] ，对应的股票价格为 [10,5] 。
> stockPrice.current(); // 返回 5 ，最新时间戳为 2 ，对应价格为 5 。
> stockPrice.maximum(); // 返回 10 ，最高价格的时间戳为 1 ，价格为 10 。
> stockPrice.update(1, 3); // 之前时间戳为 1 的价格错误，价格更新为 3 。
>  // 时间戳为 [1,2] ，对应股票价格为 [3,5] 。
> stockPrice.maximum(); // 返回 5 ，更正后最高价格为 5 。
> stockPrice.update(4, 2); // 时间戳为 [1,2,4] ，对应价格为 [3,5,2] 。
> stockPrice.minimum(); // 返回 2 ，最低价格时间戳为 4 ，价格为 2 。



这道题刚拿到时一看，我靠，这么多文字，肯定很难！然后读来读去。。。我靠，这不就是考验编程的数据结构问题么？？？？我觉得准确的说应该定义为 **简单题**！下面是代码，注释清晰明了~

```java
class StockPrice {

    int maxTime = Integer.MIN_VALUE;
    // key: timestamp value: price
    private Map<Integer,Integer> source;
    // key: price value: priceCount
    private TreeMap<Integer,Integer> currentSource;

    // 初始化
    public StockPrice() {
        source = new HashMap<>();
        currentSource = new TreeMap<>();
    }

    public void update(int timestamp, int price) {
        // 找到最大的时间
        maxTime = Math.max(timestamp, maxTime);
        if (source.containsKey(timestamp)) {
            int old = source.get(timestamp);
            int cnt = currentSource.get(old);
            // 判断相同的数目是否到了1，如果到了1 就删除这个
            if (cnt == 1) currentSource.remove(old);
            // 如果没到1，就重新赋值
            else currentSource.put(old, cnt - 1);
        }
        source.put(timestamp, price);
        currentSource.put(price, currentSource.getOrDefault(price, 0) + 1);
    }

    // 直接拿到当前最大时间的价格即可
    public int current() {
        Integer currPrice = source.get(maxTime);
        return currPrice;
    }

    // 由于使用的是有序的，天生可以排序，所以直接拿到最后的值就是最大值
    public int maximum() {
        return currentSource.lastKey();
    }
    // 由于使用的是有序的，天生可以排序，所以直接拿到最开始的值就是最小值
    public int minimum() {
        return currentSource.firstKey();
    }
}
```

简简单单AC！ 开启美好的一天~~~~
