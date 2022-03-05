# 子数组和

给你一个整数数组 `nums` 。`nums` 中，子数组的 **范围** 是子数组中最大元素和最小元素的差值。

返回 `nums` 中 **所有** 子数组范围的 **和** *。*

子数组是数组中一个连续 **非空** 的元素序列。

 

**示例 1：**

```
输入：nums = [1,2,3]
输出：4
解释：nums 的 6 个子数组如下所示：
[1]，范围 = 最大 - 最小 = 1 - 1 = 0 
[2]，范围 = 2 - 2 = 0
[3]，范围 = 3 - 3 = 0
[1,2]，范围 = 2 - 1 = 1
[2,3]，范围 = 3 - 2 = 1
[1,2,3]，范围 = 3 - 1 = 2
所有范围的和是 0 + 0 + 0 + 1 + 1 + 2 = 4
```

**示例 2：**

```
输入：nums = [1,3,3]
输出：4
解释：nums 的 6 个子数组如下所示：
[1]，范围 = 最大 - 最小 = 1 - 1 = 0
[3]，范围 = 3 - 3 = 0
[3]，范围 = 3 - 3 = 0
[1,3]，范围 = 3 - 1 = 2
[3,3]，范围 = 3 - 3 = 0
[1,3,3]，范围 = 3 - 1 = 2
所有范围的和是 0 + 0 + 0 + 2 + 0 + 2 = 4
```

**示例 3：**

```
输入：nums = [4,-2,-3,4,1]
输出：59
解释：nums 中所有子数组范围的和是 59
```



首先拿到这道题，光看题目是完全看不懂是什么意思的，然后看了示例才知道，我们需要列举出来所有可能的子数组，然后求最大值和最小值的差值和

首当其中，我们肯定会想到使用暴力方式进行双循环破解

## 暴力法

所谓暴力法，就是进行双循环的方式，求每次循环出来的最大值最小值然后求差值，最后叠加即可

假设我们传入的 nums[] = {1,2,3}

那么我们每一次循环的子数组应该为：

[1,2] max = 2 min = 1 diff = 1

[1,3] max = 3 min = 1 diff = 2

[2,3] max = 3 min = 2 diff = 1

最终结果应该为：1+2+1 = 4

如果是我们个人想法应该是 

[1,2] => 1 [2,3] => 1 [1,2,3] => 2 total = 4

```java
public static long subArrayRanges(int[] nums) {
    int len = nums.length, max = 0, min = 0;
    long res = 0;
    // 枚举子数组左边界
    for ( int i = 0; i < len; i++ ) {
        max = nums[i];
        min = nums[i];
        // 枚举有边界
        for ( int j = i+1; j < len; j++ ) {
            // 维护min max
            max = Math.max(max, nums[j]);
            min = Math.min(min, nums[j]);
            res += (max-min);
        }
    }
```

## 单调栈法(难)

![image-20220305101511015](http://image.tinx.top/image-20220305101511015.png)

```java
static int n;
public static long subArrayRanges2(int[] nums) {
    n = nums.length;
    // min[i] 为 nums[i] 作为区间最小值的次数；max[i] 为 nums[i] 作为区间最大值的次数
    long[] min = getCnt(nums, true), max = getCnt(nums, false);
    long ans = 0;
    for (int i = 0; i < n; i++) ans += (max[i] - min[i]) * nums[i];
    return ans;
}
static long[] getCnt(int[] nums, boolean isMin) {
    int[] a = new int[n], b = new int[n];
    Deque<Integer> d = new ArrayDeque<>();
    for (int i = 0; i < n; i++) {
        while (!d.isEmpty() && (isMin ? nums[d.peekLast()] >= nums[i] : nums[d.peekLast()] <= nums[i])) d.pollLast();
        a[i] = d.isEmpty() ? -1 : d.peekLast();
        d.addLast(i);
    }
    d.clear();
    for (int i = n - 1; i >= 0; i--) {
        while (!d.isEmpty() && (isMin ? nums[d.peekLast()] > nums[i] : nums[d.peekLast()] < nums[i])) d.pollLast();
        b[i] = d.isEmpty() ? n : d.peekLast();
        d.addLast(i);
    }
    long[] ans = new long[n];
    for (int i = 0; i < n; i++) ans[i] = (i - a[i]) * 1L * (b[i] - i);
    return ans;
}
```

## 区间 DP（预处理）跟暴力法相仿，可以忽略

![image-20220305100001490](http://image.tinx.top/image-20220305100001490.png)

代码的三维数组并不是很难理解，可以理解为{[0,0,0],[0,0,0],[0,0,0]},在这个二维数组每一个元素0后面又多出来两个元素，可以理解为一个长方体，其中多出来这两个元素，表示当前元素遍历时的最大/最小值

然后求结果时，我们只需要遍历三维数组多出来的这两个元素的差值叠加就是最终结果

```java
public static long subArrayRanges1(int[] nums) {
    int n = nums.length;
    int[][][] f = new int[n][n][2];
  	// 每个元素进行三维数组赋值
    for (int i = 0; i < n; i++) f[i][i][0] = f[i][i][1] = nums[i];
    for (int len = 2; len <= n; len++) {
        for (int l = 0; l + len - 1 < n; l++) {
            int r = l + len - 1;
            f[l][r][0] = Math.min(nums[r], f[l][r - 1][0]);
            f[l][r][1] = Math.max(nums[r], f[l][r - 1][1]);
        }
    }
    long ans = 0;
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            ans += f[i][j][1] - f[i][j][0];
        }
    }
    return ans;
}
```