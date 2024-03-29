# 括号的最大嵌套深度<font color=green>简单</font>

如果字符串满足以下条件之一，则可以称之为 有效括号字符串（valid parentheses string，可以简写为 VPS）：

字符串是一个空字符串 ""，或者是一个不为 "(" 或 ")" 的单字符。
字符串可以写为 AB（A 与 B 字符串连接），其中 A 和 B 都是 有效括号字符串 。
字符串可以写为 (A)，其中 A 是一个 有效括号字符串 。
类似地，可以定义任何有效括号字符串 S 的 嵌套深度 depth(S)：

> depth("") = 0
> depth(C) = 0，其中 C 是单个字符的字符串，且该字符不是 "(" 或者 ")"
> depth(A + B) = max(depth(A), depth(B))，其中 A 和 B 都是 有效括号字符串
> depth("(" + A + ")") = 1 + depth(A)，其中 A 是一个 有效括号字符串
> 例如：""、"()()"、"()(()())" 都是 有效括号字符串（嵌套深度分别为 0、1、2），而 ")(" 、"(()" 都不是 有效括号字符串 。

给你一个 有效括号字符串 s，返回该字符串的 s 嵌套深度 。

> 示例 1：
>
> 输入：s = "(1+(2*3)+((8)/4))+1"
> 输出：3
> 解释：数字 8 在嵌套的 3 层括号中。
> 示例 2：
>
> 输入：s = "(1)+((2))+(((3)))"
> 输出：3
> 示例 3：
>
> 输入：s = "1+(2*3)/(2-1)"
> 输出：1
> 示例 4：
>
> 输入：s = "1"
> 输出：0

其实这个题目类型我之前做过，不过比这个更高级一些，就是求多括号的算术题，那道题是使用栈Stack（FILO）来解决的，然后接触到这道题不自觉的就会使用栈来解决，但是发现，我们不用存储中间值，所以不需要栈，直接使用两个临时数即可解决：

方法内的思路为：

1. 将字符串转换为字符数组，然后进行遍历
2. 如果遇到了 ( 就对临时值 flag加1，因为遇到了一个括号
3. 然后比对最终返回值和临时值的最大值(确保永远是最大的那个)
4. 如果遇到了  ) 就对临时值 flag减1
5. 最后返回这个最终值即可

```java
public static int maxDepth(String s) {
    int res = 0,flag = 0;
    // Deque<Character> stack = new ArrayDeque<>();
    char[] chars = s.toCharArray();
    for (char data : chars) {
        if('(' == data){
            // 如果栈内为空，就直接清除最终返回的元素即可
            flag++;
            res = Math.max(res,flag);
        }
        if(')'== data){
            flag--;
        }
    }
    return res;
}
```

经过上述的操作，最终成功AC!

![image-20220107131157039](http://rloqc3ngo.hd-bkt.clouddn.com/image-20220107131157039.png)