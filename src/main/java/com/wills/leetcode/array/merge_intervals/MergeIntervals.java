package com.wills.leetcode.array.merge_intervals;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName MergeIntervals
 * @Date 2022/6/29 14:52
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class MergeIntervals {

    private static int[][] source = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};

    @Test
    public void test() {
        int[][] merge = merge(source);
        for (int i = 0; i < merge.length; i++) {
            System.out.println(Arrays.toString(merge[i]));
        }
    }

    public int[][] merge(int[][] intervals) {
        List<int[]> inter = Arrays.asList(intervals);
        List<int[]> newInter = new ArrayList<>(inter);
        // 对 intelvals 排序，需要按照先比较区间开始，如果相同再比较区间结束，使用默认的排序规则即可
        newInter.sort((o1, o2) -> o1[0] - o2[0]);

        List<int[]> res = new ArrayList<>();
        // 使用双指针，左边指针指向当前区间的开始
        for (int i = 0; i < newInter.size(); ) {
            // 使用一个变量来记录连续的范围 t
            int t = newInter.get(i)[1];
            // 右指针开始往后寻找，如果后续的区间的开始值比 t 还小，说明重复了，可以归并到一起
            int j = i + 1;
            while (j < newInter.size() && newInter.get(j)[0] <= t) {
                // 此时更新更大的结束值到 t
                t = Math.max(t, newInter.get(j)[1]);
                j++;
            }
            // 直到区间断开，将 t 作为区间结束，存储到答案里
            res.add(new int[]{newInter.get(i)[0], t});
            // 然后移动左指针，跳过中间已经合并的区间
            i = j;
        }

        int[][] ans = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) {
            ans[i][0] = res.get(i)[0];
            ans[i][1] = res.get(i)[1];
        }
        return ans;
    }
}
