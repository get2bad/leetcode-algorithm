package com.wills.leetcode.simple.xor.count_triplets_two_arrays_equal_XOR;

public class CountTripletsThatCanFormTwoArraysEqualXOR {

    public static void main(String[] args) {
        int[] source = {2,3,1,6,7};
        System.out.println(countTriplets(source));
    }

    public static int countTriplets2(int[] arr) {
        int res = 0;




        return res;
    }

    public static int countTriplets1(int[] arr) {
        int res = 0;




        return res;
    }

    public static int countTriplets(int[] arr) {
        int ans = 0;
        int n = arr.length;
        int[] sum = new int[n + 1];

        for (int i = 1; i <= n; i++){
            // 拿到 arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1] 从头到尾拿到值
            // arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
            sum[i] = sum[i - 1] ^ arr[i - 1];
        }

        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                for (int k = j; k <= n; k++) {
                    int a = sum[j - 1] ^ sum[i - 1];
                    int b = sum[k] ^ sum[j - 1];
                    if (a == b) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

}
