package com.wills.leetcode.waiting_to_sorted.linked_list.reverse_linked_list;

import com.wills.leetcode.common.ListNode;

import java.util.Stack;

/**
 * @author 王帅
 * @date 2021-03-18 10:41:00
 * @description:
 * 反转链表 II
 */
public class ReverseLinkedList {

    /**
     * 给你单链表的头节点 head 和两个整数 left 和 right ，其中 left <= right 。
     * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
     *
     *
     * 示例 1：
     *
     *
     * 输入：head = [1,2,3,4,5], left = 2, right = 4
     * 输出：[1,4,3,2,5]
     * 示例 2：
     *
     * 输入：head = [5], left = 1, right = 1
     * 输出：[5]
     */
    public static void main(String[] args) {
        ListNode source = new ListNode(1);
        source.next = new ListNode(2);
        source.next.next = new ListNode(3);
        source.next.next.next = new ListNode(4);
        source.next.next.next.next = new ListNode(5);
        System.out.println(reverseBetween(source,2,4));
    }

    /**
     * 使用笨方法,将一个链表切分为 要目的截取之前的链表，要翻转的链表，截取之后的链表，
     * 然后将 翻转的链表使用通用方法去翻转，然后依次拼接
     * 时间复杂度： O(n)
     * 空间复杂度： O(1)
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        // 因为头节点有可能发生变化，使用虚拟头节点可以避免复杂的分类讨论
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode pre = dummyNode;
        // 第 1 步：从虚拟头节点走 left - 1 步，来到 left 节点的前一个节点
        // 建议写在 for 循环里，语义清晰
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        // 第 2 步：从 pre 再走 right - left + 1 步，来到 right 节点
        ListNode rightNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }

        // 第 3 步：切断出一个子链表（截取链表）
        ListNode leftNode = pre.next;
        ListNode curr = rightNode.next;

        // 注意：切断链接
        pre.next = null;
        rightNode.next = null;

        // 第 4 步：同第 206 题，反转链表的子区间
        reverseLinkedList(leftNode);

        // 第 5 步：接回到原来的链表中
        pre.next = rightNode;
        leftNode.next = curr;
        return dummyNode.next;
    }

    /**
     * 翻转方法
     */
    private static void reverseLinkedList(ListNode head) {
        // 也可以使用递归反转一个链表
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
    }

    /**
     * 上面的问题的简化版
     * 思路就是：
     * 用一个变量存储 要翻转的目标链表的前一个地址
     * 然后持续使用 for循环 ，循环条件是 (right - left) 然后将在结束前调用方法去翻转，最后返回
     */
    public ListNode reverseBetween1(ListNode head, int left, int right) {
        // 设置 dummyNode 是这一类问题的一般做法
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode next;
        for (int i = 0; i < right - left; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummyNode.next;
    }

    /**
     * 使用栈的方式去解决
     * 使用栈 (先进后出 FILO)来翻转我们要翻转的链表，然后出栈就是翻转好了的链表了
     * 本解决方案的难点就是：
     * 使用变量去存储翻转链表前的变量 以及翻转链表后的变量,其他就是基本的数据结构操作
     */
    public static ListNode reverseBetween2(ListNode head, int left, int right) {
        if (left == right || head == null)
            return head;
        int count = 1;
        ListNode start = head;
        ListNode end = null;
        ListNode start0 = head;
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            if (count < left)
                start = head;
            else {
                if (count <= right)
                    stack.push(head);
                else {
                    end = head;
                    break;
                }
            }

            head = head.next;
            count++;
        }
        ListNode start1 = stack.peek();
        ListNode cur = stack.pop();
        while (!stack.isEmpty()) {
            cur.next = stack.peek();
            cur = stack.pop();
        }
        cur.next = end;
        if (left == 1)
            return start1;
        start.next = start1;
        return start0;
    }
}
