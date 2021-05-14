package com.wills.leetcode.tree.deep_tree;

import com.wills.leetcode.common.TreeNode;

/**
 * @author 王帅
 * @date 2021-02-22 09:55:56
 * @description:
 * 二叉树的最大深度
 */
public class DeepTree {

    /**
     * 给定一个二叉树，找出其最大深度。
     *
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回它的最大深度 3
     */
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1,new TreeNode(2,new TreeNode(3,null,null),new TreeNode(3,null,null)),new TreeNode(5,null,null));
        System.out.println(maxDepth(t1));
    }

    /**
     * 因为本题的内容是 求出二叉树的最大深度，那么可以直接套用 DFS 深度优先搜索算法
     *
     * 深度优先算法 就是直接使用 递归算法 进行解出
     */
    public static int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        } else{
            return Math.max(maxDepth(root.left) , maxDepth(root.right)) + 1;
        }
    }
}
