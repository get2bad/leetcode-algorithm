# [组合总和(重要，回溯算法经典案例)](https://leetcode.cn/problems/combination-sum/)

给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。

candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。 

对于给定的输入，保证和为 target 的不同组合数少于 150 个。

> 示例 1：
>
> 输入：candidates = [2,3,6,7], target = 7
> 输出：[[2,2,3],[7]]
> 解释：
> 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
> 7 也是一个候选， 7 = 7 。
> 仅有这两种组合。
> 示例 2：
>
> 输入: candidates = [2,3,5], target = 8
> 输出: [[2,2,2,2],[2,3,3],[3,5]]
> 示例 3：
>
> 输入: candidates = [2], target = 1
> 输出: []

本题的关键是，可以重复获取相同元素当作加减的值，所以我们可以使用回溯算法

由于自己对回溯算法了解的不深，故借鉴了一下答案，发现回溯算法不过就是要弄一颗二叉树，二叉树的作用就是当作 减所有可能元素的差值，当作下一次计算用，然后因为涉及到二叉树必定会涉及到 遍历的问题，所以这里二叉树的深度优先搜索遍历。构建二叉树图如下：

![image-20220622162223186](http://image.tinx.top/image-20220622162223186.png)

说明：

以 target = 7 为 根结点 ，创建一个分支的时 做减法 ；
每一个箭头表示：从父亲结点的数值减去边上的数值，得到孩子结点的数值。边的值就是题目中给出的 candidate 数组的每个元素的值；
减到 0 或者负数的时候停止，即：结点 0 和负数结点成为叶子结点；
所有从根结点到结点 0 的路径（只能从上往下，没有回路）就是题目要找的一个结果。

这棵树有 4 个叶子结点的值 0，对应的路径列表是 [[2, 2, 3], [2, 3, 2], [3, 2, 2], [7]]，而示例中给出的输出只有 [[7], [2, 2, 3]]。即：题目中要求每一个符合要求的解是 不计算顺序 的。下面我们分析为什么会产生重复。

**针对具体例子分析重复路径产生的原因（难点）**

产生重复的原因是：在每一个结点，做减法，展开分支的时候，由于题目中说 每一个元素可以重复使用，我们考虑了 所有的 候选数，因此出现了重复的列表。

可不可以在搜索的时候就去重呢？答案是可以的。遇到这一类相同元素不计算顺序的问题，我们在搜索的时候就需要 按某种顺序搜索。具体的做法是：每一次搜索的时候设置 下一轮搜索的起点 begin，请看下图。

![image-20220622162130372](http://image.tinx.top/image-20220622162130372.png)

故有了以下代码：

```java
// 使用回溯算法(主要是二叉树 + 深度优先搜索遍历)
public List<List<Integer>> combinationSum(int[] candidates, int target) {
    int len = candidates.length;
    List<List<Integer>> res = new ArrayList<>();
    if (len == 0) {
        return res;
    }

    Deque<Integer> path = new ArrayDeque<>();
    dfs(candidates, 0, len, target, path, res);
    return res;
}

/**
 * @param candidates 候选数组
 * @param begin      搜索起点
 * @param len        冗余变量，是 candidates 里的属性，可以不传
 * @param target     每减去一个元素，目标值变小
 * @param path       从根结点到叶子结点的路径，是一个栈
 * @param res        结果集列表
 * 使用回溯算法(主要是二叉树 + 深度优先搜索遍历)
 */
private void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
    // target 为负数和 0 的时候不再产生新的孩子结点
    if (target < 0) {
        return;
    }
    if (target == 0) {
        res.add(new ArrayList<>(path));
        return;
    }

    // 重点理解这里从 begin 开始搜索的语意
    for (int i = begin; i < len; i++) {
        path.addLast(candidates[i]);

        // 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i，这里非常容易弄错
        dfs(candidates, i, len, target - candidates[i], path, res);

        // 状态重置
        path.removeLast();
    }
}
```