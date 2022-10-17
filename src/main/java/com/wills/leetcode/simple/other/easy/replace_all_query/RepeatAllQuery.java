package com.wills.leetcode.simple.other.easy.replace_all_query;

/**
 * @ClassName RepeatAllQuery
 * @Date 2022/1/5 09:31
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class RepeatAllQuery {

    /***
     * 给你一个仅包含小写英文字母和 '?' 字符的字符串 s，请你将所有的 '?' 转换为若干小写字母，
     * 使最终的字符串不包含任何 连续重复 的字符。
     *
     * 注意：你 不能 修改非 '?' 字符。
     *
     * 题目测试用例保证 除 '?' 字符 之外，不存在连续重复的字符。
     *
     * 在完成所有转换（可能无需转换）后返回最终的字符串。如果有多个解决方案，请返回其中任何一个。
     * 可以证明，在给定的约束条件下，答案总是存在的。
     *
     * 示例 1：
     * 输入：s = "?zs"
     * 输出："azs"
     * 解释：该示例共有 25 种解决方案，从 "azs" 到 "yzs" 都是符合题目要求的。
     * 只有 "z" 是无效的修改，因为字符串 "zzs" 中有连续重复的两个 'z' 。
     *
     * 示例 2：
     * 输入：s = "ubv?w"
     * 输出："ubvaw"
     * 解释：该示例共有 24 种解决方案，只有替换成 "v" 和 "w" 不符合题目要求。
     * 因为 "ubvvw" 和 "ubvww" 都包含连续重复的字符。
     *
     * 示例 3：
     * 输入：s = "j?qg??b"
     * 输出："jaqgacb"
     *
     * 示例 4：
     * 输入：s = "??yw?ipkj?"
     * 输出："acywaipkja"
     * @param args
     */
    public static void main(String[] args) {
        String source = "??yw?ipkj?";
        String res = modifyString(source);
        System.out.println(res);
    }

    public static String modifyString(String s) {
        // 拿到传入的长度，方便后续处理
        int len = s.length();
        // 将字符串转换为字符数组
        char[] sourceArr = s.toCharArray();
        // 遍历这个字符数组
        for (int i = 0; i < len; ++i) {
            // 如果遇到了 ？ 这个字符
            if (sourceArr[i] == '?') {
                // 就循环遍历ASCII码，然后在循环中判断前后是否重复，重复就继续下一次循环，不重复就赋值
                for (char ch = 'a'; ch <= 'z'; ++ch) {
                    if ((i > 0 && sourceArr[i - 1] == ch) || ((i < len - 1) && sourceArr[i + 1] == ch)) {
                        continue;
                    }
                    sourceArr[i] = ch;
                    break;
                }
            }
        }
        // 最终返回目标即可
        return new String(sourceArr);
    }
}
