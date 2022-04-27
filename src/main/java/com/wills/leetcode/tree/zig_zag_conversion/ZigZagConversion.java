package com.wills.leetcode.tree.zig_zag_conversion;

import com.wills.leetcode.common.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName ZigZagConversion
 * @Date 2022/4/27 11:21
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class ZigZagConversion {


    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.builder()
                .val(1)
                .left(new TreeNode(2,new TreeNode(4,new TreeNode(5),new TreeNode(6)),null))
                .right(new TreeNode(3,new TreeNode(7),new TreeNode(8,new TreeNode(9),new TreeNode(10))))
                .build();
        System.out.println(zigzagLevelOrder(treeNode));
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }

        // 遍历标志，0表示从左到右，1表示从右到左
        int flag = 0;
        // 记录每一层的元素
        List<Integer> lay = new LinkedList<>();
        // 双向队列，当作栈来使用，记录当前层待处理结点
        Deque<TreeNode> stack = new LinkedList<>();
        // 记录下一层待处理结点
        Deque<TreeNode> nextStack = new LinkedList<>();

        stack.add(root);

        TreeNode node;
        while (!stack.isEmpty()) {
            // 删除栈顶元素
            node = stack.removeLast();

            // 结果入队
            lay.add(node.val);

            // 如果当前是从左到右遍历，按左子树右子树的顺序添加
            if (flag == 0) {
                if (node.left != null) {
                    nextStack.addLast(node.left);
                }

                if (node.right != null) {
                    nextStack.addLast(node.right);
                }
            }
            // 如果当前是从右到左遍历，按右子树左子树的顺序添加
            else {
                if (node.right != null) {
                    nextStack.addLast(node.right);
                }

                if (node.left != null) {
                    nextStack.addLast(node.left);
                }
            }

            // 当前层已经处理完了
            if (stack.isEmpty()) {
                Deque<TreeNode> temp = nextStack;
                nextStack = stack;  //下一个栈清空
                stack = temp;//本栈置为下一个栈

                // 标记下一层处理的方向
                flag = 1 - flag;
                // 保存本层结果
                result.add(lay);
                // 创建新的链表处理下一层的结果
                lay = new LinkedList<>();
            }
        }

        return result;
    }

}
