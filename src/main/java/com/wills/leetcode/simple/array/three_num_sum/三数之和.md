# [15. 三数之和](https://leetcode.cn/problems/3sum/)

给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。

**注意：答案中不可以包含重复的三元组。**

> 示例 1：
>
> 输入：nums = [-1,0,1,2,-1,-4]
> 输出：[[-1,-1,2],[-1,0,1]]
> 示例 2：
>
> 输入：nums = []
> 输出：[]
> 示例 3：
>
> 输入：nums = [0]
> 输出：[]

算法流程：

1. 特判，对于数组长度 nn，如果数组为 null 或者数组长度小于 3，返回 [][]。
2. 对数组进行排序。
3. 遍历排序后数组：
   + 若 nums[i]>0：因为已经排序好，所以后面不可能有三个数加和等于 00，直接返回结果。
   + 对于重复元素：跳过，避免出现重复解
   + 令左指针 L=i+1，右指针 R=n-1，当 L<R时，执行循环：
     + 当 nums[i]+nums[L]+nums[R] = 0，执行循环，判断左界和右界是否和下一位置重复，去除重复解。并同时将 L,R移到下一位置，寻找新的解
     + 若和大于 0，说明 nums[R] 太大，R 左移
     + 若和小于 0，说明 nums[L]太小，L 右移

```java
public List<List<Integer>> threeSum(int[] nums) {
    int len = nums.length;
    if (len < 3) return Collections.EMPTY_LIST;
    List<List<Integer>> res = new ArrayList<>();
    Arrays.sort(nums);
    for (int i = 0; i < len; i++) {
        if (nums[i] > 0) return res;

        if (i > 0 && nums[i] == nums[i - 1]) continue;

        int curr = nums[i];
        // 遍历 + 双指针
        int left = i + 1, right = len - 1;
        while (left < right) {
            int tmp = curr + nums[left] + nums[right];
            if (tmp == 0) {
                res.add(Arrays.asList(curr, nums[left], nums[right]));
                // 排除重复的元素 重点！因为题目要求
                while (left < right && nums[left + 1] == nums[left]) ++left;
                while (left < right && nums[right - 1] == nums[right]) --right;
                // 过滤重复元素后，要将指针进行位移，因为目前左右指针依旧指向之前/重复的元素
                ++left;
                --right;
            } else if (tmp < 0) {
                // 如果小于0，就，右移左指针，因为数组是有序的，要到大于0的元素来叠加
                ++left;
            } else {
                --right;
            }
        }
    }
    return res;
}
```

