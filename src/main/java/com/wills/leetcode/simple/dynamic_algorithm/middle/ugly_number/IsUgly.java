package com.wills.leetcode.simple.dynamic_algorithm.middle.ugly_number;

import org.junit.Test;

/**
 * @ClassName IsUgly
 * @Date 2022/8/8 14:35
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class IsUgly {

    private static final int n = 6;

    @Test
    public void test() {
        System.out.println(isUgly(n));
    }

    public boolean isUgly(int n) {
        if (n < 1) return false;
        while (n % 5 == 0) {
            n /= 5;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        while (n % 2 == 0) {
            n >>= 1;
        }
        return n == 1;
    }
}
