package com.wills.leetcode.tree.balance_tree;

import com.wills.leetcode.common.TreeNode;

/**
 * @author 王帅
 * @date 2021-02-26 09:31:50
 * @description:
 * 平衡二叉树
 */
public class BalanceTree {

    /**
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     * 本题中，一棵高度平衡二叉树定义为：
     * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
     *
     *
     *
     * 示例 1：
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：true
     *
     * 示例 2：
     * 输入：root = [1,2,2,3,3,null,null,4,4]
     * 输出：false
     *
     * 示例 3：
     * 输入：root = []
     * 输出：true
     */
    public static void main(String[] args) {
        // 测试用例 [1,2,3,null,null,3,null,null,5,null,null]
        TreeNode source = new TreeNode(1,new TreeNode(2,new TreeNode(3,null,null),new TreeNode(3,null,null)),new TreeNode(5,null,null));
        System.out.println(isBalanced(source));
    }

    /**
     * 因为是查看二叉树是不是盖度平衡的二叉树，
     * 涉及到二叉树的遍历 肯定就涉及到
     * DFS 深度搜索优先算法
     * BFS 广度搜索优先算法
     *
     * 基于此，我们可以使用 DFS 来探寻左右子树的深度(递归)
     *
     * // 为什么要加isBalanced(root.left) && isBalanced(root.right)？
     * 因为要遍历每一个根节点是否是高度相同的二叉树，不光要看这一整棵树是否高度平衡
     *
     * 本方法为 自顶向下递归遍历
     * 时间复杂度为: O(n^2)
     * 空间复杂度为： O(n)
     */
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

    /**
     * 自底向上的递归
     */
    public static boolean isBalanced1(TreeNode root) {
        return height(root) >= 0;
    }

    public static int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
}
