package com.wills.leetcode.top100.Q2_num_of_add;

import com.wills.leetcode.simple.common.ListNode;
import org.junit.Test;

/**
 * @ClassName AddTwoNumbers
 * @Date 2022/10/17 11:15
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class AddTwoNumbers {

    private static ListNode l1;
    private static ListNode l2;

    public AddTwoNumbers() {
        l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
    }

    @Test
    public void test() {
        System.out.println(addTwoNumbers(l1, l2));
    }

    /*
     * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 你可以假设除了数字 0 之外，这两个数都不会以 0开头
     *
     * 示例 1：
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * 解释：342 + 465 = 807.
     *
     * 示例 2：
     * 输入：l1 = [0], l2 = [0]
     * 输出：[0]
     *
     * 示例 3：
     * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
     * 输出：[8,9,9,9,0,0,0,1]
     *
     * 思路：
     * 声明一个 flag 表示递进位，然后依次遍历
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        // 临时遍历的变量
        ListNode curr = res;
        int flag = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;

            int sum = x + y + flag;
            flag = sum / 10;
            sum %= 10;
            curr.next = new ListNode(sum);

            curr = curr.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (flag == 1) curr.next = new ListNode(flag);

        return res.next;
    }
}
