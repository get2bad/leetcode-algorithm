package com.wills.leetcode.other.easy.count_of_matches;

/**
 * @ClassName CountOfMatches
 * @Date 2022/1/25 10:52
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class CountOfMatches {

    public static void main(String[] args) {
        int res = numberOfMatches(14);
        System.out.println(res);
    }

    public static int numberOfMatches(int n) {
        if(n == 0) return 0;
        int total = 0;
        while(n > 0){
            if(n % 2 == 1 && n != 1){
                // 如果是基数
                n = (n - 1) / 2 + 1;
            }else{
                // 如果是偶数
                n = n / 2;
            }
            total += n;
        }
        return total - 1;
    }

    // 侮辱智商版
    public static int numberOfMatches1(int n) {
        return n - 1;
    }
}
