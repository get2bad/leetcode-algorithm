## 最大子序和 -> MaximumSubarray

## 动态规划算法

本题有两种解题思路，但是题目要求是时间的复杂度为 O(N)，所以使用冒泡算法迭代遍历的方式显然是不符合要求(时间复杂度O(N^2))，所以我们使用"贪心算法"来解决：

我们先来了解一下动态规划的几个步骤

1，确定状态

2，找到转移公式

3，确定初始条件以及边界条件



我们试着找一下:

1，定义dp[i]表示数组中前i+1（注意这里的i是从0开始的）个元素构成的连续子数组的最大和。


2，如果要计算前i+1个元素构成的连续子数组的最大和，也就是计算dp[i]，只需要判断dp[i-1]是大于0还是小于0。如果dp[i-1]大于0，就继续累加，dp[i]=dp[i-1]+num[i]。如果dp[i-1]小于0，我们直接把前面的舍弃，也就是说重新开始计算，否则会越加越小的，直接让dp[i]=num[i]。所以转移公式如下

dp[i]=num[i]+max(dp[i-1],0);



3，边界条件判断，当i等于0的时候，也就是前1个元素，他能构成的最大和也就是他自己，所以

dp[0]=num[0];



找到了上面的转移公式，代码就简单多了，来看下

```java
/**
     * 使用贪心算法来（动态规划）解决本题,思路：
     * 1. 声明一个res cur_num变量，res指向nums第一个元素，cur_num 初始值为0
     * 2. 遍历这个Nums数组
     * 3. 不使用if语句，我们使用Math.max找出两者的最大值
     * 4.分别进行两步取max
     * a. 当前遍历元素与 cur_num的sum 还有 当前元素的最大值。如果当前元素大于两者的加和，那么就相当于
     * 重置当前遍历的元素的加和，中断之前的遍历
     * b.取返回的 res 和 cur_nums的最大值，这一步的意义就是看当前 a 步的最大值与要返回的res相比的最大值，
     * 找出 最大值进行返回
     * 5. 返回结果 res
     * 时间复杂度 O(n)
     * 如果不懂，请看官方的解题思路：
     * <a href="https://leetcode-cn.com/problems/maximum-subarray/solution/zui-da-zi-xu-he-by-leetcode-solution/">最大子序和</a>
     */
    public static int maxSubArray(int[] nums) {
        if (nums.length == 1) return nums[0];
        int res = nums[0], cur_num = 0;
        for (int i = 1; i < nums.length; i++) {
            cur_num = Math.max(nums[i], cur_num + nums[i]);

            res = Math.max(cur_num, res);
        }
        return res;
    }
```
