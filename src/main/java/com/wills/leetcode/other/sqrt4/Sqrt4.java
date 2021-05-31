package com.wills.leetcode.other.sqrt4;

/**
 * @author 王帅
 * @ClassName Sqrt4
 * @date 2021/5/31 09:35
 * @Version 1.0
 * @Description
 */
public class Sqrt4 {

    public static void main(String[] args) {
        int source = 9;
        System.out.println(isPowerOfFour1(source));
    }

    public static boolean isPowerOfFour(int n) {
        int dp = 0,source = 0;
        while(source <= n){
            source = (int)Math.pow(4,dp);
            if(source == n){
                return true;
            }
            dp++;
        }
        return false;
    }

    public static boolean isPowerOfFour1(int n) {
        if (n <= 0) return false;
        // 先对这个值进行开根号
        int x = (int)Math.sqrt(n);
        // 如果 开根后的结果不等于传入的说明不是2的次幂，直接返回 false
        // 开根后的结果 和 其 负值 进行与运算 如果不相等 原值就返回 false
        // 如果是9 这种 和 自己的负数开根 3 & -3 那么结果就是1 和之前的不同，说明不是2的次幂
        return x * x == n && (x & -x) == x;
    }

    public static boolean isPowerOfFour2(int n) {
        if(n <= 0) return false;
        if((n & (n - 1)) != 0) return false;
        int cnt = 0;
        while(n != 0) {
            n >>= 1;
            cnt++;
        }
        return cnt % 2 == 1;
    }
}
