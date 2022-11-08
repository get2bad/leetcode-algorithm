package com.wills.leetcode.top100.tree_back_foreach;

import com.wills.leetcode.simple.common.TreeNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @ClassName TreeFrontForeach
 * @Date 2022/11/8 11:34
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class TreeBackForeach {

    private static final TreeNode source = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));
//    private static final TreeNode source = new TreeNode(1,new TreeNode(2, new TreeNode(3),null),null);

    @Test
    public void test() {
        System.out.println(backForeach(source));
    }

    /**
     * 左右根
     */
    public List<Integer> backForeach(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        generate(res, root);
        return res;
    }

    public void generate(List<Integer> res, TreeNode root) {
        if (root == null) {
            return;
        }
        generate(res, root.left);
        generate(res, root.right);
        res.add(root.val);
    }
}
