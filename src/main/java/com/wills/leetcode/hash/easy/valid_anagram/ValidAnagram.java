package com.wills.leetcode.hash.easy.valid_anagram;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ValidAnagram
 * @Date 2022/3/22 09:18
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class ValidAnagram {

    public static void main(String[] args) {
        ValidAnagram anagram = new ValidAnagram();
        boolean res = anagram.isAnagram("test", "sett");
        System.out.println(res);
    }

    public boolean isAnagram(String s, String t) {
        // 边界条件判断
        if(s == null || t == null) return false;
        int sLen = s.length();
        int tLen = t.length();
        if(sLen != tLen) return false;

        Map<Character,Integer> sSource = new HashMap<>();
        for (int i = 0; i < sLen; i++) {
            int cnt = 1;
            Character curr = s.charAt(i);
            if(sSource.containsKey(curr)){
                int preCnt = sSource.get(curr);
                cnt = preCnt + 1;
            }
            sSource.put(curr, cnt);
        }
        for (int i = 0; i < tLen; i++) {
            Character curr = t.charAt(i);
            if(sSource.containsKey(curr)){
                int preCnt = sSource.get(curr);
                preCnt -= 1;
                if(preCnt != 0) sSource.put(curr,preCnt);
                else sSource.remove(curr);
            }else{
                return false;
            }
        }
        return sSource.size() == 0;
    }

    public boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }
}
