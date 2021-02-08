# leetcode-algorithm
> 本代码仓库是本菜鸟刷leetcode的记录，尽量保证每天一题(不保证突然兴起多刷几道题~~~)，奥利给，冲！

## 1. 判断括号是否有效

```java
// TODO 等待回来整理解题方法
```



## 2. 给定数组和目标值，找出数组元素相加等于目标值的索引Index

```java
// TODO 等待回来整理解题方法
```



## 3. strStr函数 找出target出现在source的第一个索引

本题可以直接调用indexOf方法，但是这样会过于简单，所以我们使用startWith来实现一下，后期会补充不用工具api来实现(找出target第一个字母在source的位置，然后截取source与target相同长度的字符串，进行比较，不对就继续遍历，找不到就返回-1)

```java
// TODO 等待回来整理解题方法
```



## 4. 查找数组中目标值的索引index

本题直接用二分查找法解出，后面多补充了一个笨方法来解题

```java
// TODO 等待回来整理解题方法
```



## 5. 查找罗马数字

```
MCMXCIV -> 1994
```

```java
// TODO 等待回来整理解题方法
```



## 6. 判断一个数字是否是回文数

简单题 大学入门题目

```java
// TODO 等待回来整理解题方法
```



## 7. 将数字进行倒过来输出

同上一道题

```java
// TODO 等待回来整理解题方法
```



## 8. 移除目标数组与target相同的数字，并且返回操作结束后的数组新长度

大学练手简单题

```java
// TODO 等待回来整理解题方法
```



## 9. 在空间复杂度O(1)下删除target指向的排序好的数组

大学练手简单题，就是空间复杂度O(1)比较棘手而已

```java
// TODO 等待回来整理解题方法
```



## 10. 字符串数组最长公共前缀

雷同于第三题

```java
// TODO 等待回来整理解题方法
```



## 11. 合并两个排序好的链表，合并后的链表也是保持顺序

稍微动一些脑筋题，不过也是简单题

```java
// TODO 等待回来整理解题方法
```



## 12. 外观数列

本题是一个典型的递归方法的小题，本题难点在于对于遍历的数字个数的保存，以及状态的改变，还有返回的字符串叠加

本题的巧妙之处在于 沿用之前的 **快慢指针** 的方法，如果当前遍历的 **快指针** 数字和 **慢指针** 数字不相同的情况下就触发以下逻辑：

1. 字符串叠加，使用 **快指针end** 减去 **慢指针start** 得出来的就是当前相同数字的个数，然后再叠加 **慢指针start** 指向的数字
2. 将 **慢指针start** 的数字 调整到 **快指针end** 的数字，用于遍历下面相同个数的数字

然后再方法的最后 **不要忘记处理字符串最后一个数字，因为上面的遍历会漏掉最后一个数字**

```java
public String countAndSay(int n) {
    if (n == 1) {
        return "1";
    }
    // 递归调用
    String source = countAndSay(n - 1);
    StringBuilder sb = new StringBuilder();
    // 在这里写出来计数的字符串
    // 。。。。。
    // 思路： 将返回的String遍历进行计数
    int start = 0;
    for (int end = 1; end < source.length(); end++) {
        // 进行计数，如果这个数与上一个不同，就中断计数重新开始 start 重置为 end所在的索引
        if (source.charAt(end) != source.charAt(start)) {
            sb.append(end-start).append(source.charAt(start));
            start = end;
        }
    }
    // 处理字符串的最后一个数字
    sb.append(source.length() - start).append(source.charAt(start));
    return sb.toString();
}
```

