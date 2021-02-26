# [平衡二叉树](https://leetcode-cn.com/problems/balanced-binary-tree/)

给定一个二叉树，判断它是否是高度平衡的二叉树。

本题中，一棵高度平衡二叉树定义为：

> 一个二叉树*每个节点* 的左右两个子树的高度差的绝对值不超过 1 。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/10/06/balance_1.jpg)

```
输入：root = [3,9,20,null,null,15,7]
输出：true
```

**示例 2：**

![img](https://assets.leetcode.com/uploads/2020/10/06/balance_2.jpg)

```
输入：root = [1,2,2,3,3,null,null,4,4]
输出：false
```

**示例 3：**

```
输入：root = []
输出：true
```

 

思路：

因为是查看二叉树是不是盖度平衡的二叉树，
涉及到二叉树的遍历 肯定就涉及到
DFS 深度搜索优先算法
BFS 广度搜索优先算法
基于此，我们可以使用 DFS 来探寻左右子树的深度(递归)

// 为什么要加isBalanced(root.left) && isBalanced(root.right)？
因为要遍历每一个根节点是否是高度相同的二叉树，不光要看这一整棵树是否高度平衡
本方法为 自顶向下递归遍历

时间复杂度为: O(n^2)
空间复杂度为： O(n)

答案：

```java
public static boolean isBalanced(TreeNode root) {
    if(root == null) return true;
    return Math.abs(getHeight(root.left) - getHeight(root.right)) <= 1
            && isBalanced(root.left)
            && isBalanced(root.right);
}

public static Integer getHeight(TreeNode root){
    if(root == null){
        return 0;
    }  else {
        return Math.max(getHeight(root.left),getHeight(root.right)) + 1;
    }
}
```

