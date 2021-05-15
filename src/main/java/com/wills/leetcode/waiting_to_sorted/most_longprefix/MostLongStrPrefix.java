package com.wills.leetcode.waiting_to_sorted.most_longprefix;

/**
 * @author 王帅
 * @date 2021-02-01 09:22:40
 * @description:
 * 最长公共前缀
 */
public class MostLongStrPrefix {

    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(strs));
    }

    public static String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0){
            return "";
        }
        String res = strs[0];
        for (String str : strs) {
            while(!str.startsWith(res)){
                if(res.length() == 0) return "";
                res = res.substring(0,res.length() - 1);
            }
        }
        return res;
    }
}
