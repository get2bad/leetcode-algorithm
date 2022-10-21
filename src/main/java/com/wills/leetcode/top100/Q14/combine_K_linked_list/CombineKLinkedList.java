package com.wills.leetcode.top100.Q14.combine_K_linked_list;

import com.wills.leetcode.simple.common.ListNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName CombineKLinkedList
 * @Date 2022/10/21 11:03
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class CombineKLinkedList {

    private static final ListNode l1 = new ListNode(1,new ListNode(4,new ListNode(5)));
    private static final ListNode l2 = new ListNode(1,new ListNode(3,new ListNode(4)));
    private static final ListNode l3 = new ListNode(2,new ListNode(6));

    @Test
    public void test(){
        System.out.println(mergeKLists(new ListNode[]{l1, l2, l3}));
    }

    /**
     *  给你一个链表数组，每个链表都已经按升序排列。
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     *
     * 示例 1：
     * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
     * 输出：[1,1,2,3,4,4,5,6]
     * 解释：链表数组如下：
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * 将它们合并到一个有序链表中得到。
     * 1->1->2->3->4->4->5->6
     *
     * 示例 2：
     * 输入：lists = []
     * 输出：[]
     *
     * 示例 3：
     * 输入：lists = [[]]
     * 输出：[]
     */
    public ListNode mergeKLists(ListNode[] lists) {
        List<Integer> source = new ArrayList<>();
        ListNode res = new ListNode(0), cur = res;
        for (ListNode node : lists) {
            while(node != null){
                source.add(node.val);
                node = node.next;
            }
        }
        Collections.sort(source);
        for (Integer data : source) {
            cur.next = new ListNode(data);
            cur = cur.next;
        }
        return res.next;
    }
}
