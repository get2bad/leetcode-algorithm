# [Excel表列序号](https://leetcode-cn.com/problems/excel-sheet-column-number/)

给定一个Excel表格中的列名称，返回其相应的列序号。

例如，

```
    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
    ...
```

**示例 1:**

```
输入: "A"
输出: 1
```

**示例 2:**

```
输入: "AB"
输出: 28
```

**示例 3:**

```
输入: "ZY"
输出: 701
```



思路：
1. 切割字符串
2. 可以将 A-Z的比作 26进制的数，
     1. 如果就一个A 那么就是 26^0 = 1
     2. 如果两个A 那么就是 26^1 = 26 + 26^0 = 2

以此类推，那么本题的答案就出来了
后面就是 ASCII码的计算了因为 A的ASCII码为65 

所以，就直接

int num = array[i] - 'A' + 1

每一次循环 就是 res * 26 + num



```java
public static int titleToNumber(String columnTitle) {
    if(columnTitle == null || columnTitle.length() == 0) return 0;
    int res = 0;
    columnTitle = columnTitle.toUpperCase();
    char[] array = columnTitle.toCharArray();
    for (int i = 0; i < array.length; i++) {
        int num = array[i] - 'A' + 1;
        res = res * 26 + num;
    }
    return res;
}
```

