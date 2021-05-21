package com.wills.leetcode.waiting_to_be_sorted.count_Say;

/**
 * 给定一个正整数 n ，输出外观数列的第 n 项。
 * <p>
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
 * <p>
 * 你可以将其视作是由递归公式定义的数字字符串序列：
 * countAndSay(1) = "1"
 * countAndSay(n) 是对 countAndSay(n-1) 的描述，然后转换成另一个数字字符串。
 * 前五项如下：
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 第一项是数字 1
 * 描述前一项，这个数是 1 即 “ 一 个 1 ”，记作 "11"
 * 描述前一项，这个数是 11 即 “ 二 个 1 ” ，记作 "21"
 * 描述前一项，这个数是 21 即 “ 一 个 2 + 一 个 1 ” ，记作 "1211"
 * 描述前一项，这个数是 1211 即 “ 一 个 1 + 一 个 2 + 二 个 1 ” ，记作 "111221"
 * 要 描述 一个数字字符串，首先要将字符串分割为 最小 数量的组，每个组都由连续的最多 相同字符 组成。
 * 然后对于每个组，先描述字符的数量，然后描述字符，形成一个描述组。要将描述转换为数字字符串，
 * 先将每组中的字符数量用数字替换，再将所有描述组连接起来。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 1
 * 输出："1"
 * 解释：这是一个基本样例。
 * <p>
 * 示例 2：
 * 输入：n = 4
 * 输出："1211"
 * 解释：
 * countAndSay(1) = "1"
 * countAndSay(2) = 读 "1" = 一 个 1 = "11"
 * countAndSay(3) = 读 "11" = 二 个 1 = "21"
 * countAndSay(4) = 读 "21" = 一 个 2 + 一 个 1 = "12" + "11" = "1211"
 * <p>
 * 注意：
 * 1 <= n <= 30
 */
public class CountAndSay {

    public static void main(String[] args) {
        int target = 4;
        System.out.println(countAndSay(target));
    }

    /**
     * 要想实现输入与输出 所以本方法必须是一个递归方法
     * 应该是从n开始遍历，遍历到1
     *
     * @param n
     * @return
     */
    public static String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        // 递归调用
        String source = countAndSay(n - 1);
        StringBuilder sb = new StringBuilder();
        // 在这里写出来计数的字符串
        // 。。。。。
        // 思路： 将返回的String遍历进行计数
        int start = 0;
        for (int end = 1; end < source.length(); end++) {
            // 进行计数，如果这个数与上一个不同，就中断计数重新开始 start 重置为 end所在的索引
            if (source.charAt(end) != source.charAt(start)) {
                sb.append(end - start).append(source.charAt(start));
                start = end;
            }
        }
        // 处理字符串的最后一个数字
        sb.append(source.length() - start).append(source.charAt(start));
        return sb.toString();
    }
}
