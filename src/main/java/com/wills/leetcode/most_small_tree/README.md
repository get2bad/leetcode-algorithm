# [二叉树的最小深度](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/)

给定一个二叉树，找出其最小深度。

最小深度是从根节点到最近叶子节点的最短路径上的节点数量。

**说明：**叶子节点是指没有子节点的节点。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/10/12/ex_depth.jpg)

```
输入：root = [3,9,20,null,null,15,7]
输出：2
```

**示例 2：**

```
输入：root = [2,null,3,null,4,null,5,null,6]
输出：5
```



easy题！看题目一定就知道我们要使用DFS算法(深度搜索遍历算法)，也就是 递归方法来解决本题：故答案：

```java
/**
 * 本地直接使用 深度搜索算法 (递归算法)即可解决
 */
public static int minDepth(TreeNode root) {
    if (root == null) return 0;
    else if (root.left == null) return minDepth(root.right) + 1;
    else if (root.right == null) return minDepth(root.left) + 1;
    else return Math.min(minDepth(root.left), minDepth(root.right)) + 1;

}
```

