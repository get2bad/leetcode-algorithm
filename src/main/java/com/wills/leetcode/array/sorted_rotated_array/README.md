# [搜索旋转排序数组](https://leetcode.cn/problems/search-in-rotated-sorted-array/)

整数数组 nums 按升序排列，数组中的值 互不相同 。

在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。

给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。

你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。

> 示例 1：
>
> 输入：nums = [4,5,6,7,0,1,2], target = 0
> 输出：4
> 示例 2：
>
> 输入：nums = [4,5,6,7,0,1,2], target = 3
> 输出：-1
> 示例 3：
>
> 输入：nums = [1], target = 0
> 输出：-1

对于寻找相关的问题，显而易见，我们可以使用 二分查找法来解决，但是这就有一个问题：

因为数组不知道在哪里是螺旋的，并且要求时间复杂度是 O(logn)，那么我们就要思考了：

1. 使用二分查找法如果切到了正好是螺旋位置，要怎么处理？ 

   a. 所以我们要判断一下 nums[mid] 和 nums[right] 的大小，如果nums[mid] 小于 nums[right] 说明没有螺旋，那么要继续去判断 ，如果 target 大于 nums[mid] 并且 target 小于 nums[right] 说明对半分的右边是我们要找的区间，所以 left = mid + 1， 否则左边就是我们要找的区间 所以 right = mid - 1

   b. 如果 nums[mid] 大于 nums[right] ，说明到了螺旋位置，所以要继续判断，如果 target 小于 nums[mid] 并且 target 大于等于 nums[left]，就 right = mid - 1,否则 left = mid - 1 

   c. 如果 nums[mid] 等于 target 就直接返回 mid 值即可

```java
public int search(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left <= right) {
        int mid = (left + right) >> 1;
        if (nums[mid] == target)
            return mid;
        else if (nums[mid] < nums[right]) {
            if (nums[mid] < target && target <= nums[right])
                left = mid + 1;
            else
                right = mid - 1;
        } else {
            if (nums[left] <= target && target < nums[mid])
                right = mid - 1;
            else
                left = mid + 1;
        }
    }
    return -1;
}
```