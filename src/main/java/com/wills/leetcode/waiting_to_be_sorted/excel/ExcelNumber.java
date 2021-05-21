package com.wills.leetcode.waiting_to_be_sorted.excel;

/**
 * @author 王帅
 * @date 2021-03-15 09:17:55
 * @description:
 * Excel表序列号
 */
public class ExcelNumber {

    /**
     * 给定一个Excel表格中的列名称，返回其相应的列序号。
     *
     * 例如，
     *
     *     A -> 1
     *     B -> 2
     *     C -> 3
     *     ...
     *     Z -> 26
     *     AA -> 27
     *     AB -> 28
     *     ...
     *
     * 示例 1:
     * 输入: "A"
     * 输出: 1
     *
     * 示例 2:
     * 输入: "AB"
     * 输出: 28
     *
     * 示例 3:
     * 输入: "ZY"
     * 输出: 701
     */
    public static void main(String[] args) {
        String source = "AA";
        System.out.println(titleToNumber(source));
    }

    /**
     * 思路：
     * 1. 切割字符串
     * 2. 可以将 A-Z的比作 26进制的数，
     *      如果就一个A 那么就是 26^0 = 1
     *      如果两个A 那么就是 26^1 = 26 + 26^0 = 27
     *      以此类推，那么本题的答案就出来了
     *      后面就是 ASCII码的计算了
     *
     *      因为 A的ASCII码为65 所以，就直接 - 'A' + 1
     *      每一次循环 就是 之前的数 * 26 + 现在的数
     * @param columnTitle
     * @return
     */
    public static int titleToNumber(String columnTitle) {
        if(columnTitle == null || columnTitle.length() == 0) return 0;
        int res = 0;
        columnTitle = columnTitle.toUpperCase();
        char[] array = columnTitle.toCharArray();
        for (int i = 0; i < array.length; i++) {
            int num = array[i] - 'A' + 1;
            res = res * 26 + num;
        }
        return res;
    }
}
