package com.wills.leetcode.tree.front_foreach;

import com.wills.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class FrontForeach {

    public static void main(String[] args) {
        // 构建一颗二叉树
        TreeNode treeNode = new TreeNode(1,null,new TreeNode(2,new TreeNode(3),null));
        List<Integer> res = preorderTraversal1(treeNode);
        res.forEach(System.out::println);
    }

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

    // 迭代解决方案
    public static List<Integer> preorderTraversal1(TreeNode root) {
        if(root == null) return null;
        List<Integer> res = new ArrayList<>();

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode tmp = root;
       while(tmp != null || !stack.isEmpty()){
           // 遍历左分支
           while(tmp != null){
               res.add(tmp.val);
               stack.push(tmp);
               tmp = tmp.left;
           }
           tmp = stack.pop();
           tmp = tmp.right;
       }
        return res;
    }
}
