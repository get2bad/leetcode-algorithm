# leetcode-algorithm
> 本代码仓库是本菜鸟刷leetcode的记录，尽量保证每天一题(不保证突然兴起多刷几道题~~~)，奥利给，冲！

## 1. 判断括号是否有效

```java
// TODO 等待回来整理解题方法
```



## 2. 给定数组和目标值，找出数组元素相加等于目标值的索引Index

```java
// TODO 等待回来整理解题方法
```



## 3. strStr函数 找出target出现在source的第一个索引

本题可以直接调用indexOf方法，但是这样会过于简单，所以我们使用startWith来实现一下，后期会补充不用工具api来实现(找出target第一个字母在source的位置，然后截取source与target相同长度的字符串，进行比较，不对就继续遍历，找不到就返回-1)

```java
// TODO 等待回来整理解题方法
```



## 4. 查找数组中目标值的索引index

本题直接用二分查找法解出，后面多补充了一个笨方法来解题

```java
// TODO 等待回来整理解题方法
```



## 5. 查找罗马数字

```
MCMXCIV -> 1994
```

```java
// TODO 等待回来整理解题方法
```



## 6. 判断一个数字是否是回文数

简单题 大学入门题目

```java
// TODO 等待回来整理解题方法
```



## 7. 将数字进行倒过来输出

同上一道题

```java
// TODO 等待回来整理解题方法
```



## 8. 移除目标数组与target相同的数字，并且返回操作结束后的数组新长度

大学练手简单题

```java
// TODO 等待回来整理解题方法
```



## 9. 在空间复杂度O(1)下删除target指向的排序好的数组

大学练手简单题，就是空间复杂度O(1)比较棘手而已

```java
// TODO 等待回来整理解题方法
```



## 10. 字符串数组最长公共前缀

雷同于第三题

```java
// TODO 等待回来整理解题方法
```



## 11. 合并两个排序好的链表，合并后的链表也是保持顺序

稍微动一些脑筋题，不过也是简单题

```java
// TODO 等待回来整理解题方法
```



## 12. 外观数列

本题是一个典型的递归方法的小题，本题难点在于对于遍历的数字个数的保存，以及状态的改变，还有返回的字符串叠加

本题的巧妙之处在于 沿用之前的 **快慢指针** 的方法，如果当前遍历的 **快指针** 数字和 **慢指针** 数字不相同的情况下就触发以下逻辑：

1. 字符串叠加，使用 **快指针end** 减去 **慢指针start** 得出来的就是当前相同数字的个数，然后再叠加 **慢指针start** 指向的数字
2. 将 **慢指针start** 的数字 调整到 **快指针end** 的数字，用于遍历下面相同个数的数字

然后再方法的最后 **不要忘记处理字符串最后一个数字，因为上面的遍历会漏掉最后一个数字**

```java
public String countAndSay(int n) {
    if (n == 1) {
        return "1";
    }
    // 递归调用
    String source = countAndSay(n - 1);
    StringBuilder sb = new StringBuilder();
    // 在这里写出来计数的字符串
    // 。。。。。
    // 思路： 将返回的String遍历进行计数
    int start = 0;
    for (int end = 1; end < source.length(); end++) {
        // 进行计数，如果这个数与上一个不同，就中断计数重新开始 start 重置为 end所在的索引
        if (source.charAt(end) != source.charAt(start)) {
            sb.append(end-start).append(source.charAt(start));
            start = end;
        }
    }
    // 处理字符串的最后一个数字
    sb.append(source.length() - start).append(source.charAt(start));
    return sb.toString();
}
```



## 13.最大子序和

本题有两种解题思路，但是题目要求是时间的复杂度为 O(N)，所以使用冒泡算法迭代遍历的方式显然是不符合要求(时间复杂度O(N^2))，所以我们使用"贪心算法"来解决：

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
     * 如果不懂，请看官方的解题思路： <a href="https://leetcode-cn.com/problems/maximum-subarray/solution/zui-da-zi-xu-he-by-leetcode-solution/">最大子序和</a>
     *
     * @param nums
     * @return
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



## 14. 最后一个单词的长度

但凡有点java 基础的都能在5秒做出来.... 不解读了，莫得意思~



## 15.二进制求和

答题思路(枯燥无味的1.最优解决办法 2.思路 3.笨方法)

1. 末尾对齐相加法

```java
/**
     * 使用末尾对齐的方式进行相加，保证了假如说 a b 字符串无法进行Integer.parseInt的时候的可行办法
     *
     * + '0' 啥意思？ 想一下大学学习的 ASCII，然后你懂得
     */
    public static String addBinary(String a,String b){
        StringBuffer sb = new StringBuffer();

        int n = Math.max(a.length(), b.length()), carry = 0;
        for (int i = 0; i < n; ++i) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            sb.append((char) (carry % 2 + '0'));
            carry /= 2;
        }

        if (carry > 0) {
            sb.append('1');
        }
        sb.reverse();

        return sb.toString();
    }
```

2. 直接进行二进制位相加 （这样代码虽然明面上说可以，但是假如说有人钻牛角尖，输入很长的一串让你无法调用API进行转换咋办？）

```java
public static String addBinary(String a,String b){
        return Integer.toBinaryString(
                Integer.parseInt(a, 2) + Integer.parseInt(b, 2)
        );
    }
```

3. 将字符串直接 toCharArray 然后进行二进制判断叠加即可(太笨的方法了，就不展示代码了)



## 16.判断回文字符串(忽略空格 / 标点符号 / 大小写)

```java
/**
     * 因为题目条件是 要忽略空格 + 标点符号
     * 思路：
     * 1. 将字符串转化为一个CharArray
     * 2.然后使用一个指针分别遍历开头和结尾的，但是要ignore大小写
     * 3.如果遇到 空格 / 标点符号 就进行跳过
     * 4.遇到一个对不上的直接返回false
     * 5.遍历到最后还是对上那么返回true即可
     */
    public static boolean isPalindrome(String s) {
        char[] source = s.toCharArray();
        int i = 0;
        int j = source.length -1;
        while(i < j){
            if(!Character.isLetterOrDigit(source[i]) || source[i] == 32){
                // 非数字或者字母 就将当前指针 + 1 / 32 是空格
                ++i;
                continue;
            }

            if(!Character.isLetterOrDigit(source[j]) || source[j] == 32){
                // 非数字或者字母 就将当前指针 - 1
                --j;
                continue;
            }
            if(Character.toLowerCase(source[i]) != Character.toLowerCase(source[j])){
                return false;
            }
            // 循环完了 将外部指针 自减
            --j;
            ++i;
        }
        return true;
    }
```



## 17.回文字符串2

> 本题的妙点就是 条件中如果删除一个字符串是回文字符串的话那就是回文字符串，
>
> 本题我们使用 **贪心算法** ，进行前后遍历，然后另开一个方法重新判断删除的这个字符串
>
> 那么 问题来了：
>
> ​	**怎么判定是删除头指针 还是 尾指针 的字符串呢？**
>
> 小孩子才做选择，咱们程序员肯定是都要啦~！
>
> 都用额外方法判断一次，然后进行异或逻辑操作后输出即可(你就说妙不妙？！)

```java
/**
     * 本题使用贪心算法
     * 本问题的关键就是 怎么判定要删除的字符串在什么位置?
     * 时间复杂度为O(n)
     */
    public static boolean validPalindrome(String s) {
        int start = 0, end = s.length() - 1;
        while(start < end){
            if(s.charAt(start) != s.charAt(end)){
                // 如果不匹配就调用 isValid 方法作为一个额外的 重新判断的方法，
                // 然后使用 异或逻辑运算 来输出是否是真的回文字符串
                return isValid(s, start + 1, end) || isValid(s, start, end - 1);
            }
            start++;
            end--;
        }
        return true;
    }

    public static boolean isValid(String s, int start, int end){
        while(start < end){
            if(s.charAt(start) != s.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
```

