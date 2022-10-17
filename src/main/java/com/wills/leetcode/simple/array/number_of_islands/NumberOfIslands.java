package com.wills.leetcode.simple.array.number_of_islands;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName NumberOfIslands
 * @Date 2022/7/14 11:27
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class NumberOfIslands {

    private static final char[][] source = {
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}
    };

    @Test
    public void test() {
        System.out.println(numIslands(source));
    }

    //利用深度递归解决，可以看图，并加记住这个模板，他可以解决岛屿中的问题，还有一题岛屿面积问题也是这个模板。
    public int numIslands(char[][] grid) {
        //定义一个表示岛屿数量的变量
        int count = 0;
        //这个两层for循环是用来遍历整张二维表格中所有的陆地
        //其中 i 表示行，j 表示列
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                //取出所有的陆地
                if (grid[i][j] == '1') {
                    //深度递归，遍历所有的陆地
                    dfs(grid, i, j);
                    //用来统计有多少岛屿，岛屿是由多个陆地组成的，概念不一样
                    count++;
                }
            }
        }
        //返回岛屿的数量
        return count;
    }

    public void dfs(char[][] grid, int i, int j) {
        //防止 i 和 j 越界，也就是防止超出岛屿（上下左右）的范围。特别注意当遍历到海洋的时候也退出循环
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') return;
        //将遍历过的陆地改为海洋，防止重复遍历
        grid[i][j] = '0';
        //上，
        dfs(grid, i + 1, j);
        //下
        dfs(grid, i - 1, j);
        //右
        dfs(grid, i, j + 1);
        //左
        dfs(grid, i, j - 1);
    }

    // BFS
    public int numIslandsByBfs(char[][] grid) {
        int count = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1'){
                    bfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }
    private void bfs(char[][] grid, int i, int j){
        Queue<int[]> list = new LinkedList<>();
        list.add(new int[] { i, j });
        while(!list.isEmpty()){
            int[] cur = list.remove();
            i = cur[0]; j = cur[1];
            if(0 <= i && i < grid.length && 0 <= j && j < grid[0].length && grid[i][j] == '1') {
                grid[i][j] = '0';
                list.add(new int[] { i + 1, j });
                list.add(new int[] { i - 1, j });
                list.add(new int[] { i, j + 1 });
                list.add(new int[] { i, j - 1 });
            }
        }
    }


}
