package com.wills.leetcode.top100.Q13_generate_partentheses;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName GeneratePartentheses
 * @Date 2022/10/21 10:45
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class GeneratePartentheses {

    private static final Integer source = 3;

    @Test
    public void test() {
        System.out.println(generateParenthesis(source));
    }

    /**
     * 数字 n代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * 示例 1：
     * 输入：n = 3
     * 输出：["((()))","(()())","(())()","()(())","()()()"]
     * <p>
     * 示例 2：
     * 输入：n = 1
     * 输出：["()"]
     *
     * 使用回溯算法即可解决本题！
     * 因为涉及到回退的操作，所以回溯是最适合本题的！关键点就是回溯方法的参数
     * List<String> res 返回的值
     * int n 规定的总括号个数
     * StringBuilder sb 临时传递的字符串
     * int open ☆ 括号开始的标志数 ☆
     * int close ☆ 括号关闭的标志数 ☆
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generate(res, n, new StringBuilder(), 0, 0);
        return res;
    }

    private void generate(List<String> res, int n, StringBuilder sb, int open, int close) {
        if (sb.length() == n * 2) {
            res.add(sb.toString());
            return;
        }
        if (open < n) {
            sb.append("(");
            generate(res, n, sb, open + 1, close);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (close < open) {
            sb.append(")");
            generate(res, n, sb, open, close + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }


}
