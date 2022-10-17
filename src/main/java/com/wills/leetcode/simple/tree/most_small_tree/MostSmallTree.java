package com.wills.leetcode.simple.tree.most_small_tree;

import com.wills.leetcode.simple.common.TreeNode;

/**
 * @author 王帅
 * @date 2021-02-27 14:47:12
 * @description:
 * 二叉树最小深度
 */
public class MostSmallTree {

    /**
     * 给定一个二叉树，找出其最小深度。
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     * 说明：叶子节点是指没有子节点的节点。
     *
     * 示例 1：
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：2
     *
     * 示例 2：
     * 输入：root = [2,null,3,null,4,null,5,null,6]
     * 输出：5
     */
    public static void main(String[] args) {
        // 测试用例 [1,2,3,null,null,3,null,null,5,null,null]
        TreeNode source = new TreeNode(1,new TreeNode(2,new TreeNode(3,null,null),new TreeNode(3,null,null)),new TreeNode(5,null,null));
        System.out.println(minDepth(source));
    }

    /**
     * 本地直接使用 深度搜索算法 (递归算法)即可解决
     */
    public static int minDepth(TreeNode root) {
        if (root == null) return 0;
        else if (root.left == null) return minDepth(root.right) + 1;
        else if (root.right == null) return minDepth(root.left) + 1;
        else return Math.min(minDepth(root.left), minDepth(root.right)) + 1;

    }
}
