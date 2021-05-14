package com.wills.leetcode.tree.same_tree;


import com.wills.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 王帅
 * @date 2021-02-19 09:23:53
 * @description:
 *  相同的树
 */
public class SameTree {

    /**
     * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
     *
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     */
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1,new TreeNode(2,new TreeNode(3,null,null),new TreeNode(3,null,null)),new TreeNode(5,null,null));
        TreeNode t2 = new TreeNode(1,new TreeNode(2,new TreeNode(3,null,null),new TreeNode(3,null,null)),new TreeNode(5,null,null));
        System.out.println(isSameTree1(t1, t2));
    }

    /**
     * 本方法涉及到二叉树遍历问题：
     * 本方法使用深度优先搜索算法
     * 时间复杂度：O(min(m,n))
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if(p== null && q == null){
            // 递归终点，如果都为null 那么两个树形结构都相同
            return true;
        }else if(p ==null || q == null){
            // 如果 两个有一个为null 有一个不为null，那么说明两个不相等，就返回false
            return false;
        } else if(p.val != q.val){
            // 如果两个值不相等，那么就是真的不相等
            return false;
        } else {
            return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
        }
    }

    /**
     * 广度优先搜索算法
     * 我们使用两个队列分别存储两个树节点，然后进行出队列比较
     * 时间复杂度：O(min(m,n))
     */
    public static boolean isSameTree1(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;

        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();

        queue1.offer(p);
        queue2.offer(q);
        while(!queue1.isEmpty() && !queue2.isEmpty()){
            // 进行判断
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            if(node1.val != node2.val){
                return false;
            }
            TreeNode left1 = node1.left,right1 = node1.right;
            TreeNode left2 = node2.left,right2 = node2.right;
            /**
             * 位异或运算（^）
             * 运算规则是：两个数转为二进制，然后从高位开始比较，如果相同则为0，不相同则为1。
             */
            if(left1== null ^ left2 == null){
                return false;
            }
            if(right1 == null ^ right2 == null){
                return false;
            }
            if(left1 != null){
                queue1.offer(left1);
            }
            if(right1 != null){
                queue1.offer(right1);
            }
            if(left2 != null){
                queue2.offer(left2);
            }
            if(right2 != null){
                queue2.offer(right2);
            }
        }
        return queue1.isEmpty() && queue2.isEmpty();
    }
}
