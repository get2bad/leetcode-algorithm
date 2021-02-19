package com.wills.leetcode;

import com.wills.leetcode.common.ListNode;

/**
 * 删除排序链表中的重复元素
 *
 *  给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 *
 * 示例1:
 * 输入: 1->1->2
 * 输出: 1->2
 *
 * 示例2:
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 */
public class RemoveDuplicatesLinkedList {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1,new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(3,null)))));
        System.out.println(deleteDuplicates1(listNode));
    }

    /**
     * 思路：
     * 使用递归的方式解决
     * 时间复杂度： O(n) 空间复杂度 O(1)
     * 该题重点：
     * 循环遍历这个链表，返回的链表状态保存问题 res.next 保存给谁，下一次赋值的时候 res.next还是第二个的问题
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        head.next = deleteDuplicates(head.next);
        if(head.val == head.next.val) head = head.next;
        return head;
    }

    /**
     * 创建一个新的链表，内存地址是head的内存地址(两个是同一份)
     * 然后进行遍历这个新的链表
     * 循环体内的内容是：
     *  1. 如果当前遍历的值等于下一个遍历的值的话 就直接跳过这个值，进行下一次比较
     *  2. 如果当前的遍历的值不等于下一个遍历的值的话，那么就将当前遍历的值重新赋值为下一个要遍历的值，进行下一次判断
     * 时间复杂度 O(n) 空间复杂度： O(1)
     */
    public static ListNode deleteDuplicates1(ListNode head) {
        ListNode tmp = head;
        while(tmp != null && tmp.next != null){
            if(tmp.val == tmp.next.val){
                // 为什么不是 tmp = tmp.next.next? 因为这样什么都没有改变
                tmp.next = tmp.next.next;
            } else {
                tmp = tmp.next;
            }
        }
        return head;
    }
}
