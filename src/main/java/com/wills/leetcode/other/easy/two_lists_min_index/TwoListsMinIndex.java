package com.wills.leetcode.other.easy.two_lists_min_index;

import java.util.*;

/**
 * @ClassName TwoListsMinIndex
 * @Date 2022/3/14 09:45
 * @Author 王帅
 * @Version 1.0
 * @Description
 *  两个列表的最小索引总和
 */
public class TwoListsMinIndex {

    public static void main(String[] args) {
        //["Shogun","Tapioca Express","Burger King","KFC"]
        //["KFC","Shogun","Burger King"]
        String[] andy = {"Shogun","Tapioca Express","Burger King","KFC"};
        String[] doris = {"KFC","Shogun","Burger King"};
        String[] restaurant = findRestaurant(andy, doris);
        for (String s : restaurant) {
            System.out.println(s);
        }
    }

    // 暴力法
    public static String[] findRestaurant1(String[] list1, String[] list2) {
        List<String> res = new ArrayList<>();
        int minIndex = Integer.MAX_VALUE;
        for (int i = 0; i < list1.length; i++) {
            for (int j = 0; j < list2.length; j++) {
                if(list1[i].equals(list2[j])){
                    int total = i + j;
                    if(total < minIndex){
                        minIndex = total;
                        res = new ArrayList<>();
                        res.add(list1[i]);
                        continue;
                    }
                    if(total == minIndex){
                        res.add(list1[i]);
                    }
                }
            }
        }
        return res.toArray(new String[res.size()]);
    }

    // 哈希表法
    public static String[] findRestaurant(String[] list1, String[] list2) {
        List<String> res = new ArrayList<>();
        Map<String,Integer> source = new HashMap<>();
        int minIndex = Integer.MAX_VALUE;
        for (int i = 0; i < list1.length; i++) {
            source.put(list1[i],i);
        }
        for (int i = 0; i < list2.length; i++) {
            String s = list2[i];
            Integer index = source.get(s);
            if(index != null){
                int total = index + i;
                if(total < minIndex){
                    res = new ArrayList<>();
                    res.add(s);
                    minIndex = total;
                    continue;
                }
                if(total == minIndex){
                    res.add(s);
                }
            }
        }
        return res.toArray(new String[res.size()]);
    }
}
