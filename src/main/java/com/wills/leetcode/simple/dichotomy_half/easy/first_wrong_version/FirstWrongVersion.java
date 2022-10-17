package com.wills.leetcode.simple.dichotomy_half.easy.first_wrong_version;

/**
 * @author 王帅
 * @ClassName FirstWrongVersion
 * @date 2021/6/18 10:51
 * @Version 1.0
 * @Description
 */
public class FirstWrongVersion {

    public static void main(String[] args) {
        int source = 20;

        System.out.println(firstBadVersion(source));
    }

    public static int firstBadVersion(int n) {
        int left = 1, right = n;
        while (left < right) { // 循环直至区间左右端点相同
            int mid = left + (right - left) / 2; // 防止计算时溢出
            if (isBadVersion(mid)) {
                right = mid; // 答案在区间 [left, mid] 中
            } else {
                left = mid + 1; // 答案在区间 [mid+1, right] 中
            }
        }
        // 此时有 left == right，区间缩为一个点，即为答案
        return left;
    }

    public static boolean isBadVersion(int version){
        int wrongVersion = 5;
        if(version >= wrongVersion) return true;
        else return false;
    }
}
