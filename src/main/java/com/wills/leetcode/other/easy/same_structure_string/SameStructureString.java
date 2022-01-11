package com.wills.leetcode.other.easy.same_structure_string;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName SameStructureString
 * @Date 2022/1/11 13:42
 * @Author 王帅
 * @Version 1.0
 * @Description
 * 同构字符串
 */
public class SameStructureString {

    public static void main(String[] args) {
        String source = "foo";
        String sourceNew = "bar";
        boolean flag = isIsomorphic(source, sourceNew);
        System.out.println(flag);
    }

    /**
     * 题目的关键：
     * 就是使用哈希表，对建立映射关系的对象建立映射关系，如果下次遍历的跟映射关系对应不上，就直接返回false否则就是true
     * @param s
     * @param t
     * @return
     */
    public static boolean isIsomorphic(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }

        Map<Character, Character> map = new HashMap<>();
        for(int i=0; i<s.length(); i++){
            if(!map.containsKey(s.charAt(i))){
                if(map.containsValue(t.charAt(i))){
                    return false;
                }
                map.put(s.charAt(i), t.charAt(i));
            }else{
                if(map.get(s.charAt(i))!=t.charAt(i)){
                    return false;
                }
            }
        }

        return true;
    }
}
