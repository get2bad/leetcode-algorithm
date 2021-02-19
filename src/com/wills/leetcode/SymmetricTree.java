package com.wills.leetcode;

import com.wills.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 王帅
 * @date 2021-02-19 11:05:46
 * @description:
 * 对称二叉树
 */
public class SymmetricTree {

    /**
     * 给定一个二叉树，检查它是否是镜像对称的。
     *
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     *
     *     1
     *    / \
     *   2   2
     *  / \ / \
     * 3  4 4  3
     *
     * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
     *
     *     1
     *    / \
     *   2   2
     *    \   \
     *    3    3
     */
    public static void main(String[] args) {

    }

    /**
     * 对于二叉树的查找，几乎都是基于 DFS BFS 搜索算法
     * 对于本题 "二叉树是否对称"的问题貌似就不再适用了
     * 所以 可以使用以下两种方式来解决：
     * 1. 递归 时间复杂度为 O(n)
     * 2. 迭代 时间复杂度为 O(n)
     *
     * 对于此题： 递归的点怎么找？思路如下：
     *
     * 1.怎么判断一棵树是不是对称二叉树？
     * 答案：如果所给根节点，为空，那么是对称。如果不为空的话，当他的左子树与右子树对称时，他对称
     *
     * 2.那么怎么知道左子树与右子树对不对称呢？在这我直接叫为左树和右树
     * 答案：如果左树的左孩子与右树的右孩子对称，左树的右孩子与右树的左孩子对称，那么这个左树和右树就对称。
     */
    public static boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        // 进行递归调用：
        return checkIfEqual(root,root);
    }

    public static boolean checkIfEqual(TreeNode left,TreeNode right){
        if(left == null && right == null) return true;

        if(left == null || right == null) return false;

        if(left.val != right.val) return false;

        return checkIfEqual(left.left,right.right) && checkIfEqual(left.right,right.left);
    }

    /**
     * 迭代：
     * 继续使用 上一题的判断两个二叉树是否相同的思路来解决
     * 使用一个 队列，分别将队列进行 offer poll 然后判断两个值是否不相等，如果不相等 就返回false
     * 时间复杂度为 O(n)
     */
    public static boolean isSymmetric1(TreeNode root) {
        if(root == null) return true;
        return checkIfEqualByQueue(root,root);
    }

    public static boolean checkIfEqualByQueue(TreeNode left,TreeNode right){
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(left);
        queue.offer(right);
        while (!queue.isEmpty()) {
            left = queue.poll();
            right = queue.poll();
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null) {
                return false;
            }

            if(left.val != right.val){
                return false;
            }

            queue.offer(left.left);
            queue.offer(right.right);

            queue.offer(left.right);
            queue.offer(right.left);
        }
        return true;
    }

}
