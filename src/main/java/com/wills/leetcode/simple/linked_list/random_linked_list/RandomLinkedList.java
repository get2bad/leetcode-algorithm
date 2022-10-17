package com.wills.leetcode.simple.linked_list.random_linked_list;

import com.wills.leetcode.simple.common.ListNode;

import java.util.Random;

/**
 * 链表随机节点
 */
public class RandomLinkedList {

    public static void main(String[] args) {
        Solution solution = new Solution(new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4)))));
        System.out.println(solution.getRandom());
        System.out.println(solution.getRandom());
        System.out.println(solution.getRandom());
        System.out.println(solution.getRandom());
        System.out.println(solution.getRandom());
        System.out.println(solution.getRandom());
    }
}

//  从链表头开始，遍历整个链表，对遍历到的第 ii 个节点，
//  随机选择区间 [0,i)[0,i) 内的一个整数，如果其等于 00，则将答案置为该节点值，否则答案不变。
class Solution {

    ListNode head;
    Random random;

    public Solution(ListNode head) {
        this.random = new Random();
        this.head = head;
    }

    public int getRandom() {
        int i = 1, ans = 0;
        ListNode tmp = head;
        while(tmp != null){
            if (random.nextInt(i) == 0) {
                ans = tmp.val;
            }
            ++i;
            tmp = tmp.next;
        }
        return ans;
    }
}

// 使用List的方式
//class Solution {
//
//    private List<Integer> source;
//    private Random random;
//
//    public Solution(ListNode head) {
//        source = new ArrayList<>();
//        while(head != null){
//            source.add(head.val);
//            head = head.next;
//        }
//        random = new Random();
//    }
//
//    public int getRandom() {
//        return source.get(random.nextInt(source.size()));
//    }
//}

// 使用临时值的方式
//class Solution {
//
//    ListNode source = null;
//    int len = 1;
//
//    // 构造方法，从调用方中拿到了一个完整的linkedList链
//    public Solution(ListNode head) {
//        this.source = head;
//        while(head.next != null){
//            len++;
//            head = head.next;
//        }
//        System.out.println("链表长度为" + len);
//    }
//
//    /**
//     * 设计一个，可以随机选取一个链表的算法
//     * @return
//     */
//    public int getRandom() {
//        ListNode tmp = source;
//        Random random = new Random();
//        int index = random.nextInt(len + 1);
//        System.out.println("随机索引为："  + index);
//        while( tmp != null){
//            if(--index <= 0){
//                return tmp.val;
//            }
//            tmp = tmp.next;
//        }
//        return -1;
//    }
//}
