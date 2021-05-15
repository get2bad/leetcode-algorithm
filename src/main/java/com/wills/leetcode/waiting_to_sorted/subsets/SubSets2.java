package com.wills.leetcode.waiting_to_sorted.subsets;

import java.util.*;

/**
 * @author 王帅
 * @date 2021-03-31 09:21:20
 * @description:
 * 子集II
 */
public class SubSets2 {

    public static void main(String[] args) {
        int[] source = {1,2,2};
        List<List<Integer>> res = subsetsWithDup(source);
        System.out.println(res);
    }

    // 穷举法 + 双层遍历
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        Set<List<Integer>> ans = new HashSet<>();
        List<Integer> cur = new ArrayList<>();

        // 枚举 i 代表，枚举所有的选择方案状态
        // 例如 [1,2]，我们有 []、[1]、[2]、[1,2] 几种方案，分别对应了 00、10、01、11 几种状态
        for (int i = 0; i < (1 << n); i++) {
            cur.clear();
            // 对当前状态进行诸位检查，如果当前状态为 1 代表被选择，加入当前方案中
            for (int j = 0; j < n; j++) {
                int t = (i >> j) & 1;
                if (t == 1) cur.add(nums[j]);
            }
            // 将当前方案中加入结果集
            ans.add(new ArrayList<>(cur));
        }
        return new ArrayList<>(ans);
    }
}
