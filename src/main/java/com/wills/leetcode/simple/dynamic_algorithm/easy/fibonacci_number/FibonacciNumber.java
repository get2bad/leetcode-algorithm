package com.wills.leetcode.simple.dynamic_algorithm.easy.fibonacci_number;

import org.junit.Test;

/**
 * @ClassName FibonacciNumber
 * @Date 2022/7/21 11:11
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class FibonacciNumber {

//    private static final int n = 2;
//    private static final int n = 3;
    private static final int n = 4;

    @Test
    public void test(){
        System.out.println(fib(n));
    }

    public int fib(int n) {
        if(n == 0) return n;
        if(n == 1) return n;
        int res = 0,a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            res = a + b;
            a = b;
            b = res;
        }
        return res;
    }
}
