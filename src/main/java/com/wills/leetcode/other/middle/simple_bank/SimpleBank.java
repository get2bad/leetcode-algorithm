package com.wills.leetcode.other.middle.simple_bank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName SimpleBank
 * @Date 2022/3/18 10:15
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class SimpleBank {

    public static void main(String[] args) {
        Bank bank = new Bank(new long[]{10, 100, 20, 50, 30});
        boolean res = false;
        res = bank.withdraw(3, 10);    // 返回 true ，账户 3 的余额是 $20 ，所以可以取款 $10 。
        // 账户 3 余额为 $20 - $10 = $10 。
        res = bank.transfer(5, 1, 20); // 返回 true ，账户 5 的余额是 $30 ，所以可以转账 $20 。
        // 账户 5 的余额为 $30 - $20 = $10 ，账户 1 的余额为 $10 + $20 = $30 。
        res = bank.deposit(5, 20);     // 返回 true ，可以向账户 5 存款 $20 。
        // 账户 5 的余额为 $10 + $20 = $30 。
        res = bank.transfer(3, 4, 15); // 返回 false ，账户 3 的当前余额是 $10 。
        // 所以无法转账 $15 。
        res = bank.withdraw(10, 50);   // 返回 false ，交易无效，因为账户 10 并不存在。
        System.out.println(res);
    }

    static class Bank {

        long[] balance;

        boolean check(int a) {
            return a > 0 && a <= balance.length;
        }

        public Bank(long[] balance) {
            this.balance = balance;
        }

        public boolean transfer(int account1, int account2, long money) {
            if (check(account2) && withdraw(account1, money)) return deposit(account2, money);
            return false;
        }

        public boolean deposit(int account, long money) {
            if (!check(account)) return false;
            balance[account - 1] += money;
            return true;
        }

        public boolean withdraw(int account, long money) {
            if (!check(account) || balance[account - 1] < money) return false;
            balance[account - 1] -= money;
            return true;
        }
    }
}
