package com.wills.leetcode.tree.middle_foreach;

import com.wills.leetcode.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @ClassName MiddleForeach
 * @Date 2022/3/30 09:17
 * @Author 王帅
 * @Version 1.0
 * @Description 二叉树的中序遍历
 */
public class MiddleForeach {

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1, new TreeNode(2, new TreeNode(3, null, null), new TreeNode(3, null, null)), new TreeNode(5, null, null));
        System.out.println(new MiddleForeach().inorderTraversal1(t1));
    }

    public List<Integer> inorderTraversalByMorris(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        TreeNode predecessor = null;

        while (root != null) {
            if (root.left != null) {
                // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止，然后跟左边建立一个循环的队列
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }

                // 让 predecessor 的右指针指向 root，继续遍历左子树
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                } else {
                    // 说明左子树已经访问完了，我们需要断开链接
                    res.add(root.val);
                    predecessor.right = null;
                    root = root.right;
                }
            } else {
                // 如果没有左孩子，则直接访问右孩子
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }

    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            // 先开始左边 添加顺序为 根 -> 左 -> 左 -> 左
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            // 然后pop出来从最左 -> 根 -> 右
            root = stack.pop();
            res.add(root.val);
            // 遍历右元素
            root = root.right;
        }
        return res;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        addAll(root, res);
        return res;
    }

    public void addAll(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        // 中序遍历是 左根右，所以要按照这个顺序来
        addAll(root.left, res);
        res.add(root.val);
        addAll(root.right, res);
    }
}
