package com.wills.leetcode.other.middle.pascal_triangle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王帅
 * @date 2021-03-02 09:39:06
 * @description:
 * 杨辉三角
 */
public class PascalTrangle2 {

    /**
     * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
     *
     *
     *
     * 在杨辉三角中，每个数是它左上方和右上方的数的和。
     * [
     *      [1],
     *     [1,1],
     *    [1,2,1],
     *   [1,3,3,1],
     *  [1,4,6,4,1]
     * ]
     * 示例:
     *
     * 输入: 3
     * 输出: [1,3,3,1]
     */
    public static void main(String[] args) {
        getRow(3).forEach(System.out::println);
    }

    /**
     * 直接返回就可以
     */
    public static List<Integer> getRow(int rowIndex) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
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
        return res.get(rowIndex);
    }
}
