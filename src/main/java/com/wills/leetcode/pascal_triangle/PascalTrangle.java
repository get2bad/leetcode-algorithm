package com.wills.leetcode.pascal_triangle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王帅
 * @date 2021-03-02 09:39:06
 * @description:
 * 杨辉三角
 */
public class PascalTrangle {

    /**
     * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
     *
     *
     *
     * 在杨辉三角中，每个数是它左上方和右上方的数的和。
     *
     * 示例:
     *
     * 输入: 5
     * 输出:
     * [
     *      [1],
     *     [1,1],
     *    [1,2,1],
     *   [1,3,3,1],
     *  [1,4,6,4,1]
     * ]
     */
    public static void main(String[] args) {
        generate(5).forEach(System.out::println);
    }

    /**
     * 分析： -> 杨辉三角
     * 1. 首先如果传入的数字为5，那么杨辉三角的长度最长为5 高度最高也为5
     * 2. 其次，如果上面有两个数，那么下面的数字应该是上面两个相邻的数字的和
     *
     * 思路：
     * 1. 进行两次 for 循环 如果 当前循环的 数字为 i 那么 a[i][j] = a[i - 1][j] + a[i - 1][j - 1]
     *
     * 时间复杂度 O(n^2)
     */
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> index = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                // 如果是边界，那么就直接赋值为1
                if(j == 0 || j == i){
                    index.add(1);
                } else {
                    index.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
                }
            }
            res.add(index);
        }
        return res;
    }
}
