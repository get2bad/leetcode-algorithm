## 4. 查找数组中目标值的索引index -> SearchIndex

本题直接用二分查找法解出，后面多补充了一个笨方法来解题

```java
/**
     * 使用二分查找法
     */
    public static int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = nums.length -1, res = n;
        while(left <= right){
            // 确定中间数， 为什么要 +left? 因为要调整最新要遍历的数组到 1/2的位置
            int mid = ((right - left) >> 1) + left;
            if(target <= nums[mid]){
                // |___left____|___right____|
                //      ↑ 取目标数组1/2左边的作为新的要遍历的数组
                right = mid -1;
                res = mid;
            } else {
                // |___left____|___right____|
                //                  ↑ 取目标数组1/2右边的作为新的要遍历的数组
                left = mid + 1;
            }

        }
        return res;
    }

    /**
     * 笨方法解决，挨个遍历
     */
    public static int searchInsert1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == target){
                return i;
            } else{
                if(nums[0]> target) return 0;
                if(i+1 >= nums.length) return nums.length;
                if(nums[i] < target && nums[i+1] > target){
                    return i +1;
                }
            }
        }
        return 0;
    }
```

