# [快乐数](https://leetcode-cn.com/problems/happy-number/)

编写一个算法来判断一个数 `n` 是不是快乐数。

「快乐数」定义为：

- 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
- 然后重复这个过程直到这个数变为 1，也可能是 **无限循环** 但始终变不到 1。
- 如果 **可以变为** 1，那么这个数就是快乐数。

如果 `n` 是快乐数就返回 `true` ；不是，则返回 `false` 。

 

**示例 1：**

```
输入：19
输出：true
解释：
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
```

**示例 2：**

```
输入：n = 2
输出：false
```



本题是一道很有意思的题，起初我写的代码和答案几乎一致，就是没有循环终止条件，导致了死循环 或者 StackOverFlow..

然后就想，是不是可以使用无重复的哈希表来解决呢？

+ 通用获取当前值n各位平方和

```java
public static int happyNum(int source){
    int res = 0;
    while(source > 0){
        int index = source % 10;
        res += index * index;
        source /= 10;
    }
    return res;
}
```



所以有了以下答案：

> 整体的思路就是 使用不重复的哈希表来存储每一次的循环数字， 循环终止条件是 获取的各位平方和等于1 / 哈希表中存在过遍历的数字就终止遍历，然后返回结果

时间复杂度O(logn) 空间复杂度 O(n)

```java
public static boolean isHappy(int n) {
    Set<Integer> seen = new HashSet<>();
    while(n != 1 && !seen.contains(n)){
        seen.add(n);
        n = happyNum(n);
    }
    return n == 1;
}
```



后面看了答案，发现还有两种方案

1. 快慢指针法

时间复杂度O(logn) 空间复杂度 O(1)

```java
public static boolean isHappy1(int n) {
    int fast = happyNum(n);
    int slow = n;
    while(fast != 1 && slow != fast ){
        slow = happyNum(slow);
        fast = happyNum(happyNum(fast));
    }
    return fast == 1;
}
```



2. 数学法(用处不大，不多做介绍)