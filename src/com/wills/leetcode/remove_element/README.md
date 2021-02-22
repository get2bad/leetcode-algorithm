## 9. 在空间复杂度O(1)下删除target指向的排序好的数组 -> RemoveElement

大学练手简单题，就是空间复杂度O(1)比较棘手而已

```java
public static int removeElement(int[] nums, int val) {
    int flag = 0;
    for (int i = 0; i < nums.length; i++) {
        if(nums[i] != val){
            // 如果当前遍历元素不等于目标要删除的元素，那么就进行重新赋值
            nums[flag] = nums[i];
            flag ++;
        }
    }
    return flag;
}
```

