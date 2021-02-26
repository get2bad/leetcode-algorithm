## 8. 移除目标数组与target相同的数字，并且返回操作结束后的数组新长度 -> RemoveDuplicatesFromSortedArray

大学练手简单题

```java
public static int removeDuplicates(int[] nums) {
    if (nums.length == 0) return 0;
    int i = 0;
    for (int j = 1; j < nums.length; j++) {
        if (nums[j] != nums[i]) {
            i++;
            nums[i] = nums[j];
        }
    }
    return i + 1;
}
public static int removeDuplicates1(int[] nums) {
    int[] source = nums;
    HashSet<Integer> set = new HashSet<>();
    int flag = 0;
    for (int i : source) {
        boolean a = set.add(i);
        if(a){
            nums[flag] = i;
            flag++;
        }
    }
    return set.size();
}
```

