package com.wills.leetcode.simple.other.middle.gray_code;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName GrayCode
 * @Date 2022/1/8 09:38
 * @Author 王帅
 * @Version 1.0
 * @Description
 * 格雷编码
 */
public class GrayCode {

    public static void main(String[] args) {
        int source = 2;
        grayCode(source).forEach(System.out::println);
    }

    /**
     * 提示，返回集合的最大的数字不能小于0且不能大于 2^n - 1
     *  1 <= n <= 16
     *  我们分析一下
     *  2^1 - 1 = 1 的二进制是 0000 0000 0000 0001
     *  2^16 - 1 = 65535的二进制是 1111 1111 1111 1111
     *  找规律的问题：
     *  0 -> 000
     *  1 -> 001
     *  3 -> 011
     *  2 -> 010
     *
     *  6 -> 110
     *  7 -> 111
     *  5 -> 101
     *  4 -> 100
     *
     *  由上述的列表可以看出，每一个循环都是4，且位数 >2位时会首位相同，然后做了一个镜面反射
     * @param n
     * @return
     */
    public static List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        if(n == 0 || n ==1) return res;
        while (n-- > 0) {
            int m = res.size();
            for (int i = m - 1; i >= 0; i--) {
                res.set(i, res.get(i) << 1);
                res.add(res.get(i) + 1);
            }
        }
        return res;
    }
}
