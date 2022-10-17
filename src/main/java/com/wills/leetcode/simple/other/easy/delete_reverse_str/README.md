# [1332. 删除回文子序列](https://leetcode-cn.com/problems/remove-palindromic-subsequences/)<font color=green>简单</font>

给你一个字符串 s，它仅由字母 'a' 和 'b' 组成。每一次删除操作都可以从 s 中删除一个回文 子序列。

返回删除给定字符串中所有字符（字符串为空）的最小删除次数。

「子序列」定义：如果一个字符串可以通过删除原字符串某些字符而不改变原字符顺序得到，那么这个字符串就是原字符串的一个子序列。

「回文」定义：如果一个字符串向后和向前读是一致的，那么这个字符串就是一个回文。

> 示例 1：
>
> 输入：s = "ababa"
> 输出：1
> 解释：字符串本身就是回文序列，只需要删除一次。
> 示例 2：
>
> 输入：s = "abb"
> 输出：2
> 解释："abb" -> "bb" -> "". 
> 先删除回文子序列 "a"，然后再删除 "bb"。
> 示例 3：
>
> 输入：s = "baabb"
> 输出：2
> 解释："baabb" -> "b" -> "". 
> 先删除回文子序列 "baab"，然后再删除 "b"。

刚拿到这道题时，分析了一下官方给的例子，然后自己又举例了几个小例子，发现好像题目的答案除了2就是1，如果遇到了不相同的，那么必定是2，所以直接使用左右指针快速结束战斗！



```java
/**
 * 分析，删除回文子序列：
 * 1. 首先满足回文子序列有两种(1) aba 这种算是一种子序列 (2) abba 这种也算是一种子序列
 * 题目要求，需要几次才能全部删除完毕这个字符串,首先我们先模拟几种字符串：
 * 抛开官方给的例证，我们再假设几种：
 * aababaaa => 2
 * aabbbbaaa => 2
 * aaaabbbb => 2
 * 发现结果除了2就是1，所以我们可以快速运用左右指针法解题
 * 步骤：
 * 1. 首先将这个字符串转换为 字符数组
 * 2. 遍历这个字符数组,运用左右指针法解题
 * @param s
 * @return
 */
public static int removePalindromeSub(String s) {
    int len = s.length();
    char[] chars = s.toCharArray();
    // 左右指针法
    int fast = len - 1,slow = 0;
    while (slow < fast){
        if(chars[slow] != chars[fast]) return 2;
        slow++;
        fast--;
    }
    return 1;
}
```

最终成功AC！遇到简单题就是快哉快哉~

![image-20220122095827381](http://image.tinx.top/image-20220122095827381.png)