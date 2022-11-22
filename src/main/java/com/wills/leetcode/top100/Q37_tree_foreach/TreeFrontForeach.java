package com.wills.leetcode.top100.Q37_tree_foreach;

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
public class TreeFrontForeach {

    private static final TreeNode source = new TreeNode(1,null,new TreeNode(2, new TreeNode(3),null));
//    private static final TreeNode source = new TreeNode(1,new TreeNode(2, new TreeNode(3),null),null);

    @Test
    public void test(){
        System.out.println(frontForeach(source));
    }

    /**
     * 根左右
     */
    public List<Integer> frontForeach(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        while(root != null || !stack.isEmpty()){
            while(root != null){
                res.add(root.val);
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }

        return res;
    }
}
