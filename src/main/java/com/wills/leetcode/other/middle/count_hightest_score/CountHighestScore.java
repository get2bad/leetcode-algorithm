package com.wills.leetcode.other.middle.count_hightest_score;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CountHighestScore
 * @Date 2022/3/11 09:28
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class CountHighestScore {



    public static void main(String[] args) {
        CountHighestScore obj = new CountHighestScore();
        int[] source = {-1,2,0,2,0};
        int res = obj.countHighestScoreNodes(source);
        System.out.println(res);
    }

    long maxScore = 0;
    int res = 0;
    int len;
    List<Integer>[] children;
    public int countHighestScoreNodes(int[] parents) {
        len = parents.length;
        children = new List[len];
        // 初始化 children 集合元素为一个 ArrayList
        for (int i = 0; i < len; i++) {
            children[i] = new ArrayList<>();
        }
        for (int i = 0; i < len; i++) {
            // 拿到传入的 元素值
            int p = parents[i];
            // 如果不是根元素
            if (p != -1) {
                children[p].add(i);
            }
        }
        dfs(0);
        return res;
    }

    public int dfs(int node) {
        long score = 1;
        int size = len - 1;
        // 遍历 children 这个 list 列表
        for (int c : children[node]) {
            int t = dfs(c);
            // 求出现在的跟递归返回的 t 值
            score *= t;
            size -= t;
        }
        // 子树的乘积
        if (node != 0) {
            score *= size;
        }
        // 如果 跟 maxScore 最大分数的相同，说明分数相同的子树多了一个
        if (score == maxScore) {
            res++;
        } else if (score > maxScore) {
            // 如果不是 当前分数 大于 maxScore 则赋值 并将结果值复位为1
            maxScore = score;
            res = 1;
        }
        return len - size;
    }
}
