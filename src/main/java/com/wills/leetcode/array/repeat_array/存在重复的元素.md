# 存在重复的元素<font color=green>简单</font>

给定一个整数数组，判断是否存在重复元素。

如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。

示例 1:

输入: [1,2,3,1]
输出: true

示例 2:

输入: [1,2,3,4]
输出: false

示例 3:

输入: [1,1,1,3,3,4,3,2,4,2]
输出: true



解题思路：

本题我们可以很巧妙的使用 **哈希表** 来解决本题，因为哈希表是天生可以去重的，以下是我的解题源码：

```java
// 使用哈希表
public static boolean containsDuplicate(int[] nums) {
    Map<Integer,Integer> source = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        Integer target = source.get(nums[i]);
        if(target != null) return true;
        // 否则就添加元素至 哈希表中
        source.put(nums[i],nums[i]);
    }
    return false;
}

// 使用 Set(和哈希表几乎一样，因为set底层也是哈希表)
public static boolean containsDuplicate1(int[] nums) {
    Set<Integer> source = new HashSet<>();
    for (int i = 0; i < nums.length; i++) {
        if(source.contains(nums[i])) return true;
        source.add(nums[i]);
    }
    return false;
}

// sets升级版
public static boolean containsDuplicate2(int[] nums) {
    Set<Integer> source = new HashSet<>();
    Arrays.stream(nums).forEach(source::add);
    return source.size() == nums.length;
}
```

简简单单的题目读一下代码就懂了，故解题思路略。