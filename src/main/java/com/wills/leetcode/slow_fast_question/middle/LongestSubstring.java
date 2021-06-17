package com.wills.leetcode.slow_fast_question.middle;

import java.util.HashMap;
import java.util.Map;

/**
 * 无重复字符的最长子串
 */
public class LongestSubstring {

    public static void main(String[] args) {
        String souce = "pwwkew";
        System.out.println(lengthOfLongestSubstring1(souce));
    }

    public static int lengthOfLongestSubstring1(String s) {
        if(s.length() == 0) return 0;
        Map<Character,Integer> source = new HashMap<>();
        int max = 0,slow = 0;
        for (int fast = 0; fast < s.length(); fast++) {
            if(source.containsKey(s.charAt(fast))){
                // 如果有，就调整慢指针到当前遍历元素的下一个指针位置
                slow = Math.max(slow,source.get(s.charAt(fast)) + 1);
            }
            // 将当前遍历的元素其添加进去
            source.put(s.charAt(fast),fast);
            max = Math.max(max,fast - slow + 1);
        }
        return max;
    }

    public static int lengthOfLongestSubstring(String s) {
        if(s.trim().length() == 0) return 0;
        int res = 0,slow = 0;
        char pre = ' ';
        for (int fast = 1; fast < s.length(); fast++) {
            if(s.charAt(slow) == s.charAt(fast) && s.charAt(slow) != pre){
                // 如果相等
                if(isSubString(s,slow,fast)){
                    res += fast - slow;
                    slow = fast;
                    pre = s.charAt(fast);
                }
            }
        }
        return res;
    }

    public static boolean isSubString(String s,int start,int end){
        int source = end;
        while(start < source){
            if(s.charAt(start++) != s.charAt(end ++)){
                return false;
            }
        }
        return true;
    }
}
