package com.wills.leetcode;

import java.util.Arrays;

/**
 * 合并两个有序的数组
 *
 * 给你两个有序整数数组nums1 和 nums2，请你将 nums2 合并到nums1中，使 nums1 成为一个有序数组。
 *
 * 初始化nums1 和 nums2 的元素数量分别为m 和 n 。你可以假设nums1 的空间大小等于m + n，这样它就有足够的空间保存来自 nums2 的元素。
 *
 * 示例 1：
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 *
 * 示例 2：
 * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
 * 输出：[1]
 *
 * 提示：
 * nums1.length == m + n
 * nums2.length == n
 * 0 <= m, n <= 200
 * 1 <= m + n <= 200
 * -109 <= nums1[i], nums2[i] <= 109
 */
public class MergeSortedArray {

    public static void main(String[] args) {
        int[] num1 = {1,2,3,4,5,0,0,0,0,0};
        int[] num2 = {2,3,4,5,6};
        merge2(num1,5,num2,5);

    }

    /**
     * 方法一 : 合并后排序
     * 使用jdk自带的api进行排序
     * 时间复杂度： O((m+n)log(m+n))
     * 最朴素的解法就是将两个数组合并之后再排序。该算法只需要一行(Java是2行)，时间复杂度较差，为O((n + m)\log(n + m))O((n+m)log(n+m))。这是由于这种方法没有利用两个数组本身已经有序这一点。
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
        Arrays.stream(nums1).forEach(System.out::println);
    }

    /**
     * 方法二： 双指针法
     * 1.声明一个临时的数组tmp，指向有数据的nums1
     * 2.将nums1有数据的复制到这个临时数组tmp
     * 3.声明三个指针，分别指向 nums1 nums2 tmp
     * 4.进行循环判断 如果小于就进行相关赋值
     * 5.如果最后遍历的元素还没有全部赋值完毕要做最后的补充
     * 时间复杂度： O(m+n)
     * 空间复杂度： O(m)
     */
    public static void merge1(int[] nums1, int m, int[] nums2, int n) {
        int[] tmp = new int[m];
        System.arraycopy(nums1,0,tmp,0,m);

        int pointer1 = 0,pointer2 = 0, pointer3 = 0;

        while((pointer1 < m)&&(pointer2 < n)){
            nums1[pointer3++] = (tmp[pointer1] < nums2[pointer2]) ? tmp[pointer1++] : nums2[pointer2++];
        }

        if(pointer1 < m){
            System.arraycopy(tmp, pointer1, nums1, pointer1 + pointer2, m + n - pointer1 - pointer2);
        }
        if(pointer2 < n){
            System.arraycopy(nums2, pointer2, nums1, pointer1 + pointer2, m + n - pointer1 - pointer2);
        }
        Arrays.stream(nums1).forEach(System.out::print);
    }

    /**
     * merge1的简化版，将空间复杂度进一步优化
     * 时间复杂度O(m+n)
     * 空间复杂度O(1)
     */
    public static void merge2(int[] nums1, int m, int[] nums2, int n) {

        int pointer1 = m - 1, pointer2 = n - 1, pointer3 = m + n -1;

        while((pointer1 >= 0)&&(pointer2 >= 0)){
            nums1[pointer3--] = (nums1[pointer1] < nums2[pointer2]) ? nums2[pointer2--]: nums1[pointer1--];
        }
        System.arraycopy(nums2, 0, nums1, 0, pointer2 + 1);

        Arrays.stream(nums1).forEach(System.out::print);
    }
}
