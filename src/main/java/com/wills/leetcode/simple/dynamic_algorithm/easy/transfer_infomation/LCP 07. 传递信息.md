# [LCP 07. 传递信息(很重要)](https://leetcode.cn/problems/chuan-di-xin-xi/)

小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：

有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0
每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。
每轮信息必须需要传递给另一个人，且信息可重复经过同一个人
给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。

> 示例 1：
>
> 输入：n = 5, relation = [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]], k = 3
>
> 输出：3
>
> 解释：信息从小 A 编号 0 处开始，经 3 轮传递，到达编号 4。共有 3 种方案，分别是 0->2->0->4， 0->2->1->4， 0->2->3->4。
>
> 示例 2：
>
> 输入：n = 3, relation = [[0,2],[2,1]], k = 2
>
> 输出：0
>
> 解释：信息不能从小 A 处经过 2 轮传递到编号 2

本题虽然是简单题，但是对于现在的我来说比较难，其实刚刚拿到这道题我有想用 ```回溯算法```的，但是发现实现起来比较麻烦(其实跟DFS差不多哈哈哈)，所以看了答案，故有了如下答案

## 动态规划

本题使用动态规划还是很有挑战性的，首先使用动态规划

1. 我们要确定小问题 - relation数组的子数组
2. 确定使用的动态规划数组 int[][] dp = new int\[k+1][n]; 其中 k+1 表示最后一轮 n 表示到达的目标
3. 确定边界问题，如果只有一个元素，那么dp\[0][0] = 1
4. 确定状态转移公式 dp\[i + 1][dist] += dp\[i][index];
5. 返回值 return dp\[k][n - 1]; (最终我们只需要拿到 dp\[k][n - 1] 表示 第 k轮到达 n-1(索引)的结果即可，根据前面的状态转移公式推到而来)

```java
public int numWaysByDynamicAlgorithm(int n, int[][] relation, int k){
    int[][] dp = new int[k + 1][n];
    dp[0][0] = 1;
    for (int i = 0; i < k; i++) {
        for (int[] edge : relation) {
            int index = edge[0], dist = edge[1];
            dp[i + 1][dist] += dp[i][index];
        }
    }
    return dp[k][n - 1];
}
```

## DFS递归

其实 DFS递归的原理跟回溯算法如出一辙！其根本就是用的是递归算法，然后寻找在第K轮到达的数量，每到达一次就 + 1，最终返回结果即可。

```java
int res = 0;
List<List<Integer>> edges;

public int numWays(int n, int[][] relation, int k) {
    for (int i = 0; i < n; i++) {
        edges.add(new ArrayList<>());
    }
    for (int[] edge : relation) {
        int index = edge[0], dst = edge[1];
        edges.get(index).add(dst);
    }
    dfs(0, 0, n, k);
    return res;
}

public void dfs(int index, int steps, int n, int k) {
    // 如果达到了 k 要求的轮数，满足 index == n - 1时会res++，否则直接跳出这个递归
    if (steps == k) {
        if (index == n - 1) {
            res++;
        }
        return;
    }
    // 拿到本次传递的目标进行递归
    List<Integer> list = edges.get(index);
    for (Integer nextIndex : list) {
        dfs(nextIndex, steps++, n, k);
    }
}
```

