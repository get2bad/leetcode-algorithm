package com.wills.leetcode.tree.path_sum_tree;

import com.sun.source.tree.Tree;
import com.wills.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 王帅
 * @date 2021-03-01 10:17:07
 * @description:
 * 路径总和
 */
public class PathSum {

    /**
     * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，
     * 这条路径上所有节点值相加等于目标和 targetSum 。
     * 叶子节点 是指没有子节点的节点。
     *
     * 示例 1：
     * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
     * 输出：true
     *
     * 示例 2：
     * 输入：root = [1,2,3], targetSum = 5
     * 输出：false
     *
     * 示例 3：
     * 输入：root = [1,2], targetSum = 0
     * 输出：false
     */
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1,new TreeNode(2,new TreeNode(3,null,null),new TreeNode(3,null,null)),new TreeNode(5,null,null));
        System.out.println(hasPathSum(t1, 5));
    }

    /**
     * 既然要遍历二叉树肯定要设计 DFS BFS搜索算法
     * 本次 我们可以使用 BFS 广度优先搜索算法 使用队列的方式解决
     */
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> queVal = new LinkedList<>();

        queue.offer(root);
        queVal.offer(root.val);

        while(!queue.isEmpty()){
            TreeNode tmp = queue.poll();
            Integer tmpVal = queVal.poll();

            if(tmp.left == null && tmp.right == null){
                if(tmpVal == targetSum){
                    return true;
                }
                continue;
            }
            if(tmp.left != null){
                queue.offer(tmp.left);
                queVal.offer(tmp.left.val + tmpVal);
            }

            if(tmp.right != null){
                queue.offer(tmp.right);
                queVal.offer(tmp.right.val + tmpVal);
            }
        }

        return false;
    }

    /**
     * 本方法使用 DFS 深度优先搜索算法 (递归)
     */
    public static boolean hasPathSum1(TreeNode root, int targetSum) {
        if(root == null) return false;
        // 如果这个节点后面没有子节点了，说明到了末端，我们要进行判断
        if(root.left == null && root.right == null){
            return targetSum == root.val;
        }
        return hasPathSum1(root.left,targetSum - root.val)
                || hasPathSum1(root.right, targetSum - root.val);
    }

}
