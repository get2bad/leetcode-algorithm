# 将有序数组转换为二叉搜索树

> 给你一个整数数组 `nums` ，其中元素已经按 **升序** 排列，请你将其转换为一棵 **高度平衡** 二叉搜索树。
>
> **高度平衡** 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
>
>  
>
> **示例 1：**
>
> ![img](https://assets.leetcode.com/uploads/2021/02/18/btree1.jpg)
>
> ```
> 输入：nums = [-10,-3,0,5,9]
> 输出：[0,-3,9,-10,null,5]
> 解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
> ```
>
> **示例 2：**
>
> ![img](https://assets.leetcode.com/uploads/2021/02/18/btree.jpg)
>
> ```
> 输入：nums = [1,3]
> 输出：[3,1]
> 解释：[1,3] 和 [3,1] 都是高度平衡二叉搜索树。
> ```

刚读这个问题，我是真的搞不懂 「**高度平衡** 」「每个节点的左右两个子树的高度差的绝对值不超过 1」这个概念到底是什么意思，然后经过面向百度编程知道了就是二叉树两边的分支的高度相同 而已

思路：

因为给我们的是一个有序的数组，还得保证是一棵高度平衡的二叉树

1. 所以我们应该将数组对半分开，这样两边的子树就一定可以保证是 高度平衡的
2. 在脑海中搜索相关算法，对于数组我们可以使用二分查找法(分治法)  + 递归 法 解决本题

```java
/**
 * 思路：
 * 「每个节点的左右两个子树的高度差的绝对值不超过 1 」
 *  意思就是 数组中相邻的两个元素的在二叉树的高度差不会超过 1
 *  注意： ** 转换后的二叉树是一棵 高度平衡 的二叉搜索树 **
 *
 *  二叉搜索树的中序遍历是升序序列，题目给定的数组是按照升序排序的有序数组，
 *  因此可以确保数组是二叉搜索树的中序遍历序列。
 *
 *
 *  区间分治 问题 其实就是一个二分查询的问题，因为是有序的，所以从二分后分为左子树 右子树
 *  必定是平衡的
 */
public static TreeNode sortedArrayToBST(int[] nums) {
    return resolve(nums,0,nums.length - 1);
}

/**
 * @param nums 二分后的数组
 * @param left 左指针
 * @param right 右指针
 *
 * 时间复杂度：O(n)
 */
public static TreeNode resolve(int[] nums,int left, int right){
    if(left > right){
        return null;
    }

    int mid = (left + right) / 2;

    TreeNode root = new TreeNode(nums[mid]);
    root.left = resolve(nums, left, mid - 1);
    root.right = resolve(nums, mid + 1, right);
    return root;
}
```