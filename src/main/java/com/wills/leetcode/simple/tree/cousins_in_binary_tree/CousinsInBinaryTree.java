package com.wills.leetcode.simple.tree.cousins_in_binary_tree;

import com.wills.leetcode.simple.common.TreeNode;

public class CousinsInBinaryTree {

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1,new TreeNode(2,new TreeNode(3,null,null),new TreeNode(3,null,null)),new TreeNode(5,null,null));
        System.out.println(isCousins(t1,1, 5));
    }

    public static boolean isCousins(TreeNode root, int x, int y) {
        return getDeepth(root,x,0) == getDeepth(root,y,0) && isFatherNode(root, x, y);
    }

    // 获取是否为同一父节点
    public static boolean isFatherNode(TreeNode tree,int x,int y){
        if(tree != null){
            if(tree.left != null && tree.right != null){
                // 判断如果是同一个父节点，那么就直接返回false
                if((tree.left.val == x && tree.right.val == y) || (tree.left.val == y && tree.right.val == x)){
                    return false;
                }
            }
            return isFatherNode(tree.left,x,y) && isFatherNode(tree.right,x,y);
        }
        return true;
    }

    // 获取深度
    private static int getDeepth(TreeNode tree, int target,int deepth){
        if(tree != null){
            if(tree.val == target) return deepth;
            int l = getDeepth(tree.left,target,deepth + 1);
            int r = getDeepth(tree.right,target,deepth + 1);
            // 如果左边没有事-1就返回右边的r，否则就返回左子树的深度
            return l == -1? r: l;
        }
        return -1;
    }
}
