package com.wills.leetcode.remove_duplicates_linked_list;

import com.wills.leetcode.common.ListNode;

/**
 * @author 王帅
 * @date 2021-03-25 09:51:27
 * @description:
 * 删除排序链表的重复元素2
 */
public class RemoveDuplicatesLinkedList2 {
    /**
     * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，
     * 只保留原始链表中 没有重复出现 的数字。
     * 返回同样按升序排列的结果链表。
     *
     * 示例 1：
     * 输入：head = [1,2,3,3,4,4,5]
     * 输出：[1,2,5]
     *
     * 示例 2：
     * 输入：head = [1,1,1,2,3]
     * 输出：[2,3]
     */
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1,new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(3,null)))));
        System.out.println(deleteDuplicates(listNode));
    }

    public static ListNode deleteDuplicates(ListNode head) {
        ListNode res = new ListNode( -1);
        ListNode tmp = res;
        while(head != null){
            if(head.next == null || head.val != head.next.val){
                // 跳过相同的节点
                tmp.next = head;
                // 然后将 尾结点替换到 tmp.next
                tmp = tmp.next;
            }
            // 如果当前值 和下一个值相等，就持续向下遍历
            while(head.next !=null && head.val == head.next.val){
                head = head.next;
            }
            // 往下继续遍历
            head = head.next;
        }
        // 截断后面的尾部
        tmp.next = null;

        return res.next;
    }
}
