# [N 叉树的前序遍历](https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/)

给定一个 n 叉树的根节点  `root` ，返回 *其节点值的 **前序遍历*** 。

n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 `null` 分隔（请参见示例）。


**示例 1：**

<img src="http://image.tinx.top/narytreeexample.png" alt="img" style="zoom:50%;" />

```
输入：root = [1,null,3,2,4,null,5,6]
输出：[1,3,5,6,2,4]
```

**示例 2：**

<img src="http://image.tinx.top/sample_4_964.png" alt="img" style="zoom:50%;" />

```
输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
输出：[1,2,3,6,7,11,14,4,8,12,5,9,13,10]
```



 起初拿到这道题，有点抓耳挠腮，以为传入的是一个 int数组，心里还在想，这咋办？如何处理？

然后发现，题目给的数据结构是个Node，包含val和```List<Node> children```，那么这就好办了，直接递归 + 暴力法搞定！

```java
List<Integer> res = new ArrayList<Integer>();

public List<Integer> preorder(Node root) {
    //递归版
    if (root == null) return res;
    res.add(root.val);
    for (Node child : root.children) {
        preorder(child);
    }

    return res;
}
```

