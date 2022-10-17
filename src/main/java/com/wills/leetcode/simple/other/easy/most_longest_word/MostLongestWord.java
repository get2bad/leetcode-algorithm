package com.wills.leetcode.simple.other.easy.most_longest_word;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName MostLongestWord
 * @Date 2022/3/17 09:46
 * @Author 王帅
 * @Version 1.0
 * @Description
 * 最长的单词
 */
public class MostLongestWord {

    public static void main(String[] args) {
        String[] source = {"a", "banana", "app", "appl", "ap", "apply", "apple"};
        MostLongestWord obj = new MostLongestWord();
        String res = obj.longestWord1(source);
        System.out.println(res);
    }

    /**
     * 给出一个字符串数组 words 组成的一本英语词典。返回 words 中最长的一个单词，
     * 该单词是由 words 词典中其他单词逐步添加一个字母组成。
     * 若其中有多个可行的答案，则返回答案中字典序最小的单词。若无答案，则返回空字符串。
     * 示例 1：
     *
     * 输入：words = ["w","wo","wor","worl", "world"]
     * 输出："world"
     * 解释： 单词"world"可由"w", "wo", "wor", 和 "worl"逐步添加一个字母组成。
     *
     * 示例 2：
     * 输入：words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
     * 输出："apple"
     * 解释："apply" 和 "apple" 都能由词典中的单词组成。但是 "apple" 的字典序小于 "apply"
     */
    public String longestWord(String[] words) {
        String res = "";
        Set<String> set = new HashSet<>();
        // 先使用 set 做一个去重
        for (String s : words) set.add(s);
        // 遍历 set
        for (String source : set) {
            int sourceLen = source.length(), resLen = res.length();
            // 如果当前遍历的元素 比 结果元素的len 就没必要比较了不符合题目要求
            if (sourceLen < resLen) continue;
            // 排除相同字符数的 并且 当前遍历元素
            if (sourceLen == resLen && source.compareTo(res) > 0) continue;
            boolean ok = true;
            // 按位遍历 数据字典中 是否有相关的值
            for (int i = 1; i <= sourceLen && ok; i++) {
                String sub = source.substring(0, i);
                if (!set.contains(sub)) ok = false;
            }
            // 有的话就将返回值进行赋值
            if (ok) res = source;
        }
        return res;
    }


    // 前缀树算法
    static int N = 30010, M = 26;
    static int[][] tr = new int[N][M];
    static boolean[] isEnd = new boolean[N];
    static int idx = 0;

    public String longestWord1(String[] words) {
        Arrays.fill(isEnd, false);
        for (int i = 0; i <= idx; i++) Arrays.fill(tr[i], 0);
        idx = 0;

        String ans = "";
        for (String s : words) add(s);
        for (String s : words) {
            int n = s.length(), m = ans.length();
            if (n < m) continue;
            if (n == m && s.compareTo(ans) > 0) continue;
            if (query(s)) ans = s;
        }
        return ans;
    }
    void add(String s) {
        int p = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            if (tr[p][u] == 0) tr[p][u] = ++idx;
            p = tr[p][u];
        }
        isEnd[p] = true;
    }
    boolean query(String s) {
        int p = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            p = tr[p][u];
            if (!isEnd[p]) return false;
        }
        return true;
    }
}
