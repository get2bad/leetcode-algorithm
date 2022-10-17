package com.wills.leetcode.simple.back_track.parenthesis_generate;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BracketGenerate
 * @Date 2022/7/18 14:14
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class ParenthesisGenerate {

    private static final int n = 3;

    @Test
    public void test() {
        System.out.println(generateParenthesis(n));
    }

    // 尝试使用回溯算法
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generate(res, new StringBuilder(), 0, 0, n);
        return res;
    }


    private void generate(List<String> res, StringBuilder source, int open, int close, int n) {
        if (source.length() == n * 2) {
            res.add(source.toString());
            return;
        }
        // 否则就进行追加
        if (open < n) {
            source.append("(");
            generate(res, source, open + 1, close, n);
            source.deleteCharAt(source.length() - 1);
        }

        if (close < open) {
            source.append(")");
            generate(res, source, open, close + 1, n);
            source.deleteCharAt(source.length() - 1);
        }
    }
}
