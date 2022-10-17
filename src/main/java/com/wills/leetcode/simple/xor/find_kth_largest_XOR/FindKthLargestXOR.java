package com.wills.leetcode.simple.xor.find_kth_largest_XOR;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 找出第K大的异或值
 */
public class FindKthLargestXOR {


    public static void main(String[] args) {
        int[][] source = {{5,2},{4,6}};
        System.out.println(kthLargestValue(source, 4));
    }

    public static int kthLargestValue(int[][] matrix, int k) {
        int len = matrix.length;
        int wid = matrix[0].length;
        // 遍历这个数组，保存到中间数组中
        int[][] pre = new int[len + 1][wid + 1];
        List<Integer> res = new ArrayList();
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= wid; j++) {
                pre[i][j] = pre[i - 1][j] ^ pre[i][j - 1] ^ pre[i - 1][j - 1] ^ matrix[i - 1][j - 1];
                res.add(pre[i][j]);
            }
        }
        // 然后进行排序
        Collections.sort(res, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        return res.get(k -1);
    }
}
