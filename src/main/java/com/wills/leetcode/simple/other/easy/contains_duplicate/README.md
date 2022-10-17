# [存在重复元素 II](https://leetcode-cn.com/problems/contains-duplicate-ii/)<font color=green>简单</font>

给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。

> 示例 1：
>
> 输入：nums = [1,2,3,1], k = 3
> 输出：true
> 示例 2：
>
> 输入：nums = [1,0,1,1], k = 1
> 输出：true
> 示例 3：
>
> 输入：nums = [1,2,3,1,2,3], k = 2
> 输出：false

这种题我们可以很简单的想出两个思路：

1. 滑动窗口
2. 哈希表

滑动窗口相比较于哈希表来说更加复杂，并且时间复杂度更高，所以我们直接选择哈希表，由于逻辑过于简单，这里仅仅展示源码：

## 通俗易懂版本

```java
public static boolean containsNearbyDuplicate(int[] nums, int k) {
    Map<Integer,Integer> source = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        // 如果不存在，就向hash表里面增加新元素
        if(!source.containsKey(nums[i])){
            source.put(nums[i],i);
        }else{
            Integer preIndex = source.get(nums[i]);
            int difference = Math.abs(i - preIndex);
            if(difference <= k){
                return true;
            }else{
                // 如果匹配不成功，就更新最新的索引值
                source.put(nums[i],i);
            }
        }
    }
    return false;
}
```

## 优化版

```java
public static boolean containsNearbyDuplicate1(int[] nums, int k) {
    Map<Integer,Integer> source = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        // 如果存在，就向判断一下,符合条件直接返回true，否则覆盖值
        if(source.containsKey(nums[i])){
            Integer preIndex = source.get(nums[i]);
            int difference = Math.abs(i - preIndex);
            if(difference <= k){
                return true;
            }
        }
        // 如果匹配不成功，就更新最新的索引值
        source.put(nums[i],i);
    }
    return false;
}
```