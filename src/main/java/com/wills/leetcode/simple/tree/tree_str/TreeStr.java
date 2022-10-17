package com.wills.leetcode.simple.tree.tree_str;

import com.wills.leetcode.simple.common.TreeNode;

import java.util.*;

/**
 * @author 王帅
 * @date 2022-03-19 18:53:56
 * @description:
 */
public class TreeStr {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,new TreeNode(2,new TreeNode(3,null,null),new TreeNode(3,null,null)),new TreeNode(5,null,null));
        TreeStr obj = new TreeStr();
        String res = obj.tree2str1(root);
        System.out.println(res);
    }

    /**
     * 你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。
     *
     * 空节点则用一对空括号 "()" 表示。而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。
     *
     * 我们知道 遇到二叉树时，肯定躲不开要使用 DFS（深度优先搜索遍历）/BFS（广度优先搜索遍历），
     * 在本题我们使用 DFS 来解决本题。
     */
    StringBuilder sb = new StringBuilder();
    public String tree2str(TreeNode root) {
        if(root == null) return "";
        dfs(root);
        // 根据题目要求要去掉左右的括号
        return sb.substring(1,sb.length() - 1);
    }

    private void dfs(TreeNode node){
        sb.append("(");

        // 结点值放到字符串中
        sb.append(node.val);
        // 如果是左节点，那么递归这个方法
        if(node.left != null) dfs(node.left);
        // 这边考虑到题目要求的 如果是 左节点为Null，右节点不为Null的情况下，就要将左节点显示为 ()
        else if (node.right != null) sb.append("()");
        // 如果是右节点，那么直接跳过这个结点，因为题目要求
        if(node.right != null) dfs(node.right);

        sb.append(")");
    }

    //  BFS 非递归的方式,使用 栈 / 队列的方式构建
    public String tree2str1(TreeNode root) {
        if(root == null) return null;
        StringBuilder sb = new StringBuilder();
        Set<TreeNode> endSet = new HashSet<>();
        Deque<TreeNode> source = new ArrayDeque<>();
        source.addLast(root);
        while (!source.isEmpty()) {
            TreeNode lastAddtion = source.pollLast();
            // 判断结束,判断如果是包含，就叠加结束符 )
            if (endSet.contains(lastAddtion)) {
                sb.append(")");
            } else {
                // 为什么要将之前 poll出来的放回去？
                // 因为我们要使用之前的来判断 Sets中是否有这个 有的话叠加结束的括号 )
                source.addLast(lastAddtion);
                sb.append("(");
                sb.append(lastAddtion.val);
                // 为什么要先将 right放进去？ 因为我们每次都是 pollLast，相当于使用了队列来实现算法
                if (lastAddtion.right != null) source.addLast(lastAddtion.right);
                if (lastAddtion.left != null) source.addLast(lastAddtion.left);
                // 这边要判断 left 为空，但是right不为空的情况，因为题目条件中规定了这个情况下，左的要赋值 ()
                else if (lastAddtion.right != null) sb.append("()");
                endSet.add(lastAddtion);
            }
        }
        return sb.substring(1,sb.length() -1);
    }
}
