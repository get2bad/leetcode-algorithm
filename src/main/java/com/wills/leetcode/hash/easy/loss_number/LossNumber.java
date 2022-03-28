package com.wills.leetcode.hash.easy.loss_number;

import java.util.Arrays;

/**
 * @ClassName LossNumber
 * @Date 2022/3/24 11:01
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class LossNumber {

    public static void main(String[] args) {
        LossNumber obj = new LossNumber();
        int[] source = {0,1};
        int res = obj.missingNumber(source);
        System.out.println(res);
    }

    /**
     * 位运算法
     * 找缺失数、找出现一次数都是异或的经典应用。
     * 解析：
     * 我们可以先求得 [1, n][1,n] 的异或和 ans，然后用 res 对各个 nums[i] 进行异或。
     * 这样最终得到的异或和表达式中，只有缺失元素出现次数为 1 次，其余元素均出现两次（x⊕x=0），
     * 即最终答案 res 为缺失元素。
     */
    public int missingNumber2(int[] nums) {
        int len = nums.length;
        int res = 0;
        for (int i = 0; i <= len; i++) res ^= i;
        for (int num : nums)  res ^= num;

        return res;
    }

    // 排序法
    public int missingNumber1(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            if(nums[i] != i) return i;
        }
        return len;
    }

    // 递归
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        return loss(1,nums);
    }

    public int loss(int index,int[] nums){
        int len = nums.length;
        if(len - index < 1) return nums[len - 1] + 1;
        int pre = nums[index - 1];
        int curr = nums[index];
        if(curr - pre == 1){
            return loss(index+1, nums);
        }else{
            return curr - 1;
        }
    }
}
