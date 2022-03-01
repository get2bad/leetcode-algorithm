package com.wills.leetcode.other.middle.zigzag_conversion;

/**
 * @ClassName ZigZagConversion
 * @Date 2022/3/1 11:14
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class ZigZagConversion {

    public static void main(String[] args) {
        String convert = convert("PAYPALISHIRING", 3);
        System.out.println(convert);
    }

    /**
     * 思路：
     *  题目要求中要构成Z字形，最后发现效果是N字形，然后我发现这道题完全可以进行找规律
     *  将横的转换为竖的，就是每次 +1 +1 +1，到头以后就进行 -1 -1 -1即可，所以我们使用
     *  StringBuilder就行（因为是同步的，速度更快，本题不存在多线程问题，就直接使用这个快的）
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {
        // 如果是构不成 一个完整的Z就直接原样返回即可
        if(s == null || s.length() == 0 || numRows <= 1) return s;
        StringBuilder[] sbs = new StringBuilder[numRows];
        // 对象实例初始化
        for (int i = 0; i < sbs.length; i++) {
            sbs[i] = new StringBuilder();
        }
        int index = 0,curr = 1;
        for (char source : s.toCharArray()) {
            // 将内容塞进StringBuilder中
            sbs[index].append(source);
            // 进行 +1 +1 +1 -1 -1 -1
            index += curr;
            // 如果到了边界，就进行取负，方便进行 +1 +1 +1 -1 -1 -1处理
            if(index == numRows - 1 || index == 0) curr = -curr;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder sb : sbs) {
            res.append(sb.toString());
        }
        return res.toString();
    }
}
