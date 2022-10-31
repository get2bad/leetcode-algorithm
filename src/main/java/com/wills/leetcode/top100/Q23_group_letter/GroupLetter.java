package com.wills.leetcode.top100.Q23_group_letter;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName GroupAnagrams
 * @Date 2022/10/31 13:49
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class GroupLetter {

    private static final String[] source = {"eat", "tea", "tan", "ate", "nat", "bat"};

    @Test
    public void test() {
        System.out.println(groupAnagrams(source));
    }

    /**
     * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
     * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
     * <p>
     * 示例 1:
     * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
     * <p>
     * 示例 2:
     * 输入: strs = [""]
     * 输出: [[""]]
     * <p>
     * 示例 3:
     * 输入: strs = ["a"]
     * 输出: [["a"]]
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String s:strs){
            char[] ch=s.toCharArray();
            Arrays.sort(ch);
            String key=String.valueOf(ch);
            if(!map.containsKey(key))    map.put(key,new ArrayList<>());
            map.get(key).add(s);
        }

        return new ArrayList<>(map.values());
    }

}
