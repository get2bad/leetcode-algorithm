package com.wills.leetcode.simple.hash.easy.two_array_intersection;

import java.util.*;

/**
 * @ClassName TwoArrayIntersection
 * @Date 2022/3/24 10:30
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class TwoArrayIntersection {

    public static void main(String[] args) {
        TwoArrayIntersection obj = new TwoArrayIntersection();
        int[] num1 = {2,1};
        int[] num2 = {1,1};
        int[] res = obj.intersection1(num1, num2);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
    // java集合的方式
    public int[] intersection1(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        Set<Integer> list2 = new HashSet<>();
        for (int i : nums1) {
            list.add(i);
        }
        for (int i : nums2) {
            list2.add(i);
        }
        list.retainAll(list2);
        return list.stream().mapToInt(i -> i).toArray();
    }

    // 暴力法
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> res = new HashSet<>();
        List<Integer> source = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            source.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if(source.contains(nums2[i])){
                res.add(nums2[i]);
            }
        }
        int[] resArray = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            resArray[i] = new ArrayList<>(res).get(i);
        }
        return resArray;
    }
}
