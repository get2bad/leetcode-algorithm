# [颠倒二进制位](https://leetcode-cn.com/problems/reverse-bits/)

颠倒给定的 32 位无符号整数的二进制位。

**示例 1：**

```
输入: 00000010100101000001111010011100
输出: 00111001011110000010100101000000
解释: 输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
     因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。
```

**示例 2：**

```
输入：11111111111111111111111111111101
输出：10111111111111111111111111111111
解释：输入的二进制串 11111111111111111111111111111101 表示无符号整数 4294967293，
     因此返回 3221225471 其二进制表示形式为 10111111111111111111111111111111 。
```



本题的思路就是按位叠加，因为是32位二进制数，那么做一个循环，每一次加最后一位即可

步骤：

1. 遍历32个位，将要返回的 res 向左移一位， 1 -> 10便于 后面叠加的时候有位可以补充
2. 然后将传入的数字 n 与 1 做**与**运算，因为与运算就是 0 & 1 = 0 / 1&1 =1 目的就是为了拿到最后一位
3. 然后与结果做叠加
4. 传入的 n 值向右移一位，删减去刚刚做了运算的位
5. 返回即可



```java
public static int reverseBits(int n) {
    int res = 0;
    for (int i = 0; i < 32; i++) {
        // 将 res向左移一位 res = 1 -> 10 便于后面的n最后一位与1做与运算叠加
        res <<= 1;
        // 叠加 最后一位与1的与运算，如果是0就是0
        res += 1 & n;
        // 传入的值向右移一位
        n >>= 1;
    }

    return res;
}
```
