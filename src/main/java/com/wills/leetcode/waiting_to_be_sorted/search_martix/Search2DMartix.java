package com.wills.leetcode.waiting_to_be_sorted.search_martix;

/**
 * @author 王帅
 * @date 2021-03-30 09:29:28
 * @description:
 * 搜索二维矩阵
 */
public class Search2DMartix {

    /**
     * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
     *
     * 每行中的整数从左到右按升序排列。
     * 每行的第一个整数大于前一行的最后一个整数。
     *
     *
     * 示例 1：
     *
     *
     * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
     * 输出：true
     * 示例 2：
     *
     *
     * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
     * 输出：false
     */
    public static void main(String[] args) {
        int[][] source = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        System.out.println(searchMatrix(source, 21));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null) return false;
        int length = matrix.length;
        int width = matrix[0].length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if(matrix[i][0] <= target && matrix[i][width - 1] >= target){
                    // 进行二分查找
                    return search(matrix[i],target);
                }
            }
        }
        return false;
    }

    public static boolean search(int[] source, int target){
        int start = 0, end = source.length - 1;
        int middle = 0;
        while(end >= start){
            middle = (end + start) / 2;
            if(source[middle] > target){
                end = middle - 1;
            } else if(source[middle] < target){
                start = middle + 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
