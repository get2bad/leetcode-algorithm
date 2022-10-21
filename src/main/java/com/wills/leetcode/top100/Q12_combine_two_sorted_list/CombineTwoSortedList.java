package com.wills.leetcode.top100.Q12_combine_two_sorted_list;

import com.wills.leetcode.simple.common.ListNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName CombineTwoSortedList
 * @Date 2022/10/21 10:34
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class CombineTwoSortedList {

    private static final ListNode l1 = new ListNode(1,new ListNode(2,new ListNode(4)));
    private static final ListNode l2 = new ListNode(1,new ListNode(3,new ListNode(4)));

    @Test
    public void test(){
        System.out.println(mergeTwoLists(l1, l2));
    }

    /**
     *  将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     *
     * 示例 1：
     * 输入：l1 = [1,2,4], l2 = [1,3,4]
     * 输出：[1,1,2,3,4,4]
     *
     * 示例 2：
     * 输入：l1 = [], l2 = []
     * 输出：[]
     *
     * 示例 3：
     * 输入：l1 = [], l2 = [0]
     * 输出：[0]
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        List<Integer> source = new ArrayList<>();
        ListNode res = new ListNode(0), cur = res;
        while(list1 != null){
            source.add(list1.val);
            list1 = list1.next;
        }
        while(list2 != null){
            source.add(list2.val);
            list2 = list2.next;
        }
        Collections.sort(source);
        for (Integer data : source) {
            cur.next = new ListNode(data);
            cur = cur.next;
        }

        return res.next;
    }
}
