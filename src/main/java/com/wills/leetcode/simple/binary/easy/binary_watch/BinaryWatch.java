package com.wills.leetcode.simple.binary.easy.binary_watch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 王帅
 * @ClassName BinaryWatch
 * @date 2021/6/21 18:38
 * @Version 1.0
 * @Description
 */
public class BinaryWatch {

    public static void main(String[] args) {
        int target = 1;
        readBinaryWatch(target).forEach(System.out::println);
    }

    public static List<String> readBinaryWatch1(int turnedOn) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 1024; ++i) {
            int h = i >> 6, m = i & 63; // 用位运算取出高 4 位和低 6 位
            if (h < 12 && m < 60 && Integer.bitCount(i) == turnedOn) {
                ans.add(h + ":" + (m < 10 ? "0" : "") + m);
            }
        }
        return ans;
    }

    /**
     * 因为传入一个数字，直接输出可能的二进制时间
     */
    public static List<String> readBinaryWatch(int turnedOn) {
        List<String> res = new ArrayList<>();
        if(turnedOn >= 8) return Arrays.asList();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                if(getOneCnt(i) + getOneCnt(j) == turnedOn){
                    res.add(i + ":" + (j < 10? "0"+j : j));
                }
            }
        }
        return res;
    }

    public static int getOneCnt(Integer target){
        int res = 0;
        while (target != 0) {
            target = target & (target - 1);
            res++;
        }
        return res;
    }
}
