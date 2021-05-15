# [多数元素](https://leetcode-cn.com/problems/majority-element/)

给定一个大小为 *n* 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 **大于** `⌊ n/2 ⌋` 的元素。

你可以假设数组是非空的，并且给定的数组总是存在多数元素。

 

**示例 1：**

```
输入：[3,2,3]
输出：3
```

**示例 2：**

```
输入：[2,2,1,1,1,2,2]
输出：2
```

 

**进阶：**

- 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。





本题是个有意思的题，

1. 首先我们会想到的是```哈希算法```，```暴力枚举数组内的元素```，然后输出哈希表中value最大的元素，但是**并不能够满足题目的进阶条件，时间复杂度 O(n),空间复杂度O(1)**

2. 其次呢，我们还会想到，如果在理想状态下，我们排序数组，**如果刚好有一半值的情况，那么直接输出nums[nums.length / 2]** 不就可以解决了么，可以使可以，不过这种算法并不那么可靠....

```java
public static int majorityElement(int[] nums) {
    Arrays.sort(nums);
    return nums[nums.length / 2];
}
```

其他的没招了... 看答案吧

+ 随机算法 （可能会导致死循环，需要优化 while循环的结束内容）

```java
public static int majorityElement1(int[] nums) {
    Random random = new Random();
    int targetCount = nums.length / 2;
    while(true){
        int source = nums[random.nextInt(nums.length - 1)];
        if(targetCount < random(nums,source)){
            return source;
        }
    }
}

public static int random(int[] nums,int num){
    int count = 0;
    for (int i = 0; i < nums.length; i++) {
        if(nums[i] == num){
            count++;
        }
    }
    return count;
}
```

+ 递归分而治之法

> 本算法的核心是将nums数组分为两块，分别取这两块的大多数，然后如果这两块最终返回大多数，如果左 = 右，那么直接返回 nums[左 / 右]

```java
public static int majorityElement2(int[] nums) {
    return majorityElement2_1(nums,0,nums.length -1);
}

public static int majorityElement2_1(int[] nums,int low,int high) {
    if (low == high) {
        return nums[low];
    }

    // 分而治之
    int middle = (high - low) / 2 + low;
    int left = majorityElement2_1(nums,low,middle);
    int right = majorityElement2_1(nums,middle +1,high);

    if(left == right) return left;

    int leftCount = countInrange(nums,left,low,high);
    int rightCount = countInrange(nums,right,low,high);

    return leftCount > rightCount ? left : right;
}

public static int countInrange(int[] nums, int num, int low,int high){
    int count = 0;
    for (int i = low; i <= high; i++) {
        if (nums[i] == num) {
            count++;
        }
    }
    return count;
}
```

+ 投票算法

> 一个喵字送给这个算法，核心就是 ```count += (num == candidate) ? 1 : -1;``` 如果当前的遍历的数字等于上一次遍历的数字，就 count + 1，如果count不为0 就一直保持这个 candidate，如果为0 就记录新的值，然后一次类推

```java
public static int majorityElement3(int[] nums) {
    int count = 0;
    Integer candidate = null;

    for (int num : nums) {
        if(count == 0){
            candidate = num;
        }
        count += (num==candidate)? 1: -1;
    }

    return candidate;
}
```

