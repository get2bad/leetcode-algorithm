package com.wills.leetcode.simple.martix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 王帅
 * @ClassName Martix
 * @date 2021/6/22 09:56
 * @Version 1.0
 * @Description
 */
public class Martix {

    public static void main(String[] args) {
        int[][] source = {  {0, 0, 0},
                            {0, 1, 0},
                            {0, 0, 0}};
        updateMatrix1(source);
    }

    // BFS 广度优先搜索遍历
    public static int[][] updateMatrix1(int[][] matrix) {
        // 首先将所有的 0 都入队，并且将 1 的位置设置成 -1，表示该位置是 未被访问过的 1
        Queue<int[]> queue = new LinkedList<>();
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                } else {
                    matrix[i][j] = -1;
                }
            }
        }
        // x 轴的上下 应该为当前元素 上： x - 1 下： x + 1 故 -1 1 0 0
        // y 轴的左右 应该为当前元素 左： y -1 右：y+ 1 故 0 0 -1 1
        // dx dy 的作用仅仅是求出当前元素的上下左右的是否为0值
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int x = point[0], y = point[1];
            for (int i = 0; i < 4; i++) {
                // 这边 （dx, dy) 代表的依次从 0 - 3 分别为： 当前元素的 上、下、左、右
                int newX = x + dx[i];
                int newY = y + dy[i];
                // 判断当前的元素是否大小于0 防止数组越界Exception
                if (newX >= 0 && newX < m && newY >= 0 && newY < n) {
                    // 如果当前遍历结果是 -1 说明这个位置不是0，就要更新最新的距离 atrix[x][y] + 1
                    if (matrix[newX][newY] == -1) {
                        // 设置访问过的符号
                        matrix[newX][newY] = matrix[x][y] + 1;
                        // 然后重新加入队列
                        queue.offer(new int[]{newX, newY});
                    }
                }
            }
        }
        return matrix;
    }

    // 暴力法 yyds
    public static int[][] updateMatrix(int[][] mat) {
        int len = mat.length, wid = mat[0].length;
        int[][] res = new int[len][wid];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < wid; j++) {
                if (mat[i][j] != 0) {
                    // 如果目标二维数组不是0，说明需要找出他的邻居 / 他邻居的邻居是不是0，是0的话计算距离
                    res[i][j] = getDistance(mat, 0, i, j);
                }
                // 否则遍历的是0 说明距离0就是他本身 为0，不做任何操作
            }
        }
        return res;
    }

    // 递归的方式，全方位的找出上下左右的距离 仅仅作为思路来展示，具体代码不详细纠结
    public static int getDistance(int[][] target, int distance, int x, int y) {
        if (target[x][y] == 0) {
            return distance;
        }

        int left = getDistance(target, ++distance, x - 1, y);
        int right = getDistance(target, ++distance, x + 1, y);
        int top = getDistance(target, ++distance, x, y - 1);
        int bottom = getDistance(target, ++distance, x, y + 1);
        // 找出最大的距离
        return Integer.max(Integer.max(Integer.max(left, right), top), bottom);
    }


}
