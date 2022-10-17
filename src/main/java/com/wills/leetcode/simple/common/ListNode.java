package com.wills.leetcode.simple.common;

import lombok.Builder;
import lombok.Data;

/**
 * @author 王帅
 * @date 2021-02-19 09:37:16
 * @description:
 */
@Data
@Builder
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
