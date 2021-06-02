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

