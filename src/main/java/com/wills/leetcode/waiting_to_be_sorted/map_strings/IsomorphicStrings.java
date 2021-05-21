package com.wills.leetcode.waiting_to_be_sorted.map_strings;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 王帅
 * @date 2021-03-22 09:58:58
 * @description:
 * 同构字符串
 */
public class IsomorphicStrings {

    /**
     * 给定两个字符串 s 和 t，判断它们是否是同构的。
     * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
     * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。
     * 不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
     *
     * 示例 1:
     * 输入：s = "egg", t = "add"
     * 输出：true
     *
     * 示例 2：
     * 输入：s = "foo", t = "bar"
     * 输出：false
     *
     * 示例 3：
     * 输入：s = "paper", t = "title"
     * 输出：true
     */
    public static void main(String[] args) {
        String s = "egg";
        String t = "add";
        System.out.println(isIsomorphic1(s, t));
    }

    /**
     * 哈希表 存储映射关系
     */
    public static boolean isIsomorphic1(String s, String t) {
        if(s.length() != t.length()) return false;
        char[] pre = s.toCharArray();
        char[] after = t.toCharArray();
        Map<Character,Character> s1 = new HashMap<>();
        Map<Character,Character> t1 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if ((s1.containsKey(pre[i]) && s1.get(pre[i]) != after[i])
                    || (t1.containsKey(after[i]) && t1.get(after[i]) != pre[i])){
                return false;
            }
            s1.put(pre[i],after[i]);
            t1.put(after[i],pre[i]);
        }
        return true;
    }

    /**
     * ASCII 码运算
     */
    public static boolean isIsomorphic(String s, String t) {
        if(s.length() != t.length()) return false;
        Integer tmp = Integer.MAX_VALUE;
        for (int i = 0; i < s.length(); i++) {
            char[] pre = s.toCharArray();
            char[] after = t.toCharArray();
            int source = pre[i] - after[i];
            if(tmp == Integer.MAX_VALUE){
                tmp = source;
            }
            if(source != tmp){
                return false;
            }
        }
        return true;
    }
}
