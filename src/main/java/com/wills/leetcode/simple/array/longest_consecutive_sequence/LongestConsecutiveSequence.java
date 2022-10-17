package com.wills.leetcode.simple.array.longest_consecutive_sequence;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName LongestConsecutiveSequence
 * @Date 2022/7/4 17:36
 * @Author 王帅
 * @Version 1.0
 * @Description 最长连续数列
 */
public class LongestConsecutiveSequence {

    private static int[] source = {100, 4, 200, 1, 3, 2};

    @Test
    public void test() {
        System.out.println(longestConsecutive(source));
    }

    // 核心思想，分别向左向右搜索，直到删除不了为止，然后取 当前值和递增/减值的差值
    public int longestConsecutive(int[] nums) {
        Set<Integer> numsSet = new HashSet<>();
        for (Integer num : nums) {
            numsSet.add(num);
        }
        int longest = 0;
        for (Integer num : nums) {
            if (numsSet.remove(num)) {
                // 向当前元素的左边搜索,eg: 当前为100, 搜索：99，98，97,...
                int currentLongest = 1;
                int current = num;
                while (numsSet.remove(current - 1)) current--;
                currentLongest += (num - current);
                // 向当前元素的右边搜索,eg: 当前为100, 搜索：101，102，103,...
                current = num;
                while (numsSet.remove(current + 1)) current++;
                currentLongest += (current - num);
                // 搜索完后更新longest.
                longest = Math.max(longest, currentLongest);
            }
        }
        return longest;
    }
}
