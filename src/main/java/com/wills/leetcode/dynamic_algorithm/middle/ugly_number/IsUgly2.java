package com.wills.leetcode.dynamic_algorithm.middle.ugly_number;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * @ClassName IsUgly
 * @Date 2022/8/8 14:35
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class IsUgly2 {

//    private static final int n = 10;
    private static final int n = 11;

    @Test
    public void test() {
        System.out.println(nthUglyNumber(n));
        System.out.println("=======================================");
        System.out.println(nthUglyNumber1(n));
    }

    // 构建小顶堆 注意可能存在超界的问题
    public int nthUglyNumber(int n) {
        // 自动排序的 队列
        PriorityQueue<Long> stack = new PriorityQueue<>();
        long res = 1;
        for (int i = 1; i < n; i++) {
            stack.offer(res * 2);
            stack.offer(res * 3);
            stack.offer(res * 5);
            res = stack.poll();
            // 每次取出队头元素，存入队头元素 * 2、队头元素 * 3、队头元素 * 5
            //但注意，像 12 这个元素，可由 4 乘 3 得到，也可由 6 乘 2 得到，所以要注意去重
            while (!stack.isEmpty() && res == stack.peek()) {
                stack.poll();
            }
        }
        return (int)res;
    }

    public int nthUglyNumber1(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; i++) {
            int num2 = dp[p2] * 2, num3 = dp[p3] * 3, num5 = dp[p5] * 5;
            dp[i] = Math.min(Math.min(num2, num3), num5);
            if (dp[i] == num2) {
                p2++;
            }
            if (dp[i] == num3) {
                p3++;
            }
            if (dp[i] == num5) {
                p5++;
            }
        }
        return dp[n];
    }
}
