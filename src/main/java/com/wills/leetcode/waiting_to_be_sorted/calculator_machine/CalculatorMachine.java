package com.wills.leetcode.waiting_to_be_sorted.calculator_machine;

/**
 * @author 王帅
 * @date 2021-02-13 08:40:05
 * @description:
 * 速算机器人
 */
public class CalculatorMachine {

    /**
     * 小扣在秋日市集发现了一款速算机器人。店家对机器人说出两个数字（记作 x 和 y），请小扣说出计算指令：
     *
     * "A" 运算：使 x = 2 * x + y；
     * "B" 运算：使 y = 2 * y + x。
     * 在本次游戏中，店家说出的数字为 x = 1 和 y = 0，小扣说出的计算指令记作仅由大写字母 A、B 组成的字符串 s，
     * 字符串中字符的顺序表示计算顺序，请返回最终 x 与 y 的和为多少。
     *
     * 示例 1：
     * 输入：s = "AB"
     * 输出：4
     *
     * 解释：
     * 经过一次 A 运算后，x = 2, y = 0。
     * 再经过一次 B 运算，x = 2, y = 2。
     * 最终 x 与 y 之和为 4。
     *
     * 提示：
     * 0 <= s.length <= 10
     * s 由 'A' 和 'B' 组成
     */
    public static void main(String[] args) {
        String source = "AB";
        System.out.println(calculate1(source));
    }

    /**
     * 思路 先拆分字符串，然后解析两个运算式，将其结果作为下一次的运算因子
     *
     * 思考： 直接进行暴力枚举 就可解决
     * @param s
     * @return
     */
    public static int calculate(String s) {
        int x=1,y=0;
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)){
                //"A" 运算：使 x = 2 * x + y；
                //"B" 运算：使 y = 2 * y + x。
                case 'A': x = 2*x + y;break;
                case 'B': y = 2*y +x;break;
                default: x = 0;y=0;break;
            }
        }
        return x+y;
    }

    /**
     * 然后仔细思考一下......
     * wtf???
     * 这不是二进制运算转十进制么？？？？？
     *
     * 好家伙，我直接 1 << s.length()
     */
    public static int calculate1(String s) {

        return 1 << s.length();
    }
}
