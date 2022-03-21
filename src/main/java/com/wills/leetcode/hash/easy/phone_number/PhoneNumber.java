package com.wills.leetcode.hash.easy.phone_number;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PhoneNumber
 * @Date 2022/3/21 09:31
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class PhoneNumber {

    public static void main(String[] args) {
        PhoneNumber obj = new PhoneNumber();
        List<String> res = obj.letterCombinations("23");
        System.out.println(String.valueOf(res));
    }

    /**
     * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     *
     * 示例 1：
     * 输入：digits = "23"
     * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
     *
     * 示例 2：
     * 输入：digits = ""
     * 输出：[]
     *
     * 示例 3：
     * 输入：digits = "2"
     * 输出：["a","b","c"]
     */
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if(digits == null || digits.trim().length() == 0) return res;
        // 初始化值
        Map<Character,String> source = new HashMap<Character,String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(res, source, digits, 0, new StringBuilder());
        return res;
    }

    /**
     * 主要的思路就是：
     * 使用递归的方法，如果当前的按键是2，那么可能的字母就是 abc，然后依次遍历，然后进行方法递归，找到第二个按键的值3，进行一次循环复制def
     * 大致就是
     * a -> 递归(def) -> ad ae af -> 递归(假设有 hig) -> adh adi adg aeh aei aeg afh afi afh
     * b -> 递归(def) -> bd be bf
     * c -> 递归(def) -> cd ce cf
     * 最后合并起来，就是结果
     */
    public void backtrack(List<String> res,
                          Map<Character, String> source,
                          String digits,
                          int index,
                          StringBuilder combination) {
        if (index == digits.length()) {
            res.add(combination.toString());
        } else {
            // 找到当前的数字
            char digit = digits.charAt(index);
            // 获取当前数字存储的字母
            String letters = source.get(digit);
            int lettersLen = letters.length();
            for (int i = 0; i < lettersLen; i++) {
                combination.append(letters.charAt(i));
                backtrack(res, source, digits, index+1, combination);
                combination.deleteCharAt(index);
            }
        }
    }
}
