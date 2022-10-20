package com.wills.leetcode.top100.Q10_remove_linkedlist_n_node;

import com.wills.leetcode.simple.common.ListNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @ClassName RemoveLinkedListNNode
 * @Date 2022/10/20 09:51
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class RemoveLinkedListNNode {

    private static final ListNode source = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5)))));
    private static final Integer n = 2;



    @Test
    public void test(){
//        System.out.println(removeNthFromEnd(source, n));
//        System.out.println(removeNthFromEndByStack(source, n));
        System.out.println(removeNthFromEndBySlowFastPointer(source, n));
    }

    /**
     *  给你一个链表，删除链表的倒数第n个结点，并且返回链表的头结点。
     *
     * 示例 1：
     * 输入：head = [1,2,3,4,5], n = 2
     * 输出：[1,2,3,5]
     *
     * 示例 2：
     * 输入：head = [1], n = 1
     * 输出：[]
     *
     * 示例 3：
     * 输入：head = [1,2], n = 1
     * 输出：[1]
     *
     * 其中 栈 和 快慢指针 的方式都是使用临时变量 + 迭代到指定位置，然后到指定位置进行截断，达到目的
     */
    /**
     * 使用快慢指针的方式解决本题 (与下面栈的方式大致相同)
     * 理解图片地址：
     * https://assets.leetcode-cn.com/solution-static/19/p3.png
     */
    public ListNode removeNthFromEndBySlowFastPointer(ListNode head, int n) {
        ListNode res = new ListNode(0, head);
        // 快指针
        ListNode first = head;
        // 慢指针
        ListNode second = res;
        // 快指针先跑 n 个元素
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        // 让慢指针到达指定点
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        // 慢指针赋值
        second.next = second.next.next;
        return res.next;
    }


    /**
     * 使用栈的方式解决本题,因为栈的特性就是先入后出
     */
    public ListNode removeNthFromEndByStack(ListNode head, int n) {
        ListNode res = new ListNode(0, head);
        ListNode cur = res;
        Deque<ListNode> stack = new ArrayDeque<>();
        // 将所有的元素入栈
        while(cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        // 出栈 n 个元素，达到回溯的作用
        for (int i = 0; i < n; ++i) {
            stack.pop();
        }
        // 重新赋值,因为 stack 的第一个元素(peek) 就是目标截断的元素
        ListNode prev = stack.peek();
        prev.next = prev.next.next;
        return res.next;
    }

    /**
     * 计算长度法，基本思想：
     * 1. 计算链表长度
     * 2； 借助临时值进行迭代，在目标点进行截断重新赋值
     * 3. 返回
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode tail = new ListNode(0, head);
        int len = getLength(head);
        ListNode cur = tail;
        for (int i = 0; i < len - n + 1; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return tail.next;
    }

    private int getLength(ListNode head) {
        int res = 0;
        while(head.next != null){
            ++res;
            head = head.next;
        }
        return res;
    }
}
