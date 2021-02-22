## 2. 给定数组和目标值，找出数组元素相加等于目标值的索引Index -> SumTwoNumbers

```java
Integer[] nums = {3,2,4};
Integer target = 6;
List<Integer> res = new ArrayList();
for (int i = 0; i < nums.length; i++) {
    for (int j = i+1; j < nums.length; j++) {
        if(nums[i] + nums[j] == target){
            res.add(i);
            res.add(j);
        }
    }
}
int[] ress = res.stream().mapToInt(Integer::intValue).toArray();
Arrays.stream(ress).forEach(System.out::println);
```

