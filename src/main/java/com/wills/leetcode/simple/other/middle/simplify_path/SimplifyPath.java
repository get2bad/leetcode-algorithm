package com.wills.leetcode.simple.other.middle.simplify_path;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @ClassName SimplifyPath
 * @Date 2022/1/6 09:57
 * @Author 王帅
 * @Version 1.0
 * @Description
 * 简化路径 力扣每日一题
 */
public class SimplifyPath {

    public static void main(String[] args) {
//        String source = "/home//foo/";
//        String source = "/../";
//        String source = "/home/";
//        String source = "/a/./b/../../c/";
        String source = "/a/../../b/../c//.//";
        String res = simplifyPath(source);
        System.out.println(res);
    }

    /**
     * test cases eg:
     * path = /home/ simplify = /home
     * path = /../ simplify = /
     * path = /home//foo/ simplify = /home/foo
     * 🤔思考：
     * 1. 我们可以进行字符串的切割，如果是 非 .. 和 . 就进行入栈元素，如果是 .. 就进行出栈一个元素，如果是.就不做变动
     * @param path
     * @return
     */
    public static String simplifyPath(String path) {
        String[] sourceArr = path.split("/");
        Deque<String> queue = new ArrayDeque<>();
        for (int i = 0; i < sourceArr.length; i++) {
            String current = sourceArr[i];
            if ("..".equals(current)) {
                if (!queue.isEmpty()) {
                    queue.pollLast();
                }
            } else if (current.length() > 0 && !".".equals(current)) {
                queue.offer(current);
            }
        }
        StringBuilder sb = new StringBuilder();
        if (queue.isEmpty()) {
            sb.append('/');
        } else {
            while (!queue.isEmpty()) {
                sb.append('/');
                sb.append(queue.poll());
            }
        }
        return sb.toString();
    }
}
