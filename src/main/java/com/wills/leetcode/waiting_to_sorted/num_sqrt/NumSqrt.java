package com.wills.leetcode.waiting_to_sorted.num_sqrt;

/**
 * 求正整数的平方根
 * <p>
 * 实现 int sqrt(int x) 函数。
 * <p>
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 示例 1:
 * 输入: 4
 * 输出: 2
 * <p>
 * 示例 2:
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 * 由于返回类型是整数，小数部分将被舍去。
 */
public class NumSqrt {

    public static void main(String[] args) {
        int source = 2147395600;
//        int source = 9;
        System.out.println(mySqrt1(source));
    }

    /**
     * 自制函数求平方根
     * <p>
     * 提交
     * 输入：2147395600
     * 输出：289398 ×
     * 应该输出： 46340
     * <p>
     * 错误分析： for 循环循环因子i 一开始被声明为int，进行叠乘 / 赋值时超限了，所以输出了289398，改为long后正常输出46340
     * <p>
     * 时间复杂度： O(n)
     */
    public static int mySqrt(int x) {
        int res = 0;
        for (long i = 1; i <= x; i++) {
            if (i * i <= x) {
                res = Integer.parseInt(i + "");
            } else {
                break;
            }
        }
        return res;
    }

    /**
     * 使用二分查找法 巧妙解决此题
     * <p>
     * 时间复杂度O(logx)
     *
     * 注意： 二分查找
     * ☆ 如果使用int mid = (end - start) / 2;可能会引发死循环问题，
     * 因为当start 和 end 相差 1的状态下， mid为 0 这样会适中转不出去循环
     *
     * 应该替换为 ** int mid = start + (end - start) / 2; **
     * 如果 start = 2 end = 3 则 mid = 1 这样就规避了 mid = 0 触发的死循环问题
     */
    public static int mySqrt1(int x) {
        int start = 0, end = x, res = 0;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if ((long) mid * mid <= x) {
                // 如果中间数平方小于目标数，就进行start指针移位
                res = mid;
                start = mid + 1;
            } else {
                // 如果中间数平方发育目标数，就进行end指针移位
                end = mid - 1;
            }
        }
        return res;
    }
}
