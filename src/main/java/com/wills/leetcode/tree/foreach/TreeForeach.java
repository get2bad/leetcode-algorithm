package com.wills.leetcode.tree.foreach;

import com.wills.leetcode.common.TreeNode;
import org.junit.Test;

import java.util.*;

/**
 * @ClassName Main
 * @Date 2022/4/24 11:08
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class TreeForeach {

    private static TreeNode treeNode = null;

    static {
        treeNode = TreeNode.builder()
                .val(1)
                .left(new TreeNode(2,new TreeNode(4,new TreeNode(5),new TreeNode(6)),null))
                .right(new TreeNode(3,new TreeNode(7),new TreeNode(8,new TreeNode(9),new TreeNode(10))))
                .build();
    }

    @Test
    public void frontForeach(){
        System.out.println("==================前序=============================");
        System.out.println(Arrays.toString(frontByRecursion(treeNode).toArray()));
        System.out.println(Arrays.toString(frontByFor(treeNode).toArray()));
    }

    // 根左右
    public List<Integer> frontByRecursion(TreeNode tree){
        if(tree == null) return null;
        List<Integer> res = new ArrayList<>();
        res.add(tree.val);
        if(tree.left != null) res.addAll(frontByRecursion(tree.left));
        if(tree.right != null) res.addAll(frontByRecursion(tree.right));
        return res;
    }

    // 根左右
    public List<Integer> frontByFor(TreeNode tree){
        if(tree == null) return null;
        List<Integer> res = new ArrayList<>();
        // 使用stack来存储
        Stack<TreeNode> stack = new Stack<>();
        stack.push(tree);
        while(!stack.isEmpty()){
            tree = stack.pop();
            res.add(tree.val);
            if(tree.right != null){
                stack.push(tree.right);
            }

            if(tree.left != null){
                stack.push(tree.left);
            }
        }

        return res;
    }

    @Test
    public void middleForeach(){
        System.out.println("==================中序=============================");
        System.out.println(Arrays.toString(middleByRecursion(treeNode).toArray()));
        System.out.println(Arrays.toString(middleByFor(treeNode).toArray()));
    }

    // 左根右
    public List<Integer> middleByRecursion(TreeNode tree){
        if(tree == null) return null;
        List<Integer> res = new ArrayList<>();
        if(tree.left != null) res.addAll(middleByRecursion(tree.left));
        res.add(tree.val);
        if(tree.right != null) res.addAll(middleByRecursion(tree.right));
        return res;
    }

    // 左根右
    public List<Integer> middleByFor(TreeNode tree){
        if(tree == null) return null;
        List<Integer> res = new ArrayList<>();
        // 使用stack来存储
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = tree;
        while(node != null || !stack.isEmpty()){
            while(node != null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            res.add(node.val);
            node = node.right;
        }
        return res;
    }

    @Test
    public void backForeach(){
        System.out.println("==================后序=============================");
        System.out.println(Arrays.toString(backByRecursion(treeNode).toArray()));
        System.out.println(Arrays.toString(backByFor(treeNode).toArray()));
    }

    List <Integer> res = new ArrayList<>();
    public List<Integer> backByRecursion(TreeNode root) {
        if (root == null) {
            return res;
        }
        backByRecursion(root.left);
        backByRecursion(root.right);
        res.add(root.val);
        return res;
    }

    /**
     * 将遍历的结果保存在List中，利用栈先进后出的特点，引入 node 和 prev 节点，其中 node 为当前正在访问的节点，
     * prev 是最近一次访问结束的节点，先一路向左走，将 node 压入栈，走到根节点时不能直接访问，
     * 判断右节点为空，或右节点已经访问过，再访问根节点，当右节点不为空，接着向右走
     */
    public List<Integer> backByFor(TreeNode tree){
        List<Integer> res = new ArrayList<>();
        if (tree == null) {
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode prev = null;
        while(tree != null || !stack.isEmpty()){
            while(tree != null){
                stack.push(tree);
                tree = tree.left;
            }
            tree = stack.pop();
            // 判断 如果 tree.right == null || prev == tree.right 说明可能为空 或者 已经被访问过
            if(tree.right == null || prev == tree.right){
                res.add(tree.val);
                prev = tree;
                tree = null;
            }else{
                stack.push(tree);
                tree = tree.right;
            }
        }
        return res;
    }

}
