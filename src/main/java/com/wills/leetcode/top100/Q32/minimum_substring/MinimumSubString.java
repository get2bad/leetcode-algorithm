package com.wills.leetcode.top100.Q32.minimum_substring;

import org.junit.Test;

/**
 * @ClassName MinimumSubString
 * @Date 2022/11/4 10:10
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class MinimumSubString {

    private static final String source = "ADOBECODEBANC";
    private static final String target = "ABC";

    @Test
    public void test() {
        System.out.println(minWindow(source, target));
    }

    /**
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 ""
     * 注意：
     * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
     * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
     * <p>
     * 示例 1：
     * 输入：s = "ADOBECODEBANC", t = "ABC"
     * 输出："BANC"
     * <p>
     * 示例 2：
     * 输入：s = "a", t = "a"
     * 输出："a"
     * <p>
     * 示例 3:
     * 输入: s = "a", t = "aa"
     * 输出: ""
     * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
     * 因此没有符合条件的子字符串，返回空字符串。
     *
     * 使用 滑动窗口算法
     * 主要思路：
     * 1. 声明两个数组，128的容量(目的是存储 两个字符串 具体位置的字符个数，方便后续处理)
     * 2. 声明 4个变量，分别为：
     *      a. left 窗口开始的左端 左指针
     *      b. right 窗口开始的右端 右指针
     *      c. minLen 最小窗口的大小，方便最后的 subString
     *      d. count 计算 source中小窗口出现target内容的个数，如果 count == t.length() 说明到达了可以进行内部遍历的标准
     *      e. start 窗口的起始位置 方便最后的 subString
     * 3. 进行遍历 while(right < s.length())
     * 4. 遍历的内部 就是查看 targetArr[sChar] 是否等于0 ，如果是，则说明当前遍历的字符不在target中，就要继续向右扩大窗口，继续遍历
     * 5. 遍历的内部 如果 sourceArr[sChar] < targetArr[sChar] 说明当前字符在target字符串中，并且出现的个数小于target的个数就要 count++
     * 6. sourceArr[sChar]++ 源字符串数组的特定位置出现的个数+1 right++ 向右扩大窗口
     * 7. 如果满足上述的 count = t.length() 说明当前窗口满足了 target要求出现的字符，就要开启内部窗口缩小流程遍历
     * 8. 如果 right - left < min (右指针索引 - 左指针索引 小于 上次最小的值) 就要更新最小值
     *     a. min = right - left / start = left
     * 9. 开始进行缩小窗口的操作，拿到左窗口的字符，如果左窗口的字符在targetArr中是0，要进行left++ 缩小窗口的操作
     * 10. 如果当前遍历的做窗口值正好等于targetArr中的值，说明缩小左窗口就会不满足要求，所以要 count--，让其进行右窗口扩充
     * 11. 上述两个假设都不成立，就继续缩小左窗口 sourceArr[leftChar]-- / left++
     */
    public String minWindow(String s, String t) {
        if (s == null || "".equals(s) || t == null || "".equals(t) || s.length() < t.length()) return "";

        //维护两个数组，记录已有字符串指定字符的出现次数，和目标字符串指定字符的出现次数
        //ASCII表总长128
        int[] targetArr = new int[128];
        int[] sourceArr = new int[128];

        //将目标字符串指定字符的出现次数记录
        for (int i = 0; i < t.length(); i++) targetArr[t.charAt(i)]++;

        //分别为左指针，右指针，最小长度(初始值为一定不可达到的长度)
        int left = 0, right = 0, min = s.length() + 1;
        //已有字符串中目标字符串指定字符的出现总频次 最小覆盖子串在原字符串中的起始位置
        int count = 0, start = 0;
        while (right < s.length()) {
            char sChar = s.charAt(right);
            //说明该字符不被目标字符串需要, 向右继续扩大遍历的窗口，直到有符合条件的出现为止
            if (targetArr[sChar] == 0) {
                right++;
                continue;
            }
            // 如果source字符串出现的字符个数 小于 target内部的个数时，说明遇到了符合条件的，要进行 匹配个数 + 1
            if (sourceArr[sChar] < targetArr[sChar]) count++;

            //已有字符串中目标字符出现的次数+1
            sourceArr[sChar]++;
            //移动右指针,增大窗口
            right++;
            // 当前窗口内的字符串满足 target 的字符串的字符，就开启窗口内的遍历，寻找最小窗口
            while (count == t.length()) {
                //当窗口的长度比已有的最短值小时，更改最小值，并记录起始位置
                if (right - left < min) {
                    min = right - left;
                    start = left;
                }
                char leftChar = s.charAt(left);
                // 遇到不是 target 字符串中的字符，直接缩小窗口，进行下一次循环
                if (targetArr[leftChar] == 0) {
                    left++;
                    continue;
                }
                //如果左边即将要去掉的字符被目标字符串需要，且出现的频次正好等于指定频次，那么如果去掉了这个字符，
                //就不满足覆盖子串的条件，此时要破坏循环条件跳出循环，即控制目标字符串指定字符的出现总频次(count）-1
                if (sourceArr[leftChar] == targetArr[leftChar]) count--;
                //已有字符串中目标字符出现的次数-1
                sourceArr[leftChar]--;
                //移动左指针
                left++;
            }
        }
        //如果最小长度还为初始值，说明没有符合条件的子串
        if (min == s.length() + 1) return "";
        //返回的为以记录的起始位置为起点，记录的最短长度为距离的指定字符串中截取的子串
        return s.substring(start, start + min);
    }
}
