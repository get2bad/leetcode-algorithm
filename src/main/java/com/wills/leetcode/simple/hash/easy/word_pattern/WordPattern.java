package com.wills.leetcode.simple.hash.easy.word_pattern;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName WordPattern
 * @Date 2022/3/22 09:42
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class WordPattern {

    public static void main(String[] args) {
        WordPattern pattern = new WordPattern();
        boolean res = pattern.wordPattern("abba", "dog dog dog dog");
        System.out.println(res);
    }

    /**
     * 给定一种规律 pattern和一个字符串s，判断 s是否遵循相同的规律。
     * 这里的遵循指完全匹配，例如，pattern里的每个字母和字符串str中的每个非空单词之间存在着双向连接的对应规律。
     *
     * 示例1:
     * 输入: pattern = "abba", str = "dog cat cat dog"
     * 输出: true
     *
     * 示例 2:
     * 输入:pattern = "abba", str = "dog cat cat fish"
     * 输出: false
     *
     * 示例 3:
     * 输入: pattern = "aaaa", str = "dog cat cat dog"
     * 输出: false
     */
    public boolean wordPattern(String pattern, String s) {
        int len = pattern.length();

        String[] arr = s.split(" ");
        if(len != arr.length) return false;
        Map<Character,String> source = new HashMap<>();
        Map<String,Character> reverse = new HashMap<>();
        source.put(pattern.charAt(0), arr[0]);
        reverse.put(arr[0],pattern.charAt(0));
        for (int i = 1; i < arr.length; i++) {
            int currFlag = i % len;
            char flag = pattern.charAt(currFlag);
            int pre = Math.max((currFlag - 1), 0);
            char preFlag = pattern.charAt(pre);
            // 如果是两个匹配的情况
            String currStr = arr[i];
            if(flag == preFlag){
                String preStr = source.get(preFlag);
                if(!preStr.equals(currStr)) return false;
            }
            // 如果是的情况
            if(source.containsKey(flag)){
                String preStr = source.get(flag);
                if(!currStr.equals(preStr)) return false;
            }
            if(reverse.containsKey(currStr)){
                char preChar = reverse.get(currStr);
                if(preChar != flag) return false;
            }
            source.put(flag, currStr);
        }

        return true;
    }
}
