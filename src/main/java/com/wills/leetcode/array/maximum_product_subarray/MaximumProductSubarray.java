package com.wills.leetcode.array.maximum_product_subarray;

import org.junit.Test;

/**
 * @ClassName MaximumProductSubarray
 * @Date 2022/7/7 12:04
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class MaximumProductSubarray {

        private static int[] source = {2, 3, -2, 4};
//    private static int[] source = {-2, 0, -1};

    @Test
    public void test() {
        System.out.println(maxProduct(source));
    }

    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE,imax = 1,imin = 1;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] < 0){
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax * nums[i],nums[i]);
            imin = Math.min(imin * nums[i],nums[i]);
            max = Math.max(max,imax);
        }
        return max;
    }

//    public int maxProduct(int[] nums) {
//        if(nums.length == 1) return nums[0];
//        int max = 0;
//        for (int i = 1; i < nums.length; i++) {
//            int tmp = nums[i] * nums[i - 1];
//            max = Math.max(max, tmp);
//        }
//        return max;
//    }
}
