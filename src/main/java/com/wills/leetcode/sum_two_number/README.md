# [两数之和](https://leetcode-cn.com/problems/two-sum/)

给定一个整数数组 `nums` 和一个整数目标值 `target`，请你在该数组中找出 **和为目标值** 的那 **两个** 整数，并返回它们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。

你可以按任意顺序返回答案。

 

**示例 1：**

```
输入：nums = [2,7,11,15], target = 9
输出：[0,1]
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
```

**示例 2：**

```
输入：nums = [3,2,4], target = 6
输出：[1,2]
```

**示例 3：**

```
输入：nums = [3,3], target = 6
输出：[0,1]
```



没啥好说的，直接 暴力枚举 / 哈希表解决！



+ 暴力枚举

```java
/**
 * 暴力枚举法
 */
public static int[] twoSum(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
        int res = target - nums[i];
        for (int j = i + 1; j < nums.length; j++) {
            if(res == nums[j]){
                return new int[]{i,j};
            }
        }
    }
    return null;
}
```

+ 哈希表

```java
/**
 * 哈希表法
 */
public static int[] twoSum1(int[] nums, int target) {
    Map<Integer,Integer> source = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        if(source.containsKey(target - nums[i])){
            return new int[]{source.get(target - nums[i]),i};
        }
        source.put(nums[i],i);
    }
    return null;
}
```





# [两数相加](https://leetcode-cn.com/problems/add-two-numbers/)

给你两个 **非空** 的链表，表示两个非负的整数。它们每位数字都是按照 **逆序** 的方式存储的，并且每个节点只能存储 **一位** 数字。

请你将两个数相加，并以相同形式返回一个表示和的链表。

你可以假设除了数字 0 之外，这两个数都不会以 0 开头。

 

**示例 1：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/01/02/addtwonumber1.jpg)

```
输入：l1 = [2,4,3], l2 = [5,6,4]
输出：[7,0,8]
解释：342 + 465 = 807.
```

**示例 2：**

```
输入：l1 = [0], l2 = [0]
输出：[0]
```

**示例 3：**

```
输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
输出：[8,9,9,9,0,0,0,1]
```



本题的难点就在于遍历这两个链表，其他没啥难的，把握好进位，巧妙使用 求模运算 取最后一位，除法运算 取是否进位，如果进位就在下一次加上即可

 

下面是答案：

```java
public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode pre = new ListNode(0);
    ListNode cur = pre;
    int carry = 0;
    // 遍历这两个ListNode
    while(l1 != null || l2 != null) {
        int x = l1 == null ? 0 : l1.val;
        int y = l2 == null ? 0 : l2.val;
        int sum = x + y + carry;
				
      // 进位
        carry = sum / 10;
      // 余位
        sum = sum % 10;
        cur.next = new ListNode(sum);

        cur = cur.next;
        if(l1 != null)
            l1 = l1.next;
        if(l2 != null)
            l2 = l2.next;
    }
    if(carry == 1) {
        cur.next = new ListNode(carry);
    }
    return pre.next;
}
```

