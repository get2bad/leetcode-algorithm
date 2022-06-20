# [四数之和](https://leetcode.cn/problems/4sum/)

给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：

0 <= a, b, c, d < n
a、b、c 和 d 互不相同
nums[a] + nums[b] + nums[c] + nums[d] == target
你可以按 任意顺序 返回答案 。

> 示例 1：
>
> 输入：nums = [1,0,-1,0,-2,2], target = 0
> 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
> 示例 2：
>
> 输入：nums = [2,2,2,2,2], target = 8
> 输出：[[2,2,2,2]]



解题思路与 [15. 三数之和](https://leetcode.cn/problems/3sum/) 一致，就是多了一层循环，并且对于多余的循环要进行 **剪枝**处理，还要提防超限问题！

```java
public List<List<Integer>> fourSum(int[] nums, int target) {
    int len = nums.length;
    if (len < 4) return Collections.EMPTY_LIST;
    List<List<Integer>> res = new ArrayList<>();
    Arrays.sort(nums);
    for (int i = 0; i < len - 3; i++) {
        // 相同的直接跳过
        if(i > 0 && nums[i] == nums[i-1])   continue;
        for (int j = i + 1; j < len - 2; j++) {
            // 跟上面一样去重
            if(j > i + 1 && nums[j] == nums[j-1])   continue;
            int left = j + 1, right = len - 1;
            while (left < right) {
                int sum = nums[i] + nums[j] + nums[left] + nums[right];
                if (sum == target) {
                    List<Integer> content = Arrays.asList(nums[i], nums[j], nums[left], nums[right]);
                    if (!res.contains(content)) res.add(content);
                    // 进行跳过相同的元素
                    while (left < right && nums[left] == nums[left + 1]) ++left;
                    while (left < right && nums[right] == nums[right - 1]) --right;
                    // 过滤重复元素后，要将指针进行位移，因为目前左右指针依旧指向之前/重复的元素
                    ++left;
                    --right;
                } else if (sum > target) right--;
                else left++;
            }
        }
    }
    return res;
}
```