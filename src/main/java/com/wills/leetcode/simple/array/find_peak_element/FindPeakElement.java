package com.wills.leetcode.simple.array.find_peak_element;

import org.junit.Test;

/**
 * @ClassName FindPeakElement
 * @Date 2022/7/11 15:05
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class FindPeakElement {

    private static int[] source = {1, 2, 3, 1};

    @Test
    public void test() {
        System.out.println(findPeakElement(source));
    }

    public int findPeakElement(int[] nums) {
        int len = nums.length,left = 0, right = len - 1;
        while(left < right){
            int middle = (left + right) >> 1;
            if(nums[middle] > nums[middle + 1]){
                right = middle;
            }else{
                left = middle + 1;
            }
        }
        return right;
    }
}
