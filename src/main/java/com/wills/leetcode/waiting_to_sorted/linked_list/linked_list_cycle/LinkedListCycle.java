package com.wills.leetcode.waiting_to_sorted.linked_list.linked_list_cycle;

import com.wills.leetcode.common.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 王帅
 * @date 2021-03-10 09:59:34
 * @description:
 * 环形链表
 */
public class LinkedListCycle {

    /**
     * 给定一个链表，判断链表中是否有环。
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     *
     * 如果链表中存在环，则返回 true 。 否则，返回 false 。
     *
     * 进阶：
     * 你能用 O(1)（即，常量）内存解决此问题吗？
     *
     *
     *
     * 示例 1：
     * 输入：head = [3,2,0,-4], pos = 1
     * 输出：true
     * 解释：链表中有一个环，其尾部连接到第二个节点。
     *
     * 示例 2：
     * 输入：head = [1,2], pos = 0
     * 输出：true
     * 解释：链表中有一个环，其尾部连接到第一个节点。
     *
     * 示例 3：
     * 输入：head = [1], pos = -1
     * 输出：false
     * 解释：链表中没有环。
     */
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1,new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(3,null)))));
        System.out.println(hasCycle(listNode));
    }

    /**
     * 读不懂题.... pos 到底是什么鬼？？？？
     *
     * 看过答案才知道，pos 根本就不是啥输入值，仅仅是为了判断在链表中有没有相同地址的对象！！！
     *
     * 本答案
     * 时间复杂度 O(N)
     * 空间复杂度 O(N)
     */
    public static boolean hasCycle(ListNode head) {
        Set<ListNode> source = new HashSet<>();
        while (head != null){
            if(!source.add(head)){
                return true;
            }
            head = head.next;
        }
        return false;
    }

    /**
     * 双指针法：
     * 时间复杂度 O(N)
     * 空间复杂度 O(1)
     *
     * pre 慢指针
     * post 快指针
     *
     * 步骤：
     * 1.开启一个循环遍历这个链表 条件是 两者不能相等(如果相等了不就是一个循环链表了么！！)
     * 2. 如果循环到 当前快指针是null了 或者 当前快指针的下一个是 null 了说明肯定就不是循环链表
     * 3. 如果链表没有结束，就将当前的快慢指针替换为下一个
     */
    public static boolean hasCycle1(ListNode head) {
        if(head == null || head.next == null) return false;

        ListNode pre = head;
        ListNode post = head.next;
        while(pre != post){
            if(post == null || post.next == null){
                // 终止符
                return false;
            }

            pre = pre.next;
            post = post.next.next;
        }

        return true;
    }
}
