# [统计最高分的节点数目](https://leetcode-cn.com/problems/count-nodes-with-the-highest-score/)

给你一棵根节点为 `0` 的 **二叉树** ，它总共有 `n` 个节点，节点编号为 `0` 到 `n - 1` 。

同时给你一个下标从 **0** 开始的整数数组 `parents` 表示这棵树，其中 `parents[i]` 是节点 `i` 的父节点。由于节点 `0` 是根，所以 `parents[0] == -1` 。

一个子树的 **大小** 为这个子树内节点的数目。每个节点都有一个与之关联的 **分数** 。

求出某个节点分数的方法是，将这个节点和与它相连的边全部 **删除** ，剩余部分是若干个 **非空** 子树，这个节点的 **分数** 为所有这些子树 **大小的乘积** 。

请你返回有 **最高得分** 节点的 **数目** 。

**示例 1:**

![example-1](http://rloqc3ngo.hd-bkt.clouddn.com/example-1.png)

```
输入：parents = [-1,2,0,2,0]
输出：3
解释：
- 节点 0 的分数为：3 * 1 = 3
- 节点 1 的分数为：4 = 4
- 节点 2 的分数为：1 * 1 * 2 = 2
- 节点 3 的分数为：4 = 4
- 节点 4 的分数为：4 = 4
最高得分为 4 ，有三个节点得分为 4 （分别是节点 1，3 和 4 ）。
```

**示例 2：**

![example-2](http://rloqc3ngo.hd-bkt.clouddn.com/example-2.png)

```
输入：parents = [-1,2,0]
输出：2
解释：
- 节点 0 的分数为：2 = 2
- 节点 1 的分数为：2 = 2
- 节点 2 的分数为：1 * 1 = 1
最高分数为 2 ，有两个节点分数为 2 （分别为节点 0 和 1 ）。
```



本题目的要求 真的很绕，我们拿示例1来举例(提前了解子树的分数怎么得的，假设a去除x元素后，还剩下2个子树，其中第一个子树有3个元素 第二个子树有1个元素，则总分数为 3 * 1 = 3 就这么简单),以下全部用先序遍历的方式阐述子树：

+ 当去除0 时 剩下 [2,3,4] , [4]，则分数为 3 * 1 = 3
+ 当去除1 时 剩下 [0,2,3,4] 则分数为 4=4
+ 当去除2 时 剩下 [3] [0,4] [1] 则分数为 1 * 2 * 1 = 2
+ 当去除3 时 剩下 [0,2,1,4] 则分数为 4 = 4 
+ 当去除4 时 剩下 [0,2,3,1] 则分数为 4=4

因为最高得分为4，而4的方案有三个，那么答案就是 res = 3 



那么现在我们的意图很明显了，我们使用深度优先的模式去搜索最高分数有几个方案



## DFS

在一棵树中，当把一个节点和与它相连的所有边删除，剩余部分最多为三棵非空子树，即原节点的左子树（如果有），右子树（如果有），以及把以这个节点为根结点的子树移除所形成的子树（除根结点外均有）。

而这个节点的分数为这些子树的节点个数的乘积。

我们可以先用 parents 数组统计出每个节点的子节点，然后使用<font color=red>深度优先搜索</font>来计算以每个节点为根结点的子树的大小，同时计算每个节点的大小，作为深度优先搜索的返回值，最后统计最大分数出现的次数。在实现上，统计最大分数出现的次数可以放到深度优先搜索中完成，从而节省一部分空间。

parents => {-1,2,0,2,0}

children => {{2,4},{null,null},{1,3},{null,null},{null,null}}

进入 dfs方法后的逻辑:

node => 0 -> 2 -> 1 <- 2 -> 3 -> 4

score => 1 -> 3 -> 4 -> 4 -> 4

maxScore => 1 -> 3 -> 4

res => 1 -> 1 -> 1 -> 2 -> 3

```java
long maxScore = 0;
int res = 0;
int len;
List<Integer>[] children;
public int countHighestScoreNodes(int[] parents) {
    len = parents.length;
    children = new List[len];
    // 初始化 children 集合元素为一个 ArrayList
    for (int i = 0; i < len; i++) children[i] = new ArrayList<>();
  	// 计算子节点
    for (int i = 0; i < len; i++) {
        // 拿到传入的 元素值
        int p = parents[i];
        // 如果不是根元素
        if (p != -1) {
            children[p].add(i);
        }
    }
    dfs(0);
    return res;
}
// len = 5
// children => {{2,4},{null,null},{1,3},{null,null},{null,null}}
public int dfs(int node) {
    long score = 1;
    int size = len - 1;
    // 遍历 children 这个 list 列表
    for (int c : children[node]) {
        int t = dfs(c);
        // 求出现在的跟递归返回的 t 值
        score *= t;
        size -= t;
    }
    // 子树的乘积
    if (node != 0) {
        score *= size;
    }
    // 如果 跟 maxScore 最大分数的相同，说明分数相同的子树多了一个
    if (score == maxScore) {
        res++;
    } else if (score > maxScore) {
        // 如果不是 当前分数 大于 maxScore 则赋值 并将结果值复位为1
        maxScore = score;
        res = 1;
    }
    return len - size;
}
```

