package com.wills.leetcode.simple.dynamic_algorithm.middle.expression_design;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ExpressionDesign
 * @Date 2022/7/29 09:40
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class ExpressionDesign {

    //    private static final String source = "2-1-1";
    private static final String source = "2*3-4*5";

    @Test
    public void test() {
        System.out.println(diffWaysToCompute(source));
    }

    char[] cs;

    public List<Integer> diffWaysToCompute(String s) {
        cs = s.toCharArray();
        return dfs(0, cs.length - 1);
    }

    List<Integer> dfs(int left, int right) {
        List<Integer> res = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            // 如果遍历到了数字 就跳过，因为遍历到的要为运算符才可以进行相关的计算
            if (cs[i] >= '0' && cs[i] <= '9') continue;
            // ☆☆☆重点☆☆☆  将式子分为左右两部分
            // 递归运算符的左边 dfs(l, i-1) 拿到左边所有的结果
            // 递归运算符右边 dfs(i+1, r) 拿到右边的所有结果
            List<Integer> l1 = dfs(left, i - 1), l2 = dfs(i + 1, right);
            for (int a : l1) {
                for (int b : l2) {
                    int cur;
                    if (cs[i] == '+') cur = a + b;
                    else if (cs[i] == '-') cur = a - b;
                    else cur = a * b;
                    res.add(cur);
                }
            }
        }
        // 如果是第一次递归，就是空的，所以就要将第一个结果放入res中当作后面的计算因子
        // 每一个数字位的本位计算
        if (res.isEmpty()) {
            int cur = 0;
            for (int i = left; i <= right; i++) cur = cur * 10 + (cs[i] - '0');
            res.add(cur);
        }
        return res;
    }
}
