package com.wills.leetcode.array.sort_array;

import com.wills.leetcode.array.sort_array.source.Sort;
import com.wills.leetcode.array.sort_array.source.impl.BubbleSort;
import com.wills.leetcode.array.sort_array.source.impl.InsertionSort;
import com.wills.leetcode.array.sort_array.source.impl.MergeSort;
import com.wills.leetcode.array.sort_array.source.impl.QuickSort;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author 王帅
 * @date 2022-04-07 22:23:56
 * @description:
 */
public class Main {

    private static final int[] source = {1,4,6,6,32,1,6,8,9,2};
    private static Sort obj;

    @Test
    public void bubbleSort(){
        System.out.println("===============================bubbleSort===============================");
        obj = new BubbleSort();
        int[] res = obj.sort(source);
        Arrays.stream(res).forEach(System.out::println);
    }

    @Test
    public void insertionSort(){
        System.out.println("=============================insertionSort=================================");
        obj = new InsertionSort();
        int[] res = obj.sort(source);
        Arrays.stream(res).forEach(System.out::println);
    }

    @Test
    public void insertionSortTest(){
        System.out.println("=============================insertionSortTest=================================");
        int[] nums = source;
        int len = source.length;
        for (int i = 1; i < len; i++) {
            int tmp = nums[i],j=i;
            while(j > 0 && nums[j - 1] > tmp){
                nums[j] = nums[j - 1];
                j--;
            }
            nums[j] = tmp;
        }
        Arrays.stream(nums).forEach(System.out::println);
    }

    @Test
    public void mergeSort(){
        System.out.println("=============================mergeSort=================================");
        obj = new MergeSort();
        int[] res = obj.sort(source);
        Arrays.stream(res).forEach(System.out::println);
    }

    @Test
    public void quickSort(){
        System.out.println("=============================quickSort=================================");
        obj = new QuickSort();
        int[] res = obj.sort(source);
        Arrays.stream(res).forEach(System.out::println);
    }
}
