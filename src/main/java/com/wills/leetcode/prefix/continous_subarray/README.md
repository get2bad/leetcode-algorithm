# [连续的子数组和](https://leetcode-cn.com/problems/continuous-subarray-sum/)

给你一个整数数组 `nums` 和一个整数 `k` ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：

- 子数组大小 **至少为 2** ，且
- 子数组元素总和为 `k` 的倍数。

如果存在，返回 `true` ；否则，返回 `false` 。

如果存在一个整数 `n` ，令整数 `x` 符合 `x = n * k` ，则称 `x` 是 `k` 的一个倍数。

 

**示例 1：**

```
输入：nums = [23,2,4,6,7], k = 6
输出：true
解释：[2,4] 是一个大小为 2 的子数组，并且和为 6 。
```

**示例 2：**

```
输入：nums = [23,2,6,4,7], k = 6
输出：true
解释：[23, 2, 6, 4, 7] 是大小为 5 的子数组，并且和为 42 。 
42 是 6 的倍数，因为 42 = 7 * 6 且 7 是一个整数。
```

**示例 3：**

```
输入：nums = [23,2,6,4,7], k = 13
输出：false
```



## 前缀和 + 暴力法

刚看到这道题就觉得似曾相识，这不是求连续的子数组么？我们可以直接套用前缀和 + 暴力法来搞定,但是可惜的是最终以超时告终(尴尬😓....)

![](http://image.tinx.top/20210602094628.png)

```java
public static boolean checkSubarraySum(int[] nums, int k) {
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
        sum += nums[i];
        for (int j = i + 1; j < nums.length; j++) {
            sum += nums[j];
            if(sum % k == 0){
                return true;
            }
        }
        sum = 0;
    }
    return false;
}
```





## 前缀和 + 哈希表

我们另辟蹊径，继续采用前缀和的方式，外加一个哈希表的方式来搞定这个！go ~!

步骤如下：

1. 定义一个map，以 余数 为键，以 遍历到的下标 为值
2. 遍历nums数组：

- 计算 加上当前数后，跟 k的倍数 相差多少
- 若 map中存在 当前余数，
  且 两下标之间，相差距离大于2
  那么，表示 两下标之间 的 连续子数组，为 k的倍数

![](http://image.tinx.top/20210602103842.png)

```java
public static boolean checkSubarraySum1(int[] nums, int k) {
    if (nums == null || nums.length < 2) {
        return false;
    }

    int length = nums.length;
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();    // 以 余数 为键，以 遍历到的下标 为值，存储的map
    map.put(0, -1);
    int remainder = 0;
    for (int i = 0; i < length; i++) {
        remainder = (remainder + nums[i]) % k;  // 计算 加上当前数后，跟 k的倍数 相差多少

        /*
            若 map中存在 当前余数，
                且 两下标之间，相差距离大于2
            那么，表示 两下标之间 的 连续子数组，为 k的倍数
        */
        if (map.containsKey(remainder)) {
            if (i- map.get(remainder) >= 2) {
                return true;
            }
        } else {
            map.put(remainder, i);
        }
    }

    return false;
}
```





# [连续数组](https://leetcode-cn.com/problems/contiguous-array/)

给定一个二进制数组 `nums` , 找到含有相同数量的 `0` 和 `1` 的最长连续子数组，并返回该子数组的长度。

**示例 1:**

```
输入: nums = [0,1]
输出: 2
说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
```

**示例 2:**

```
输入: nums = [0,1,0]
输出: 2
说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
```



刚看到这道题，研究了好久都不知道是啥意思，这官方出题越来越敷衍了，好歹可以看看英文版，看完英文原文，才终于了解了意思，佛了....



## 前缀和 + 哈希表

思路：

>  如果答案非 0，那么子数组长度必然为偶数，且长度至少为 2。

具体的，我们在预处理前缀和时，将 nums[i] 为 0 的值时当做 −1 处理。

从而将问题转化为：**如何求得最长一段区间和为 0 的子数组。**

同时使用「哈希表」来记录「某个前缀和出现的最小下标」是多少。

再结合「如果答案非 0，子数组长度至少为 2」的特性，我们让循环从 2 开始，并在循环开始前往「哈希表」存入前缀和元素，从而实现不需要处理边界问题。

大致想法就是：

如果哈希表中存在这个元素，那么就将现在从2开始遍历的元素减去前缀和存储的数组索引值，具体如下：

测试用例： [0,1,0,1,0,1]

前缀和结果： [0,-1,0,-1,0,-1,0]

哈希表遍历结果步骤：

1. Map => (0,0) | ans = 0
2. Map => (-1,1)(0,0) | ans = 3 - 1 = 2
3. map => 不变 | ans = 2
4. map => 不变 | ans = 5 - 1 = 4
5. map => 不变 | ans = 4
6. map => 不变 | ans = 6 - 0 = 6
7. map => 不变 | ans = 6
8. 返回 ans = 6

![](http://image.tinx.top/20210603114932.png)

```java
public static int findMaxLength(int[] nums) {
    int n = nums.length, ans = 0;
    int[] sum = new int[n + 1];
    for (int i = 1; i <= n; i++) {
        sum[i] = sum[i - 1] + (nums[i - 1] == 1 ? 1 : -1);
    }
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 0);
    for (int i = 2; i <= n; i++) {
        if (!map.containsKey(sum[i - 2])) {
            map.put(sum[i - 2], i - 2);
        }
        if (map.containsKey(sum[i])) {
            ans = Math.max(ans, i - map.get(sum[i]));
        }
    }
    return ans;
}
```



