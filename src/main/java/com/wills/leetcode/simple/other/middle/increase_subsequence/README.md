# [递增的三元子序列](https://leetcode-cn.com/problems/increasing-triplet-subsequence/)<font color=orange>中等</font>

给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。

如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。

> 示例 1：
>
> 输入：nums = [1,2,3,4,5]
> 输出：true
> 解释：任何 i < j < k 的三元组都满足题意
> 示例 2：
>
> 输入：nums = [5,4,3,2,1]
> 输出：false
> 解释：不存在满足题意的三元组
> 示例 3：
>
> 输入：nums = [2,1,5,0,4,6]
> 输出：true
> 解释：三元组 (3, 4, 5) 满足题意，因为 nums[3] == 0 < nums[4] == 4 < nums[5] == 6

一开始还以为是构建连续递增的子序列呢，就胡乱写了个，没想到失败了(要怪就怪题目没说明白，力扣出的题 “is like a shit”，好多题题目都没描述清楚，给的示例都没包含)，看到提交报错以及相关用例，我就知道不是关联的几个元素是递增的就可以了，大意了啊，没有闪！

![image-20220112105512820](http://image.tinx.top/image-20220112105512820.png)

然后吭哧吭哧做了一顿。。。。用了栈、队列啥的，做的不太对，丢人的看了答案，发现这么简单，我们使用贪心算法，步骤大致为：

1.  先声明一个 first(指向数组的第一个元素),second(指向最大元素，在遍历时会改变)
2. 然后从1开始遍历数组，因为0我们已经使用到了first中
3. 在遍历中，如果当前数大于第二个数，说明这就是个自增数列，直接返回true即可
4. 如果 当前数 大于 first 说明，构建的数列的第二个数second达到了要求，将当前数赋值给second
5. 如果当前数小于first，说明需要重新构建这个队列，将当前数赋值给first

整体流程就这么简单~

```java
public static boolean increasingTriplet(int[] nums) {
    int len = nums.length;
    if(len < 3) return false;
    // 首先赋值 first为数组中第一个元素 second为整数最大值，为的是构造一个三连队列
    int first = nums[0], second = Integer.MAX_VALUE;
    // 从 index = 1开始遍历这个数组
    for (int i = 1; i < len; i++) {
        // 拿到当前遍历的这个数
        int num = nums[i];
        // 如果 当前数 大于 second()
        if (num > second) {
            // 说明是一个递增序列
            return true;
        } else if (num > first) { // 如果 当前值 大于 first
            // 那么 将当前值赋值给 second，因为这是构造递增序列的第二步(第二关键值)
            second = num;
        } else {
            // 否则 当前值赋值给first 因为重新构建一个递增序列，之前的已经被破坏(遇到了一个比起始值还小的数字)
            first = num;
        }
    }
    return false;
}
```

最终用抄来的代码成功AC！囧

![image-20220112113617451](http://image.tinx.top/image-20220112113617451.png)

也看到了一个新的思路，下面是大致步骤和代码：

1. 声明一个从起始循环的新数组 leftMin，从左到右依次遍历最小的数，当作一个三连数中的最左边的元素，遍历结束后结果为： [20, 20, 10, 10, 5, 5]
2. 声明一个从结束到起始循环的新数组rightMax，从右到左依次遍历最大的数，当作一个三连数中最右边的蒜素，遍历结束后结果为： [100, 100, 13, 13, 13, 13]
3. 然后从右到左遍历传入的数组，当作中间数，依次判断是否和左小数是否比它大，右大数是否比它小，如果满足条件返回true即可。

```java
public static boolean increasingTriplet1(int[] nums) {
    int n = nums.length;
    if (n < 3) {
        return false;
    }
    // 复制一个相同长度的数组
    int[] leftMin = new int[n];
    // 将新数组的第一个元素赋值为传入的数组第一个元素，找到最小值排序
    leftMin[0] = nums[0];
    // 比较当前遍历元素与现在遍历的元素的大小，将最小的放入新的数组
    for (int i = 1; i < n; i++) {
        leftMin[i] = Math.min(leftMin[i - 1], nums[i]);
    }
    // 声明一个新的数组，从右边遍历的，找到最大值排序
    int[] rightMax = new int[n];
    // 找到传入数组的最后一个
    rightMax[n - 1] = nums[n - 1];
    for (int i = n - 2; i >= 0; i--) {
        rightMax[i] = Math.max(rightMax[i + 1], nums[i]);
    }
    for (int i = 1; i < n - 1; i++) {
        int currentNum = nums[i];
        int leftMinNum = leftMin[i - 1];
        int rightMaxNum = rightMax[i + 1];
        if (currentNum > leftMinNum && currentNum < rightMaxNum) {
            return true;
        }
    }
    return false;
}
```

