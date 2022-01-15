package com.wills.leetcode.other.easy.leetcode_bank;

/**
 * @ClassName LeetCodeBank
 * @Date 2022/1/15 10:39
 * @Author 王帅
 * @Version 1.0
 * @Description
 * 力扣银行，制作叠加的问题
 */
public class LeetCodeBank {

    public static void main(String[] args) {
        LeetCodeBank bank = new LeetCodeBank();
        int res = bank.totalMoney(10);
        System.out.println(res);
    }

    public int totalMoney(int n) {
        int currentMoney = 1, day = 1;
        int total = 0;
        for (int i = 0; i < n; ++i) {
            total += currentMoney + day - 1;
            if (++day == 8) {
                day = 1;
                ++currentMoney;
            }
        }
        return total;
    }
}
