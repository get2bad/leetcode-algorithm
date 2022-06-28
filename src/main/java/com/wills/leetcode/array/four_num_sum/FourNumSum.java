package com.wills.leetcode.array.four_num_sum;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName FourNumSum
 * @Date 2022/6/20 14:14
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class FourNumSum {

    int[] source = new int[]{1, 0, -1, 0, -2, 2};
    int target = 0;

//    int[] source = new int[]{2, 2, 2, 2, 2};
//    int target = 8;

//    int[] source = new int[]{1000000000, 1000000000, 1000000000, 1000000000};
//    int target = -294967296;

    @Test
    public void test() {
        System.out.println(fourSum(source, target));
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        int len = nums.length;
        if (len < 4) return Collections.EMPTY_LIST;
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < len - 3; i++) {
            // 相同的直接跳过
            if(i > 0 && nums[i] == nums[i-1])   continue;
            for (int j = i + 1; j < len - 2; j++) {
                // 跟上面一样去重
                if(j > i + 1 && nums[j] == nums[j-1])   continue;
                int left = j + 1, right = len - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        List<Integer> content = Arrays.asList(nums[i], nums[j], nums[left], nums[right]);
                        if (!res.contains(content)) res.add(content);
                        // 进行跳过相同的元素
                        while (left < right && nums[left] == nums[left + 1]) ++left;
                        while (left < right && nums[right] == nums[right - 1]) --right;
                        // 过滤重复元素后，要将指针进行位移，因为目前左右指针依旧指向之前/重复的元素
                        ++left;
                        --right;
                    } else if (sum > target) right--;
                    else left++;
                }
            }
        }
        return res;
    }
}
