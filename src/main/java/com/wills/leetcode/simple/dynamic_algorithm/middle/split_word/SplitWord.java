package com.wills.leetcode.simple.dynamic_algorithm.middle.split_word;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName SplitWord
 * @Date 2022/7/20 09:46
 * @Author 王帅
 * @Version 1.0
 * @Description 单词拆分
 */
public class SplitWord {

//    private static final String source = "applepenapple";
//    private static final String[] words = {"apple","pen"};

//    private static final String source = "catsandog";
//    private static final String[] words = {"cats", "dog", "sand", "and", "cat"};

    private static final String source = "aaaaaaa";
    private static final String[] words = {"aaaa", "aaa"};

    private static final List<String> wordDict = new ArrayList<String>(Arrays.asList(words));

    @Test
    public void test() {
        System.out.println(wordBreak(source, wordDict));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 0; i < len; i++) {
            if (!dp[i]) {
                continue;
            }
            for (String word : wordDict) {
                int end = word.length() + i;
                if (end <= len && s.substring(i,end).equals(word)) {
                    dp[i + word.length()] = true;
                }
            }
        }
        return dp[len];
    }

    // 自我实现的版本，如果遇到 aaaaaaa + {"aaaa","aaa"} 无法处理，遂放弃！
    public boolean wordBreak1(String s, List<String> wordDict) {
        int len = s.length(), containsFlag = 0;
        for (int i = 1; i <= len; i++) {
            String tmp = s.substring(containsFlag, i);
            if (wordDict.contains(tmp)) containsFlag = i;
        }

        return containsFlag == len;
    }
}
