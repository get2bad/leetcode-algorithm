package com.wills.leetcode.tree.nray_tree_front_foreach;

import com.wills.leetcode.common.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName NRayTreeForeach
 * @Date 2022/3/10 11:41
 * @Author 王帅
 * @Version 1.0
 * @Description N叉树的前序遍历
 */
public class NRayTreeForeach {

    public static void main(String[] args) {
    }

    /**
     * 给定一个 n 叉树的根节点  root ，返回 其节点值的 前序遍历 。
     * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
     * <p>
     * 示例 1：
     * 输入：root = [1,null,3,2,4,null,5,6]
     * 输出：[1,3,5,6,2,4]
     * <p>
     * 示例 2：
     * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
     * 输出：[1,2,3,6,7,11,14,4,8,12,5,9,13,10]
     */
    List<Integer> res = new ArrayList<Integer>();

    public List<Integer> preorder(Node root) {
        //递归版
        if (root == null) return res;
        res.add(root.val);
        for (Node child : root.children) {
            preorder(child);
        }

        return res;
    }
}
