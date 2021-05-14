package com.wills.leetcode.dynamic_algorithm.climbing_stairs;

/**
 * 爬楼梯问题
 * <p>
 * 假设你正在爬楼梯。需要 n阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * <p>
 * 示例 2：
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 */
public class ClimbingStairs {

    public static void main(String[] args) {
        int source = 30;
        System.out.println(climbStairs1(source));
    }

    /**
     * 递归方法调用问题  伪代码模式，本题主要考察 动态规划问题
     */
    public static int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }
        return climbStairs1(n - 1) + climbStairs1(n - 2);
    }

    /**
     * 动态规划解法：
     * <p>
     * 这种方法的时间复杂度是O(n)，空间复杂度是是O(n)。
     */
    public static int climbStairs1(int n) {
        if(n <= 2){
            return n;
        }
        // 可以加上缓存功能，防止因为递归调用带来的不必要的计算
//        int[] cache = new int[n];
//        cache[0] = 1;
//        cache[1] = 2;
//        for (int i = 2; i < n; i++) {
//            cache[i] = cache[i - 1] + cache[i - 2];
//        }
//
//        return cache[n - 1];
        // 上面代码的优化版 优化空间复杂度为 O(1)
        // preOfPre 代表 f(n - 2) pre 代表 f(n -1) cur 代表最终结果
        // 因为我们知道了 f(1) = 1 f(2) = 2 所以要减少不必要的计算，所以直接将 pre / preOfPre 赋值结果
        int pre = 2, preOfPre = 1, cur = 1;
        // 然后我们从 f(3) = f(2) + f(1) 开始计算
        for(int i = 3; i <= n; i++) {
            // f(n -1) + f(n -2)
            cur = pre + preOfPre;
            // 因为要继续下一次循环了，所以要将f(n -2)调整到 f(n -1)的状态
            preOfPre = pre;
            // 因为要继续下一次循环了，所以要将当前的cur结果赋值给 f(n -1) 以供下一次计算 f(n -1) + f(n -2)
            pre = cur;
        }
        return cur;
    }
}
