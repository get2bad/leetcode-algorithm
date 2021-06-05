package com.wills.leetcode.linked_list.remove_linked_list;

import com.wills.leetcode.common.ListNode;

/**
 * @author 王帅
 * @ClassName RemoveLinkedList
 * @date 2021/6/5 09:24
 * @Version 1.0
 * @Description
 */
public class RemoveLinkedList {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1,new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(3,null)))));
        System.out.println(removeElements(listNode,1));
    }

    // 使用递归来解决
    public static ListNode removeElements(ListNode head, int val) {
        // 递归终结表达式
        if(head == null ){
            return head;
        }
        head.next = removeElements(head.next,val);
        if(head.val == val){
            return head.next;
        }
        return head;
    }

    // 迭代
    public static ListNode removeElements1(ListNode head, int val) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null) {
            if (temp.next.val == val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return dummyHead.next;
    }
}
