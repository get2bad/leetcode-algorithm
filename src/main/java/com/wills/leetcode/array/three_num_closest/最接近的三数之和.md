# [最接近的三数之和](https://leetcode.cn/problems/3sum-closest/)

给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。

返回这三个数的和。

假定每组输入只存在恰好一个解。

> 示例 1：
>
> 输入：nums = [-1,2,1,-4], target = 1
> 输出：2
> 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
> 示例 2：
>
> 输入：nums = [0,0,0], target = 1
> 输出：0

解题思路与 **[15. 三数之和](https://leetcode.cn/problems/3sum/)** 相似，可以使用 遍历 + 双指针的方式来解题，题目的关键就是**要对指针的把控要特别精准才可以。**

```java
public int threeSumClosest(int[] nums, int target) {
    Arrays.sort(nums);
    int len = nums.length;
    int res = nums[0] + nums[1] + nums[2];
    // 然后相关操作
    for (int i = 0; i < len; i++) {
        int left = i + 1, right = len - 1;
        while (left < right) {
            int tmp = nums[left] + nums[right] + nums[i];
            int lastDiff = Math.abs(target - res), currDiff = Math.abs(target - tmp);
            if (currDiff < lastDiff) {
                res = tmp;
            } else if (tmp  < target) {
                left++;
            } else right--;
        }
    }
    return res;
}
```