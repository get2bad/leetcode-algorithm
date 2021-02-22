## ☆21.爬楼梯问题 -> ClimbingStairs☆

> 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
>
> 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
>
> 注意：给定 n 是一个正整数。
>
> 示例 1：
>
> 输入： 2
> 输出： 2
> 解释： 有两种方法可以爬到楼顶。
>
> 1.  1 阶 + 1 阶
> 2.  2 阶
>     示例 2：
>
> 输入： 3
> 输出： 3
> 解释： 有三种方法可以爬到楼顶。
>
> 1.  1 阶 + 1 阶 + 1 阶
> 2.  1 阶 + 2 阶
> 3.  2 阶 + 1 阶

思考：

我们先想一下每一次要爬楼梯的解题数：

1 -> 1

2 -> 2

3 -> 3

4 -> 5

5 -> 8

仔细一想，这不是斐波那契数列么？(f(n) = f(n -1) + f(n -2))

答案：

1. 动态规划(**时间复杂度 O(n),空间复杂度O(n)**)

```java
    public static int climbStairs1(int n) {
			if(n <= 2){
			    return n;
			}
			// 可以加上缓存功能，防止因为递归调用带来的不必要的计算
			int[] cache = new int[n];
			cache[0] = 1;
			cache[1] = 2;
			for (int i = 2; i < n; i++) {
			    cache[i] = cache[i - 1] + cache[i - 2];
			}

			return cache[n - 1];
    }
```

优化：因为上述数组存储了没什么从的从前值，所以可以优化掉，直接使用变量来存储前值还有前前值 **空间复杂度 O(1)**

```java
public static int climbStairs1(int n) {
        if(n <= 2){
            return n;
        }
        // 上面代码的优化版 优化空间复杂度为 O(1)
        // preOfPre 代表 f(n - 2) pre 代表 f(n -1) cur 代表最终结果
        // 因为我们知道了 f(1) = 1 f(2) = 2 所以要减少不必要的计算，所以直接将 pre / preOfPre 赋值结果
        int pre = 2, preOfPre = 1, cur = 1;
        // 然后我们从 f(3) = f(2) + f(1) 开始计算
        for(int i = 3; i <= n; i++) {
            // f(n -1) + f(n -2)
            cur = pre + preOfPre;
            // 因为要继续下一次循环了，所以要将f(n -2)调整到 f(n -1)的状态
            preOfPre = pre;
            // 因为要继续下一次循环了，所以要将当前的cur结果赋值给 f(n -1) 以供下一次计算 f(n -1) + f(n -2)
            pre = cur;
        }
        return cur;
}
```

