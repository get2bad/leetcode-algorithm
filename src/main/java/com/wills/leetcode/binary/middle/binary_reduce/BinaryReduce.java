package com.wills.leetcode.binary.middle.binary_reduce;

/**
 * @author 王帅
 * @ClassName binary_reduce
 * @date 2021/5/28 10:59
 * @Version 1.0
 * @Description
 */
public class BinaryReduce {

    public static void main(String[] args) {
        int[] resource = {2, 14, 4};
        System.out.println(totalHammingDistance1(resource));
    }

    public static int totalHammingDistance1(int[] nums) {
        int ans = 0;
        for (int x = 31; x >= 0; x--) {
            int s0 = 0, s1 = 0;
            for (int u : nums) {
                if (((u >> x) & 1) == 1) {
                    // 如果是32位数字当前位数和 1 进行 与运算是1 ，那么 s1 + 1
                    s1++;
                } else {
                    // 如果是32位数字当前位数和 1 进行 与运算是0 ，那么 s0 + 1
                    s0++;
                }
            }
            // 要求得「第 xx 位所有不同数」的对数的个数，只需要应用乘法原理，将两者元素个数相乘即可
            ans += s0 * s1;
        }
        return ans;
    }

    public static int totalHammingDistance(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                res += getHamm(nums[i], nums[j]);
            }
        }
        return res;
    }

    private static int getHamm(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}
