package com.wills.leetcode.prefix.best_day_to_eat_candy;

/**
 * @author 王帅
 * @ClassName Candy
 * @date 2021/6/1 09:27
 * @Version 1.0
 * @Description
 * 你能在你最喜欢的那天吃到你最喜欢的糖果吗？
 */
public class Candy {

    public static void main(String[] args) {
        int[] candiesCount = {7,4,5,3,8};
        int[][] queries = {{0,2,2},{4,2,4},{2,13,1000000000}};
        for (boolean b : canEat(candiesCount, queries)) {
            System.out.print(b + "_");
        }
    }

    /**
     * 读了好久的题目，才理解这个意思，其中这个queries数组的的每个数组元素数组代表的意思是：
     * 例如[3,1,2] => [最想吃的是第4类(3+1)类糖果，最想在第2(1+1)天吃到，每天最多吃2颗糖果]
     * 然后输出的answer数组就是 上述的数组能否满足在最喜欢的那天能否吃到第X类糖果
     */
    public static boolean[] canEat(int[] candiesCount, int[][] queries) {
        int n  = candiesCount.length;
        boolean[] res = new boolean[queries.length];
        long[] sum = new long [n + 1];
        sum[0] = 0L;
        // 利用前缀和保存第 i 类糖果个数的区间
        for (int i = 1 ; i <= n ; i++) {
            sum[i] = sum[i - 1] + candiesCount[i - 1];
        }
        // 遍历这个需求数组，进行相关的判断处理
        for (int i = 0; i < queries.length; i ++) {
            // queries[i] = [favoriteTypei, favoriteDayi, dailyCapi]
            int favoriteType = queries[i][0];
            int favoriteDay = queries[i][1] + 1;
            int dailyCap = queries[i][2];
            // 在favoriteDayi日可以吃到的糖果个数区间 (第0天开始吃糖果)
            // 这里记得转类型，不然会溢出
            long total = (long) favoriteDay * dailyCap;
            // 最喜欢吃的糖果的个数的区间 最少 和 最多
            long favoriteMin = sum[favoriteType] + 1;
            long favoriteMax = sum[favoriteType + 1];
            // 判断两个区间是否存在交集，存在则在favoriteDay日可以吃到favoriteType糖果
            res[i] = favoriteMin <= total && favoriteDay <= favoriteMax;
        }
        return res;
    }
}
