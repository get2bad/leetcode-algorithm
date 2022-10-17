package com.wills.leetcode.simple.hash.middle.group_anagrams;

import java.util.*;

/**
 * @author 王帅
 * @date 2022-03-28 21:50:47
 * @description:
 */
public class GroupAnagrams {

    public static void main(String[] args) {
        String[] source = {"cab","tin","pew","duh","may","ill","buy","bar","max","doc"};
        List<List<String>> res = new GroupAnagrams().groupAnagrams(source);
        System.out.println(res);
    }

    // 这里用到了 数组的排序，然后拿到排序后的数组转换成字符串 去判断map中是否有，有的话就添加，否则就初始化然后添加
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,ArrayList<String>> map=new HashMap<>();
        for(String s:strs){
            char[] ch=s.toCharArray();
            Arrays.sort(ch);
            String key=String.valueOf(ch);
            if(!map.containsKey(key)) map.put(key,new ArrayList<>());
            map.get(key).add(s);
        }
        return new ArrayList(map.values());
    }
}
