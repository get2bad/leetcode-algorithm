# [在排序数组中查找元素的第一个和最后一个位置](https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/)

给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。

如果数组中不存在目标值 target，返回 [-1, -1]。

你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。

> 示例 1：
>
> 输入：nums = [5,7,7,8,8,10], target = 8
> 输出：[3,4]
> 示例 2：
>
> 输入：nums = [5,7,7,8,8,10], target = 6
> 输出：[-1,-1]
> 示例 3：
>
> 输入：nums = [], target = 0
> 输出：[-1,-1]

## 方法一 折半查询法

对于数组的搜索，我们可以使用折半查询法，但是涉及到区间，所以我们要进行两次折半查询。

在此联想到之前做过的一道题：

> 给你一个连续递增数组nums，让你找 target 的区间，解决方案是 使用两次折半查询，第一次查询目标的最小区间 + 再次查找 target+ 1的即可解决

解决代码为：

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int l = search(nums, target);
        int r = search(nums, target + 1);
        return l == nums.length || l >= r ? new int[]{-1, -1} : new int[]{l, r - 1};
    }

    private int search(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

本题做法与上面讲述的完全一致,成功AC！

