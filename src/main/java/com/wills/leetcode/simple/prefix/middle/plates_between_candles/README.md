# [蜡烛之间的盘子](https://leetcode-cn.com/problems/plates-between-candles/)

给你一个长桌子，桌子上盘子和蜡烛排成一列。给你一个下标从 **0** 开始的字符串 `s` ，它只包含字符 `'*'` 和 `'|'` ，其中 `'*'` 表示一个 **盘子** ，`'|'` 表示一支 **蜡烛** 。

同时给你一个下标从 **0** 开始的二维整数数组 `queries` ，其中 `queries[i] = [lefti, righti]` 表示 **子字符串** `s[lefti...righti]` （**包含左右端点的字符**）。对于每个查询，你需要找到 **子字符串中** 在 **两支蜡烛之间** 的盘子的 **数目** 。如果一个盘子在 **子字符串中** 左边和右边 **都** 至少有一支蜡烛，那么这个盘子满足在 **两支蜡烛之间** 。

- 比方说，`s = "||**||**|*"` ，查询 `[3, 8]` ，表示的是子字符串 `"*||***\**\***|"` 。子字符串中在两支蜡烛之间的盘子数目为 `2` ，子字符串中右边两个盘子在它们左边和右边 **都** 至少有一支蜡烛。

请你返回一个整数数组 `answer` ，其中 `answer[i]` 是第 `i` 个查询的答案。

 

**示例 1:**

![ex-1](http://rloqc3ngo.hd-bkt.clouddn.com/ex-1.png)

```
输入：s = "**|**|***|", queries = [[2,5],[5,9]]
输出：[2,3]
解释：
- queries[0] 有两个盘子在蜡烛之间。
- queries[1] 有三个盘子在蜡烛之间。
```

**示例 2:**

![ex-2](http://rloqc3ngo.hd-bkt.clouddn.com/ex-2.png)

```
输入：s = "***|**|*****|**||**|*", queries = [[1,17],[4,5],[14,17],[5,11],[15,16]]
输出：[9,0,0,0,0]
解释：
- queries[0] 有 9 个盘子在蜡烛之间。
- 另一个查询没有盘子在蜡烛之间。
```



看到这道题，第一感觉 woc怎么这么长的题目啊，完了我要趴窝了。。。。然后看了看发现emmm，好像暴力可以解出来，就有了以下的代码:

## 暴力法

关键点就是先 切割传入的字符串到 queries 内的两个边界的位置，然后对其进行暴力循环。

```java
public static int[] platesBetweenCandles(String s, int[][] queries) {
    int len = queries.length;
    int[] res = new int[len];

    for (int i = 0; i < len; i++) {
        String substring = s.substring(queries[i][0], queries[i][1] + 1);
        res[i] = getCount(substring);
    }

    return res;
}

public static int getCount(String s){
    int res = 0;
    int start = s.indexOf("|");
    int end = s.lastIndexOf("|");
    if(start == -1 || end == -1) return  0;
    char[] source = s.toCharArray();
    for (int i = start; i < end; i++) {
        if(source[i] != '|'){
            res++;
        }
    }
    return res;
}
```

果不其然，超时了，给的用例真的太多了 -__-

![image-20220309110849288](http://rloqc3ngo.hd-bkt.clouddn.com/image-20220309110849288.png)



没办法，算法基础太薄弱 无奈只能看了 三叶姐的答案，发现可以使用 前缀和 + 二分查找来解决

## 前缀和 + 二分法

在这里简述一下流程:

前缀和: 遍历这个字符串，如果遇到了 * 就跟之前的值 + 1，如果遇到了 | 就将其索引添加到 list中，方便后续的二分查找。

然后进入了二分查找阶段：

1. 先找出左边界

   就是将上面的 list 进行查找，

   如果遍历的开始 start点小于等于蜡烛的中间索引值，就进行 right缩小到mid值

   否则就 将 left = mid + 1，直到 left >= right时使用 list.get(right) 就是我们要找的左边界

   假设 start = 3

   list => 1,3,5,7

   left => 0

   Right => 3

   mid => 1

   最终

   left => 0 1

   right => 3 1

   Mid => 1 0

   最终 right = 1，就可以拿到 list中的3了

2. 右边界同理

最后关键的异步，将前缀和的右边界 - 左边界的值就是我们的答案

```java
public static int[] platesBetweenCandles1(String s, int[][] queries) {
    char[] strArray = s.toCharArray();
    int strLen = strArray.length, qsLen = queries.length;
    int[] ans = new int[qsLen], sum = new int[strLen + 1];
    List<Integer> list = new ArrayList<>();
    // 执行前缀和的准备操作，遇到 * 就和之前的加1否则加0，list用于存储 | 的索引
    for (int i = 0; i < strLen; i++) {
        if (strArray[i] == '|') list.add(i);
        sum[i + 1] = sum[i] + (strArray[i] == '*' ? 1 : 0);
    }
    // r如果没有蜡烛 就直接原样返回就行 0,0,0,0
    if (list.size() == 0) return ans;
    // 遍历需要查找的区间，查找盘子
    for (int i = 0; i < qsLen; i++) {
        // 拿到起始点/结束点
        int start = queries[i][0], end = queries[i][1];
        int leftIndex = -1, rightIndex = -1;
        // 找到 start 右边最近的蜡烛
        int left = 0, right = list.size() - 1;
        while (left < right) {
            // 找到一半的值(二分法)
            int mid = left + right >> 1;
            // 如果找到蜡烛的索引 大于等于 start，就将 right的值切换为中间值继续寻找
            if (list.get(mid) >= start) right = mid;
            // 否则就移动 left值
            else left = mid + 1;
        }
        // 如果 右边界索引 大于 start  ** 拿到左边界的蜡烛索引 **
        if (list.get(right) >= start) leftIndex = list.get(right);
        // 否则 start > 右边界 是不合理的就置0 进行下一次
        else continue;
        // 找到 end 左边最近的蜡烛 同上
        left = 0; right = list.size() - 1;
        while (left < right) {
            int mid = left + right + 1 >> 1;
            if (list.get(mid) <= end) left = mid;
            else right = mid - 1;
        }
        if (list.get(right) <= end) rightIndex = list.get(right);
        else continue;
        // 关键！因为前面用了前缀和来，所以两个索引相减就是答案！
        if (leftIndex <= rightIndex) ans[i] = sum[rightIndex + 1] - sum[leftIndex];
    }
    return ans;
}
```



## 前缀和

上面的升级版，直接进行预处理，具体步骤看注释

```java
// 前缀和
public static int[] platesBetweenCandles2(String s, int[][] qs) {
    char[] cs = s.toCharArray();
    int n = cs.length, m = qs.length;
    int[] leftCandy = new int[n], rightCandy = new int[n];
    int[] sum = new int[n + 1];
    for (int i = 0, j = n - 1, p = -1, q = -1; i < n; i++, j--) {
        if (cs[i] == '|') p = i;
        if (cs[j] == '|') q = j;
        // 如果遇到了蜡烛会将索引赋值到对应的槽中，否则就是 -1
        leftCandy[i] = p; rightCandy[j] = q;
        // 进行前缀和的操作
        sum[i + 1] = sum[i] + (cs[i] == '*' ? 1 : 0);
    }
    int[] ans = new int[m];
    for (int i = 0; i < m; i++) {
        // 拿到需要查找的 开始 结束 边界
        int start = qs[i][0], end = qs[i][1];
        // 拿到开始结束的索引
        int startCandyIndex = rightCandy[start], endCandyIndex = leftCandy[end];
        if (startCandyIndex != -1 && startCandyIndex <= endCandyIndex)
            // 求出差值
            ans[i] = sum[endCandyIndex + 1] - sum[startCandyIndex];
    }
    return ans;
}
```
