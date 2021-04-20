package com.wills.leetcode.dynamic_algorithm.middle.unique_binary_search_tree;

/**
 * @author 王帅
 * @date 2021-04-20 11:03:37
 * @description:
 */
public class UniqueBinarySearchTree {

    public static void main(String[] args) {
        System.out.println(numTrees(3));
    }

    public static int numTrees(int n) {
        int[] res = new int[n + 1];
        // 如果是输入 0 或者 1 的话 他们只有一种方法
        res[0] = 1;
        res[1] = 1;

        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                res[i] += res[j - 1] * res[i - j];
            }
        }
        return res[n];
    }
}
