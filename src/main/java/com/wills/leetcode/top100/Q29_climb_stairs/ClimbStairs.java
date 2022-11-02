package com.wills.leetcode.top100.Q29_climb_stairs;

import org.junit.Test;

/**
 * @ClassName ClimbFloor
 * @Date 2022/11/2 11:43
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class ClimbStairs {

    private static final Integer n = 3;

    @Test
    public void test(){
        System.out.println(climbStairs(n));
    }

    /**
     *  假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     *
     * 示例 1：
     * 输入：n = 2
     * 输出：2
     * 解释：有两种方法可以爬到楼顶。
     * 1. 1 阶 + 1 阶
     * 2. 2 阶
     *
     * 示例 2：
     * 输入：n = 3
     * 输出：3
     * 解释：有三种方法可以爬到楼顶。
     * 1. 1 阶 + 1 阶 + 1 阶
     * 2. 1 阶 + 2 阶
     * 3. 2 阶 + 1 阶
     *
     * 示例 3
     * 输入：n = 4
     * 输出：5 f(4) = f(3) + f(2)
     *
     * 讲解网址：
     * https://leetcode.cn/problems/climbing-stairs/solution/yuan-lai-hui-pa-lou-ti-de-zheng-que-zi-s-pjez/
     * 规律问题，若有 n 级台阶，则 f(n) = f(n - 1) + f(n - 2)
     */
    public int climbStairs(int n) {
        // 递归终止条件
        if(n == 1) return 1;
        if(n == 2) return 2;

        // 当前递归层的逻辑处理 加法
        // 进入下一层递归 climbStairs(n - 1) 和 climbStairs(n - 2)
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    public int climbStairsFast(int n) {
        // lastRes 表示上上次的结果 preRes 表示上次结果
        // 用来展现 f(n) = f(n - 2) + f(n - 1)
        int lastRes = 0, preRes = 0, res = 1;
        for (int i = 1; i <= n; ++i) {
            lastRes = preRes;
            preRes = res;
            res = lastRes + preRes;
        }
        return res;
    }
}
