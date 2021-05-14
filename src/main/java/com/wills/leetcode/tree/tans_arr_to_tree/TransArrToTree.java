package com.wills.leetcode.tree.tans_arr_to_tree;

import com.wills.leetcode.common.TreeNode;

/**
 * @author 王帅
 * @date 2021-02-23 09:32:41
 * @description:
 *
 * 将有序数组转换为二叉搜索树
 */
public class TransArrToTree {

    /**
     * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
     * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
     * 示例 1：
     * 输入：nums = [-10,-3,0,5,9]
     * 输出：[0,-3,9,-10,null,5]
     * 解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
     *
     * 示例 2：
     * 输入：nums = [1,3]
     * 输出：[3,1]
     * 解释：[1,3] 和 [3,1] 都是高度平衡二叉搜索树。
     */
    public static void main(String[] args) {
        int[] source = {-10,-3,0,5,9};
        System.out.println(sortedArrayToBST(source));
    }

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
}
