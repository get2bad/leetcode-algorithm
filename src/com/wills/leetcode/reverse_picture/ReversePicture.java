package com.wills.leetcode.reverse_picture;

import java.util.Arrays;

/**
 * @author 王帅
 * @date 2021-02-24 09:34:20
 * @description:
 *
 *  翻转图像
 */
public class ReversePicture {

    /**
     * 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
     * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
     * 反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
     *
     * 示例 1：
     * 输入：[[1,1,0],[1,0,1],[0,0,0]]
     * 输出：[[1,0,0],[0,1,0],[1,1,1]]
     * 解释：首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
     *      然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
     *
     * 示例 2：
     * 输入：[[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
     * 输出：[[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
     * 解释：首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
     *      然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
     */
    public static void main(String[] args) {
        int[][] source = {{1,1,0},{1,0,1},{0,0,0}};
        int[][] res = flipAndInvertImage(source);
        Arrays.stream(res).forEach(data -> {
            for (int i = 0; i < data.length; i++) {
                System.out.println(data[i]);
            }
        });
    }

    /**
     * 步骤：
     * 1. 先 水平翻转图像  [1,1,0] -> [0,1,1]
     *  水平翻转图像，就是折半对换，然后取反
     * 2. 后 翻转图像 [0,1,1] -> [1,0,0]
     *
     * 思路：
     * 1. 遍历这个二维数组
     * 2. 使用双指针方法 （为什么不遍历 A[i].length / 2? 因为遍历这个，如果遇到了偶数的长度，结果会出错，所以还是双指针比较方便）
     * 3.因为通过观察，如果[1,0,0] -> 结果应为 [1,1,0]
     *      如果 A[i][left] == A[i][right] 的时候 和1做异或取反
     *      如果不相等 ，结果是不变的 例如 [1,0,1] -> [1,1,1]
     */
    public static int[][] flipAndInvertImage(int[][] A) {
        if(A == null && A.length == 0) return null;
        int length = A.length;
        for (int i = 0; i < length; i++) {
            int left = 0,right = length -1;
            while(left < right){
                if(A[i][left] == A[i][right]){
                    A[i][left] ^= 1;
                    A[i][right] ^= 1;
                }
                left ++;
                right --;
            }
            // 如果 左 = 右 说明指针到了中间，将中间进行去个反就行了
            // 如果是偶数长度 不加 if会出错
            // 如果是 双 for循环形式 也要加个 左 = 右 的判断
            if(left == right){
                A[i][left] ^= 1;
            }
        }
        return A;
    }
}
