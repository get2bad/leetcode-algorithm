package com.wills.leetcode.top100.Q9_phone_number_combination;

import org.junit.Test;

import java.util.*;

/**
 * @ClassName PhoneNumberCombination
 * @Date 2022/10/20 09:30
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class PhoneNumberCombination {

    private static final String source = "23";

    @Test
    public void test() {
        System.out.println(letterCombinations(source));
    }

    /**
     * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * <p>
     * 示例 1：
     * 输入：digits = "23"
     * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
     * <p>
     * 示例 2：
     * 输入：digits = ""
     * 输出：[]
     * <p>
     * 示例 3：
     * 输入：digits = "2"
     * 输出：["a","b","c"]
     * <p>
     * 回溯算法？
     * 使用回溯算法可以完美解决本问题！因为需要返回去重新遍历，所以回溯算法可以很好符合本题需要
     * 回溯算法步骤：
     * 1. 画递归树，找到状态变量
     * 2. 根据题意，确立结束条件
     * 3. 找准选择列表与第一步紧密关联
     * 4. 判断是否要剪枝
     * 5. 做出选择，递归调用，进入下一层
     * 6. 撤销选择(removeLast)
     */
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<String>();
        if (digits == null || digits.length() == 0) return res;
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        generate(res, phoneMap, digits, 0, new StringBuilder());

        return res;
    }

    public void generate(List<String> res, Map<Character, String> phoneMap, String digits, Integer index, StringBuilder sb) {
        // 如果长度符合返回的条件，就添加到返回集合中
        if (index == digits.length()) res.add(sb.toString());
        else {
            // 否则就进行相关的回溯处理
            char c = digits.charAt(index);
            String letters = phoneMap.get(c);
            for (int i = 0; i < letters.length(); i++) {
                sb.append(letters.charAt(i));
                generate(res, phoneMap, digits, index + 1, sb);
                sb.deleteCharAt(index);
            }
        }
    }

}
