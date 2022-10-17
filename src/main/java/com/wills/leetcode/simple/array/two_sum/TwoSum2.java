package com.wills.leetcode.simple.array.two_sum;

import com.wills.leetcode.simple.common.ListNode;
import org.junit.Test;

/**
 * @ClassName TwoSum2
 * @Date 2022/9/27 16:21
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class TwoSum2 {

    private static final ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
    private static final ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));

    @Test
    public void test() {
        System.out.println(addTwoNumbers(l1, l2));
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0), cur = pre;
        // 进位
        int carry = 0;
        // 遍历这两个ListNode
        while(l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;

            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);

            cur = cur.next;
            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }
        if(carry == 1) cur.next = new ListNode(carry);

        return pre.next;
    }
}
