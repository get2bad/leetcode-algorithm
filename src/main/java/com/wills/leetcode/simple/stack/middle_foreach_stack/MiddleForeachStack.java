package com.wills.leetcode.simple.stack.middle_foreach_stack;

import com.wills.leetcode.simple.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @ClassName MiddleForeachStack
 * @Date 2022/1/24 10:17
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class MiddleForeachStack {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));
        MiddleForeachStack stack = new MiddleForeachStack();
        List<Integer> res = stack.inorderTraversal(node);
        res.forEach(System.out::println);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode tmp = root;
        while(tmp != null || !stack.isEmpty()){
            // 中序遍历 是 左 中 右
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