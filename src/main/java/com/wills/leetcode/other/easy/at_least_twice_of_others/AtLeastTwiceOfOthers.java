package com.wills.leetcode.other.easy.at_least_twice_of_others;

/**
 * @ClassName AtLeastTwiceOfOthers
 * @Date 2022/1/13 09:27
 * @Author 王帅
 * @Version 1.0
 * @Description
 * 至少是数组其他元素的两倍大
 */
public class AtLeastTwiceOfOthers {

    public static void main(String[] args) {
//        int[] source = {3,6,1,0};
        int[] source = {0,0,0,1};
        int res = dominantIndex(source);
        System.out.println(res);
    }

    public static int dominantIndex(int[] nums) {
        int maxNum = 0;
        int index = 0;
        int len = nums.length;
        if(len == 1) return 0;
        // 找到最大值
        for (int i = 0; i < len; i++) {
            if(maxNum < nums[i]){
                maxNum = nums[i];
                index = i;
            }
        }
        for (int i = 0; i < len; i++) {
            int currentNum = nums[i];
            if(currentNum != maxNum && currentNum * 2 > maxNum){
                return -1;
            }
            if(index != i && maxNum == currentNum){
                return -1;
            }
        }

        return index;
    }
}
