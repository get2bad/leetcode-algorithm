package com.wills.leetcode.waiting_to_be_sorted.linked_list.reverse_linked_list;

import com.wills.leetcode.common.ListNode;

/**
 * @author 王帅
 * @date 2021-03-25 10:58:22
 * @description:
 * 翻转链表
 */
public class ReverseLinkedList1 {

    /**
     * 反转一个单链表。
     *
     * 示例:
     *
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     * 进阶:
     * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
     */
    public static void main(String[] args) {
        ListNode source = new ListNode(1);
        source.next = new ListNode(2);
        source.next.next = new ListNode(3);
        source.next.next.next = new ListNode(4);
        source.next.next.next.next = new ListNode(5);
        System.out.println(reverseList1(source));
    }

    /**
     * 使用迭代
     * 1 -> 2 -> 3 -> 4 -> 5
     */
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            // 存储下一个结点
            ListNode next = curr.next;
            // 翻转链表复制给 下一个结点
            curr.next = prev;
            // 当前结点 赋值给 翻转链表
            prev = curr;
            // 下一个结点 供下一次遍历用
            curr = next;
        }
        return prev;
    }

    /**
     * 使用递归
     */
    public static ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
