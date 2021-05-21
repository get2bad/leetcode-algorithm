package com.wills.leetcode.waiting_to_be_sorted.happy_num;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 王帅
 * @date 2021-03-17 09:37:53
 * @description:
 * 快乐数
 */
public class HappyNum {

    /**
     * 编写一个算法来判断一个数 n 是不是快乐数。
     *
     * 「快乐数」定义为：
     *
     * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
     * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
     * 如果 可以变为  1，那么这个数就是快乐数。
     * 如果 n 是快乐数就返回 true ；不是，则返回 false 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：19
     * 输出：true
     * 解释：
     * 12 + 92 = 82
     * 82 + 22 = 68
     * 62 + 82 = 100
     * 12 + 02 + 02 = 1
     * 示例 2：
     *
     * 输入：n = 2
     * 输出：false
     */
    public static void main(String[] args) {
        int source = 100;
        System.out.println(isHappy1(source));
    }

    /**
     * 哈希表法
     */
    public static boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();
        while(n != 1 && !seen.contains(n)){
            seen.add(n);
            n = happyNum(n);
        }
        return n == 1;
    }

    /**
     * 快慢指针法
     */
    public static boolean isHappy1(int n) {
        int fast = happyNum(n);
        int slow = n;
        while(fast != 1 && slow != fast ){
            slow = happyNum(slow);
            fast = happyNum(happyNum(fast));
        }
        return fast == 1;
    }

    public static int happyNum(int source){
        int res = 0;
        while(source > 0){
            int index = source % 10;
            res += index * index;
            source /= 10;
        }
        return res;
    }
}
