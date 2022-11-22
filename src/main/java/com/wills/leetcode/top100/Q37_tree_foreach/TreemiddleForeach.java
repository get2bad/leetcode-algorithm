package com.wills.leetcode.top100.Q37_tree_foreach;

import com.wills.leetcode.simple.common.TreeNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @ClassName TreemiddleForeach
 * @Date 2022/11/8 11:19
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class TreemiddleForeach {

//    private static final TreeNode source = new TreeNode(1,null,new TreeNode(2, new TreeNode(3),null));
    private static final TreeNode source = new TreeNode(1,new TreeNode(2, new TreeNode(3),null),null);

    @Test
    public void test(){
        System.out.println(inorderTraversal(source));
    }

    /**
     *  给定一个二叉树的根节点 root ，返回 它的 中序遍历
     *
     * 示例 1：
     * 输入：root = [1,null,2,3]
     * 输出：[1,3,2]
     *
     * 示例 2：
     * 输入：root = []
     * 输出：[]
     *
     * 示例 3：
     * 输入：root = [1]
     * 输出：[1]
     *
     * 中序遍历 左根右
     * 使用栈的方式来解决，步骤：
     * 1. 使用while循环，找到左侧所有的左分支
     * 2. 然后出栈一次，将val增加至返回的数组中
     * 3. 将当前的 root.right 赋值给 root，当作下一次循环用
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }

        return res;
    }
}
