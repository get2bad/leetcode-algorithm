package com.wills.leetcode.other.hard.line_points;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 王帅
 * @ClassName LinePoints
 * @date 2021/6/24 09:19
 * @Version 1.0
 * @Description
 */
public class LinePoints {

    public static void main(String[] args) {
        int[][] source = {{1,1},{2,2},{3,3}};
        System.out.println(maxPoints(source));
    }

    // 哈希表解决方案
    public static int maxPoints(int[][] ps) {
        int len = ps.length;
        int res = 1;
        for (int i = 0; i < len; i++) {
            Map<String, Integer> map = new HashMap<>();
            // 由当前点 i 发出的直线所经过的最多点数量
            int max = 0;
            int x1 = ps[i][0], y1 = ps[i][1];
            for (int j = i + 1; j < len; j++) {
                int x2 = ps[j][0], y2 = ps[j][1];
                // 找出两个点的 差值
                int xDiff = x1 - x2, yDiff = y1 - y2;
                // k 为两个点组成直线的斜率
                int k = gcd(xDiff, yDiff);
                // 一个临时值的斜率 key
                String key = (xDiff / k) + "_" + (yDiff / k);
                // 将求出的两个点的斜率存入哈希表，如果没有就是从1开始，如果有就是之前的值 + 1
                map.put(key, map.getOrDefault(key, 0) + 1);
                // 和上一次斜率进行比较，如果比上一次斜率大，那么最大斜率就是当前斜率
                max = Math.max(max, map.get(key));
            }
            // 取当前哈希表存储的斜率和 res的最大值 作为最新的最大值
            res = Math.max(res, max + 1);
        }
        return res;
    }

    // 求出斜率
    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
