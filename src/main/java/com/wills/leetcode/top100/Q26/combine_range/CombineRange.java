package com.wills.leetcode.top100.Q26.combine_range;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @ClassName CombineRange
 * @Date 2022/11/2 10:47
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class CombineRange {


    private static final int[][] source = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};

    @Test
    public void test() {
        int[][] merge = merge(source);
        for (int[] ints : merge) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi]
     * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
     * <p>
     * 示例 1：
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * <p>
     * 示例2：
     * 输入：intervals = [[1,4],[4,5]]
     * 输出：[[1,5]]
     * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
     */
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        List<int[]> inter = Arrays.asList(intervals);
        List<int[]> newInter = new ArrayList<>(inter);
        // 对 intelvals 排序，需要按照先比较区间开始，如果相同再比较区间结束，使用默认的排序规则即可
        newInter.sort(Comparator.comparingInt(o -> o[0]));
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
