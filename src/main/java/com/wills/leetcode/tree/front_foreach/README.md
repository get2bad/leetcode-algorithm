# [ 二叉树的前序遍历](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/)<font color=green>简单</font>

给你二叉树的根节点 root ，返回它节点值的 前序 遍历。

 ```text
 示例 1：
 输入：root = [1,null,2,3]
 输出：[1,2,3]
 
 示例 2：
 输入：root = []
 输出：[]
 
 示例 3：
 输入：root = [1]
 输出：[1]
 
 示例 4：
 输入：root = [1,2]
 输出：[1,2]
 
 示例 5：
 输入：root = [1,null,2]
 输出：[1,2]
 
 提示：
 树中节点数目在范围 [0, 100] 内
 -100 <= Node.val <= 100
 ```

刚看到题的时候，发现就是一个二叉树的前序遍历，众所周知，二叉树的前序遍历的规则为： 根->左->右 

so.... 我们按照题目的提示可以先来一个递归算法的（<font color=red>深度优先搜索算法DFS-> 本质就是递归</font>）：

```java
// 使用递归的算法进行计算设置
public static List<Integer> preorderTraversal(TreeNode root) {
    if(root == null) return null;
    List<Integer> res = new ArrayList<>();
    // 将当前递归的树进行叠加赋值
    res.add(root.val);
    // 开始递归 简略版的分治算法
    if(root.left != null) res.addAll(preorderTraversal(root.left));
    if(root.right != null) res.addAll(preorderTraversal(root.right));
    return res;
}
```

然后呢？题目提到了遍历的模式，所以我们肯定要挑战一下，下面是实现的代码（<font color=red>实现原理就是遵循二叉树的广度优先搜索算法 BFS，本质是找一个合适的数据结构来承载其中间值，进行遍历算法的延续</font>）：

其中主要的思路就是使用一个 栈 来存储我们的临时值，因为 前序遍历 主要的关注点出了根以外就是左边的元素，所以我们使用while循环(因为for循环在这里完全无法使用！)第一层用于开始这个二叉树的遍历，然后第二层我们主要关注于树的左元素的遍历。

在左元素的遍历中，我们主要的想法就是

1. 先把当前值放入结果数组中
2. 然后将当前遍历元素放入栈中(相当于保存下一次遍历的右子树)
3. 然后将临时二叉树的左元素重新赋值，重复1，结束的标志就是他的左元素为空。

上面为什么要使用二叉树来存储呢？因为二叉树的存储原理就是FILO(先进后出，后进先出)，方便我们存储初始的右分治，最后一次弹出可以很方便的进行右分支的遍历

后面的步骤就是：

1. 将栈中保存的元素进行弹出
2. 然后重新赋值给临时值，继续下一次循环，直到这个tmp为null 或者 栈中没有元素为止！

```java
// 迭代解决方案
public static List<Integer> preorderTraversal1(TreeNode root) {
    if(root == null) return null;
    List<Integer> res = new ArrayList<>();

    Deque<TreeNode> stack = new LinkedList<>();
    TreeNode tmp = root;
    while (!stack.isEmpty() || tmp != null) {
        while (tmp != null) {
            res.add(tmp.val);
            stack.push(tmp);
            tmp = tmp.left;
        }
        tmp = stack.pop();
        tmp = tmp.right;
    }
    return res;
}
```

