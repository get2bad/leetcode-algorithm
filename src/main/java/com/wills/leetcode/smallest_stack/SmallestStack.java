package com.wills.leetcode.smallest_stack;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 王帅
 * @date 2021-03-11 09:32:42
 * @description:
 * 最小栈
 */
public class SmallestStack {

    /**
     * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
     * push(x) —— 将元素 x 推入栈中。
     * pop() —— 删除栈顶的元素。
     * top() —— 获取栈顶元素。
     * getMin() —— 检索栈中的最小元素。
     *
     * 示例:
     * 输入：
     * ["MinStack","push","push","push","getMin","pop","top","getMin"]
     * [[],[-2],[0],[-3],[],[],[],[]]
     * 输出：
     * [null,null,null,null,-3,null,0,-2]
     * 解释：
     * MinStack minStack = new MinStack();
     * minStack.push(-2);
     * minStack.push(0);
     * minStack.push(-3);
     * minStack.getMin();   --> 返回 -3.
     * minStack.pop();
     * minStack.top();      --> 返回 0.
     * minStack.getMin();   --> 返回 -2.
     */
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(-0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }


}

class MinStack {
    Deque<Integer> xStack;
    Deque<Integer> minStack;

    public MinStack() {
        xStack = new LinkedList<Integer>();
        minStack = new LinkedList<Integer>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        xStack.push(x);
        minStack.push(Math.min(minStack.peek(), x));
    }

    public void pop() {
        xStack.pop();
        minStack.pop();
    }

    public int top() {
        return xStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

//class MinStack {
//
//    private List<Integer> stack;
//    private List<Integer> minStack;
//    private Integer min;
//
//    /** initialize your data structure here. */
//    public MinStack() {
//        stack = new LinkedList<>();
//        minStack = new LinkedList<>();
//        min = Integer.MAX_VALUE;
//    }
//
//    public void push(int x) {
//        stack.add(x);
//        if(x < min){
//            min = x;
//        }
//        minStack.add(min);
//    }
//
//    public void pop() {
//        minStack.remove(minStack.size() - 1);
//        stack.remove(stack.size() - 1);
//    }
//
//    public int top() {
//        return stack.get(stack.size() - 1);
//    }
//
//    public int getMin() {
//        return minStack.get(minStack.size() - 1);
//    }
//}