package com.wills.leetcode.simple.common;

import lombok.Builder;
import lombok.Data;

/**
 * @author 王帅
 * @date 2021-02-19 09:42:55
 * @description:
 */
@Data
@Builder
public class TreeNode {

    public int val;

    public TreeNode left;

    public TreeNode right;

    public TreeNode() {}

    public TreeNode(int val) { this.val = val; }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
