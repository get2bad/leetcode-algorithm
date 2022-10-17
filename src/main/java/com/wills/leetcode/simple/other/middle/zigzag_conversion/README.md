# [Z 字形变换](https://leetcode-cn.com/problems/zigzag-conversion/)

将一个给定字符串 `s` 根据给定的行数 `numRows` ，以从上往下、从左到右进行 Z 字形排列。

比如输入字符串为 `"PAYPALISHIRING"` 行数为 `3` 时，排列如下：

```
P   A   H   N
A P L S I I G
Y   I   R
```

之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如：`"PAHNAPLSIIGYIR"`。

请你实现这个将字符串进行指定行数变换的函数：

```
string convert(string s, int numRows);
```

 

**示例 1：**

```
输入：s = "PAYPALISHIRING", numRows = 3
输出："PAHNAPLSIIGYIR"
```

**示例 2：**

```
输入：s = "PAYPALISHIRING", numRows = 4
输出："PINALSIGYAHRPI"
解释：
P     I    N
A   L S  I G
Y A   H R
P     I
```

**示例 3：**

```
输入：s = "A", numRows = 1
输出："A"
```



思路：
 题目要求中要构成Z字形，最后发现效果是N字形，然后我发现这道题完全可以进行找规律将横的转换为竖的，就是每次 +1 +1 +1，到头以后就进行 -1 -1 -1即可，所以我们使用StringBuilder就行（因为是同步的，速度更快，本题不存在多线程问题，就直接使用这个快的）

```java
public static String convert(String s, int numRows) {
    // 如果是构不成 一个完整的Z就直接原样返回即可
    if(s == null || s.length() == 0 || numRows <= 1) return s;
    StringBuilder[] sbs = new StringBuilder[numRows];
    // 对象实例初始化
    for (int i = 0; i < sbs.length; i++) {
        sbs[i] = new StringBuilder();
    }
    int index = 0,curr = 1;
    for (char source : s.toCharArray()) {
        // 将内容塞进StringBuilder中
        sbs[index].append(source);
        // 进行 +1 +1 +1 -1 -1 -1
        index += curr;
        // 如果到了边界，就进行取负，方便进行 +1 +1 +1 -1 -1 -1处理
        if(index == numRows - 1 || index == 0) curr = -curr;
    }
    StringBuilder res = new StringBuilder();
    for (StringBuilder sb : sbs) {
        res.append(sb.toString());
    }
    return res.toString();
}
```

 完美AC！

![image-20220301113239800](http://image.tinx.top/image-20220301113239800.png)