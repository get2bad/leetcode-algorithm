# 转置矩阵

给你一个二维整数数组 `matrix`， 返回 `matrix` 的 **转置矩阵** 。

矩阵的 **转置** 是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。

![img](https://assets.leetcode.com/uploads/2021/02/10/hint_transpose.png)

 

**示例 1：**

```
输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[[1,4,7],[2,5,8],[3,6,9]]
```

**示例 2：**

```
输入：matrix = [[1,2,3],[4,5,6]]
输出：[[1,4],[2,5],[3,6]]
```

 

**提示：**

- `m == matrix.length`
- `n == matrix[i].length`
- `1 <= m, n <= 1000`
- `1 <= m * n <= 105`
- `-109 <= matrix[i][j] <= 109`



本题是一个极其简单的问题，我们可以使用 双指针法 / 双遍历法，在之前的题目中，已经出现过 双指针法，所以本次我们使用双遍历法：

```java
/**
 * 本题的主题思路就是 横 转 竖
 * 我们来推断一下：
 * source[0][0] -> source[0][10
 * source[0][1] -> source[1][0] / source[1][0] -> source[0][1]
 * source[0][2] -> source[2][0] / source[2][0] -> source[0][2]
 *
 * 所以，双指针法 / 双遍历法可以解决本问题
 * 假设 二维数组总长度为 9 则横纵向 坐标应该为
 *  x = 9 / martix[0].length
 *  y = 9 % martix[0].length
 *
 *  横转竖应该为
 *  y = 9 / martix[0].length
 *  x = 9 % martix[0].length
 *
 *  双遍历法就是
 *  res[j][i] = matrix[i][j]
 */
public static int[][] transpose(int[][] matrix) {
    if(matrix == null || matrix.length == 0) return matrix;
    int length = matrix.length;
    int width = matrix[0].length;
    int[][] res = new int[length][width];
    for (int i = 0; i < length; i++) {
        for (int j = 0; j < width; j++) {
            res[j][i] = matrix[i][j];
        }
    }
    return res;
}
```

