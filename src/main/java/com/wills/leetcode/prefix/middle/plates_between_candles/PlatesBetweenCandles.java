package com.wills.leetcode.prefix.middle.plates_between_candles;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PlatesBetweenCandles
 * @Date 2022/3/8 09:55
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class PlatesBetweenCandles {

    public static void main(String[] args) {
        String s = "***|**|*****|**||**|*";
        int[][] queries = {{1,17},{4,5},{14,17},{5,11},{15,16}};
//        String s = "**|**|***|";
//        int[][] queries = {{2,5},{5,9}};
        int[] res = platesBetweenCandles(s, queries);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }

    // 二分 + 前缀和
    public static int[] platesBetweenCandles1(String s, int[][] queries) {
        char[] strArray = s.toCharArray();
        int strLen = strArray.length, qsLen = queries.length;
        int[] ans = new int[qsLen], sum = new int[strLen + 1];
        List<Integer> list = new ArrayList<>();
        // 执行前缀和的准备操作，遇到 * 就和之前的加1否则加0，list用于存储 | 的索引
        for (int i = 0; i < strLen; i++) {
            if (strArray[i] == '|') list.add(i);
            sum[i + 1] = sum[i] + (strArray[i] == '*' ? 1 : 0);
        }
        // r如果没有蜡烛 就直接原样返回就行 0,0,0,0
        if (list.size() == 0) return ans;
        // 遍历需要查找的区间，查找盘子
        for (int i = 0; i < qsLen; i++) {
            // 拿到起始点/结束点
            int start = queries[i][0], end = queries[i][1];
            int leftIndex = -1, rightIndex = -1;
            // 找到 start 右边最近的蜡烛
            int left = 0, right = list.size() - 1;
            while (left < right) {
                // 找到一半的值(二分法)
                int mid = left + right >> 1;
                // 如果找到蜡烛的索引 大于等于 start，就将 right的值切换为中间值继续寻找
                if (list.get(mid) >= start) right = mid;
                // 否则就移动 left值
                else left = mid + 1;
            }
            // 如果 右边界索引 大于 start  ** 拿到左边界的蜡烛索引 **
            if (list.get(right) >= start) leftIndex = list.get(right);
            // 否则 start > 右边界 是不合理的就置0 进行下一次
            else continue;
            // 找到 end 左边最近的蜡烛 同上
            left = 0; right = list.size() - 1;
            while (left < right) {
                int mid = left + right + 1 >> 1;
                if (list.get(mid) <= end) left = mid;
                else right = mid - 1;
            }
            if (list.get(right) <= end) rightIndex = list.get(right);
            else continue;
            // 关键！因为前面用了前缀和来，所以两个索引相减就是答案！
            if (leftIndex <= rightIndex) ans[i] = sum[rightIndex + 1] - sum[leftIndex];
        }
        return ans;
    }

    /**
     * 暴力法
     */
    public static int[] platesBetweenCandles(String s, int[][] queries) {
        int len = queries.length;
        int[] res = new int[len];

        for (int i = 0; i < len; i++) {
            String substring = s.substring(queries[i][0], queries[i][1] + 1);
            res[i] = getCount(substring);
        }

        return res;
    }

    public static int getCount(String s){
        int res = 0;
        int start = s.indexOf("|");
        int end = s.lastIndexOf("|");
        if(start == -1 || end == -1) return  0;
        char[] source = s.toCharArray();
        for (int i = start; i < end; i++) {
            if(source[i] != '|'){
                res++;
            }
        }
        return res;
    }
}
