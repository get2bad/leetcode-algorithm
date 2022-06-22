# [下一个排列](https://leetcode.cn/problems/next-permutation/)

找出这个数组排序出的所有数中，刚好比当前数大的那个数

比如当前 nums = [1,2,3]。这个数是123，找出1，2，3这3个数字排序可能的所有数，排序后，比123大的那个数 也就是132

如果当前 nums = [3,2,1]。这就是1，2，3所有排序中最大的那个数，那么就返回1，2，3排序后所有数中最小的那个，也就是1，2，3 -> [1,2,3]

> 示例 1：
>
> 输入：nums = [1,2,3]
> 输出：[1,3,2]
> 示例 2：
>
> 输入：nums = [3,2,1]
> 输出：[1,2,3]
> 示例 3：
>
> 输入：nums = [1,1,5]
> 输出：[1,5,1]

相关的思路：

按照题干中的说明，我们可以理解为：找一个集合中合并起来的下一个最小值(除当前序列的第二小的值)

那么如何找第二个最小值呢？我们的思路是这样的(我们以 1，5，8，4，7，6，5，3，1来举例)：

1. 我们从数组队尾到队头来遍历，找到不是单调递增的数组下标，最终我们找到了 元素4不是单调递增的,i
2. 然后记录下元素4的数组下标3，然后根据这个元素继续从队尾向前遍历，直到找到比元素4大的元素5(索引为6) j
3. 交换一下这两个位置（swap(nums,i,j)）,最终变为： 1，5，8，5，7，6，4，3，1
4. 但是这还不是最终值，因为我们之前找的都是递增序列，那么从 i 开始肯定都是依次递减的(从队头开始看)，所以我们只需要交换从 i + 1开始到 队尾的元素即可(递减的反义词不就是递增了)
5. 达到目标

```java
public void nextPermutation(int[] nums) {
    int len = nums.length;
    int i = len - 2;
    // 寻找非降序的 a[i - 1] (当前 i 比 后面的小为止)
    while (i >= 0 && nums[i] >= nums[i + 1]) {
        i--;
    }
    // 如果是顺序的数组 那么 i 就是1（len = 3）
    if (i >= 0) {
        int j = len - 1;
        // 当前值跟倒序元素比较大小，如果 i 元素比 倒序指针元素大，倒序指针减1
        // 寻找 a[j]
        while (j >= 0 && nums[i] >= nums[j]) {
            j--;
        }
        // 交换 a[i - 1] 和 a[j]
        swap(nums, i, j);
    }
    // 翻转从 i+1后的数组
    reverse(nums, i + 1);
}

public void reverse(int[] nums, int start) {
    int left = start, right = nums.length - 1;
    while (left < right) {
        swap(nums, left, right);
        left++;
        right--;
    }
}

public void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}
```