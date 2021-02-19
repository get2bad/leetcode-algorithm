\* [1\. 判断括号是否有效 \-&gt; ValidParentheses](#1-判断括号是否有效---validparentheses)

\* [2\. 给定数组和目标值，找出数组元素相加等于目标值的索引Index \-&gt; SumTwoNumbers](#2-给定数组和目标值找出数组元素相加等于目标值的索引index---sumtwonumbers)

\* [3\. strStr函数 找出target出现在source的第一个索引 \-&gt; StrStr](#3-strstr函数-找出target出现在source的第一个索引---strstr)

\* [4\. 查找数组中目标值的索引index \-&gt; SearchIndex](#4-查找数组中目标值的索引index---searchindex)

\* [5\. 查找罗马数字 \-&gt; RomaNumber](#5-查找罗马数字---romanumber)

\* [6\. 判断一个数字是否是回文数 \-&gt; ReverseString](#6-判断一个数字是否是回文数---reversestring)

\* [7\. 将数字进行倒过来输出 \-&gt; ReverseNumber](#7-将数字进行倒过来输出---reversenumber)

\* [8\. 移除目标数组与target相同的数字，并且返回操作结束后的数组新长度 \-&gt; RemoveDuplicatesFromSortedArray](#8-移除目标数组与target相同的数字并且返回操作结束后的数组新长度---removeduplicatesfromsortedarray)

\* [9\. 在空间复杂度O(1)下删除target指向的排序好的数组 \-&gt; RemoveElement](#9-在空间复杂度o1下删除target指向的排序好的数组---removeelement)

\* [10\. 字符串数组最长公共前缀 \-&gt; MostLongStrPrefix](#10-字符串数组最长公共前缀---mostlongstrprefix)

\* [11\. 合并两个排序好的链表，合并后的链表也是保持顺序 \-&gt; MergeTwoSortedLists](#11-合并两个排序好的链表合并后的链表也是保持顺序---mergetwosortedlists)

\* [12\. 外观数列 \-&gt; CountAndSay](#12-外观数列---countandsay)

\* [13\.最大子序和 \-&gt; MaximumSubarray](#13最大子序和---maximumsubarray)

\* [14\. 最后一个单词的长度](#14-最后一个单词的长度)

\* [☆15\.二进制求和 \-&gt; BinaryAdd ☆](#15二进制求和----binaryadd-)

\* [16\.判断回文字符串(忽略空格 / 标点符号 / 大小写) \-&gt; ValidPalindrome](#16判断回文字符串忽略空格--标点符号--大小写---validpalindrome)

\* [17\.回文字符串2 \-&gt; ValidPalindrome2](#17回文字符串2---validpalindrome2)

\* [18\. 速算机器人 \-&gt; CalculatorMachine](#18-速算机器人---calculatormachine)

\* [19\. 早餐组合 \-&gt; BreakfastCombination](#19-早餐组合---breakfastcombination)

\* [20\. 自制平方根 sqrt函数](#20-自制平方根-sqrt函数)

\* [☆21\.爬楼梯问题 \-&gt; ClimbingStairs☆](#21爬楼梯问题---climbingstairs)

\* [22\. 删除排序链表中的重复元素 \-&gt; RemoveDuplicatesLinkedList](#22-删除排序链表中的重复元素---removeduplicateslinkedlist)

\* [☆23\.合并两个有序的数组 \-&gt; MergeSortedArray☆](#23合并两个有序的数组---mergesortedarray)

\* [24\. 重塑矩阵 \-&gt; ReShape](#24-重塑矩阵---reshape)

\* [☆25\.相同的树 \-&gt; SameTree☆](#25相同的树---sametree)

\* [☆26\. 对称二叉树 \-&gt; SymmetricTree☆](#26-对称二叉树---symmetrictree)



## 1. 判断括号是否有效 -> ValidParentheses

> HashMap 还有 Stack 栈 最佳实践，Deque = Stack

```java
public static boolean isValid(String s) {
        if(s.length() == 0 || s == null || s.length() %2 != 0) return false;
        Deque<Character> deque = new ArrayDeque<>();
        Map<Character,Character> source = new HashMap<>();
        source.put(')','(');
        source.put('}','{');
        source.put(']','[');
        for (char c : s.toCharArray()) {
            if(c == '(' || c== '{' || c== '['){
                deque.push(c);
            } else {
                // 如果 出栈匹配不上现在的遍历元素 就直接返回flase
                if(deque.size() == 0 || source.get(c) != deque.pop()){
                    return false;
                }
            }
        }
        return deque.size() == 0;
    }
```



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



## 3. strStr函数 找出target出现在source的第一个索引 -> StrStr

本题可以直接调用indexOf方法，但是这样会过于简单，所以我们使用startWith来实现一下，后期会补充不用工具api来实现(找出target第一个字母在source的位置，然后截取source与target相同长度的字符串，进行比较，不对就继续遍历，找不到就返回-1)

```java
public static int strStr(String haystack, String needle) {
        if(needle.equals("")) return 0;
        int res = 0;
        // 沿用之前的那个寻找公共字符串的题进行解出
        while(!haystack.startsWith(needle)){
            res ++;
            if("".equals(haystack)) return -1;
            haystack = haystack.substring(1);
        }
        return res;
    }
```



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



## 5. 查找罗马数字 -> RomaNumber

```
MCMXCIV -> 1994
```

```java
public static int romanToInt(String s) {
    int sum = 0;
    int preNum = getValue(s.charAt(0));
    for(int i = 1;i < s.length(); i ++) {
        int num = getValue(s.charAt(i));
        if(preNum < num) {
            sum -= preNum;
        } else {
            sum += preNum;
        }
        preNum = num;
    }
    sum += preNum;
    return sum;
}

private static int getValue(char ch) {
    switch(ch) {
        case 'I': return 1;
        case 'V': return 5;
        case 'X': return 10;
        case 'L': return 50;
        case 'C': return 100;
        case 'D': return 500;
        case 'M': return 1000;
        default: return 0;
    }
}
```



## 6. 判断一个数字是否是回文数 -> ReverseString

简单题 大学入门题目

```java
public static boolean isPalindrome(int x) {
    // 先转换为字符串
    char[] source  = (x + "").toCharArray();
    int start = 0;
    int end = source.length - 1;
    boolean flag = true;
    while(start <= source.length / 2){
        if(source[start] != source[end]){
            flag = false;
            break;
        }
        start ++;
        end --;
    }
    return flag;
}
```



## 7. 将数字进行倒过来输出 -> ReverseNumber

同上一道题

```java
public static int reverse(int x) {
    long res = 0;
    while(x != 0){
        res = (res * 10) + (x % 10);
        x = x / 10;
    }
    // 注意一定要考虑溢出性检查
    if(res != (int)res){
        return 0;
    }
    return (int)res;
}
```



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



## 10. 字符串数组最长公共前缀 -> MostLongStrPrefix

雷同于第三题

```java
public static String longestCommonPrefix(String[] strs) {
    if(strs == null || strs.length == 0){
        return "";
    }
    String res = strs[0];
    for (String str : strs) {
        while(!str.startsWith(res)){
            if(res.length() == 0) return "";
            res = res.substring(0,res.length() - 1);
        }
    }
    return res;
}
```



## 11. 合并两个排序好的链表，合并后的链表也是保持顺序 -> MergeTwoSortedLists

稍微动一些脑筋题，不过也是简单题

```java
class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}

public class MergeTwoSortedLists {

    public static void main(String[] args) {
        ListNode a = new ListNode(1,new ListNode(3,null));
        ListNode b = new ListNode(2,new ListNode(4,null));
        System.out.println(mergeTwoLists(a, b));
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 声明一个空的 ListNode 对象，作为要返回的对象
        ListNode dummy = new ListNode(0);
        // 复制这个要返回的对象 (内存地址复制)
        ListNode head = dummy;
        // 循环方法传入的 ListNode，直到一方为null未知
        while(l1 != null && l2 != null){
            // 进行比较，如果l1.val < l2.val 那么就把head的 next 指向这个 l1.val
            if(l1.val < l2.val){
                head.next = l1;
                l1 = l1.next;
            } else {
                // 如果是 l1.val >= l2.val 那么就把head的 next 指向这个 l2.val
                head.next = l2;
                l2 = l2.next;
            }
            // 将头结点指向下一个，供下一次循环赋值
            head = head.next;
        }
        // 如果两个ListNode长度不相同，那么就进行直接追加(因为之前的while循环一定会循环到一个的next为null的情况)
        head.next = l1 == null? l2:l1;
        return dummy.next;
    }
}
```



## 12. 外观数列 -> CountAndSay

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



## 13.最大子序和 -> MaximumSubarray

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



## ☆15.二进制求和 ->  BinaryAdd ☆

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



## 16.判断回文字符串(忽略空格 / 标点符号 / 大小写) -> ValidPalindrome

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



## 17.回文字符串2 -> ValidPalindrome2

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



## 18. 速算机器人 -> CalculatorMachine

> 小扣在秋日市集发现了一款速算机器人。店家对机器人说出两个数字（记作 `x` 和 `y`），请小扣说出计算指令：
>
> - `"A"` 运算：使 `x = 2 * x + y`；
> - `"B"` 运算：使 `y = 2 * y + x`。
>
> 在本次游戏中，店家说出的数字为 `x = 1` 和 `y = 0`，小扣说出的计算指令记作仅由大写字母 `A`、`B` 组成的字符串 `s`，字符串中字符的顺序表示计算顺序，请返回最终 `x` 与 `y` 的和为多少。
>
> **示例 1：**
>
> > 输入：`s = "AB"`
> >
> > 输出：`4`
> >
> > 解释：
> > 经过一次 A 运算后，x = 2, y = 0。
> > 再经过一次 B 运算，x = 2, y = 2。
> > 最终 x 与 y 之和为 4。
>
> **提示：**
>
> - `0 <= s.length <= 10`
> - `s` 由 `'A'` 和 `'B'` 组成

```java
/**
     * 思路 先拆分字符串，然后解析两个运算式，将其结果作为下一次的运算因子
     *
     * 思考： 直接进行暴力枚举 就可解决
     */
    public static int calculate(String s) {
        int x=1,y=0;
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)){
                //"A" 运算：使 x = 2 * x + y；
                //"B" 运算：使 y = 2 * y + x。
                case 'A': x = 2*x + y;break;
                case 'B': y = 2*y +x;break;
                default: x = 0;y=0;break;
            }
        }
        return x+y;
    }

    /**
     * 然后仔细思考一下......
     * wtf???
     * 这不是二进制运算转十进制么？？？？？
     *
     * 好家伙，我直接 1 << s.length()
     */
    public static int calculate1(String s) {
        return 1 << s.length();
    }
```



## 19. 早餐组合 -> BreakfastCombination

> 小扣在秋日市集选择了一家早餐摊位，一维整型数组 `staple` 中记录了每种主食的价格，一维整型数组 `drinks` 中记录了每种饮料的价格。小扣的计划选择一份主食和一款饮料，且花费不超过 `x` 元。请返回小扣共有多少种购买方案。
>
> 注意：答案需要以 `1e9 + 7 (1000000007)` 为底取模，如：计算初始结果为：`1000000008`，请返回 `1`
>
> **示例 1：**
>
> > 输入：`staple = [10,20,5], drinks = [5,5,2], x = 15`
> >
> > 输出：`6`
> >
> > 解释：小扣有 6 种购买方案，所选主食与所选饮料在数组中对应的下标分别是：
> > 第 1 种方案：staple[0] + drinks[0] = 10 + 5 = 15；
> > 第 2 种方案：staple[0] + drinks[1] = 10 + 5 = 15；
> > 第 3 种方案：staple[0] + drinks[2] = 10 + 2 = 12；
> > 第 4 种方案：staple[2] + drinks[0] = 5 + 5 = 10；
> > 第 5 种方案：staple[2] + drinks[1] = 5 + 5 = 10；
> > 第 6 种方案：staple[2] + drinks[2] = 5 + 2 = 7。
>
> **示例 2：**
>
> > 输入：`staple = [2,1,1], drinks = [8,9,5,1], x = 9`
> >
> > 输出：`8`
> >
> > 解释：小扣有 8 种购买方案，所选主食与所选饮料在数组中对应的下标分别是：
> > 第 1 种方案：staple[0] + drinks[2] = 2 + 5 = 7；
> > 第 2 种方案：staple[0] + drinks[3] = 2 + 1 = 3；
> > 第 3 种方案：staple[1] + drinks[0] = 1 + 8 = 9；
> > 第 4 种方案：staple[1] + drinks[2] = 1 + 5 = 6；
> > 第 5 种方案：staple[1] + drinks[3] = 1 + 1 = 2；
> > 第 6 种方案：staple[2] + drinks[0] = 1 + 8 = 9；
> > 第 7 种方案：staple[2] + drinks[2] = 1 + 5 = 6；
> > 第 8 种方案：staple[2] + drinks[3] = 1 + 1 = 2；
>
> **提示：**
>
> - `1 <= staple.length <= 10^5`
> - `1 <= drinks.length <= 10^5`
> - `1 <= staple[i],drinks[i] <= 10^5`
> - `1 <= x <= 2*10^5`

+ 贪心算法(暴力枚举) 没啥好解释

```java
/**
 * 第一种方案，暴力枚举法
 */
public static int breakfastNumber(int[] staple, int[] drinks, int x) {
    int res = 0;
    for (int i = 0; i < staple.length; i++) {
        for (int j = 0; j < drinks.length; j++) {
            if(staple[i] + drinks[j] <= x){
                res++;
            }
        }
    }
    return res % 1000000007;
}
```

+ 二分查找法妙用

```java
/**
 * 第二种方案 -》 二分查找法
 */
public static int breakfastNumber1(int[] staple, int[] drinks, int x) {
    // 因为是使用 二分查找法，所以必须要让数组有序
    Arrays.sort(staple);
    Arrays.sort(drinks);
    int res = 0;
    for (int i = 0; i < staple.length; i++) {
        int remain = x - staple[i];
        res += searchBreakFast(drinks, remain);
    }
    return res % 1000000007;
}

/**
 * 运用二分查找法
 */
public static int searchBreakFast(int[] drinks, int remain){
    // 二分查找
    int start = 0, end = drinks.length;
    while(start < end){
        int mid = (end - start) /2;
        if(drinks[mid] > remain){
            // 如果大于就锁定在 前半部分
            end = mid;
        } else if (drinks[mid] <= remain){
            start = mid + 1;
        }
    }
    return start;
}
```



## 20. 自制平方根 sqrt函数

> 实现 int sqrt(int x) 函数。
>
> 计算并返回 x 的平方根，其中 x 是非负整数。
>
> 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
>
> 示例 1:
>
> 输入: 4
> 输出: 2
>
> 示例 2:
>
> 输入: 8
> 输出: 2
> 说明: 8 的平方根是 2.82842..., 
>      由于返回类型是整数，小数部分将被舍去。

+ 普通暴力枚举法 (for循环遍历) 时间复杂度： O(n)

```java
/**
     * 自制函数求平方根
     * <p>
     * 提交
     * 输入：2147395600
     * 输出：289398 ×
     * 应该输出： 46340
     * <p>
     * 错误分析： for 循环循环因子i 一开始被声明为int，进行叠乘 / 赋值时超限了，所以输出了289398，改为long后正常输出46340
     * <p>
     * 时间复杂度： O(n)
     */
    public static int mySqrt(int x) {
        int res = 0;
        for (long i = 1; i <= x; i++) {
            if (i * i <= x) {
                res = Integer.parseInt(i + "");
            } else {
                break;
            }
        }
        return res;
    }
```

+ 二分查找法 (妙) 时间复杂度 O(logn)

```java
/**
     * 使用二分查找法 巧妙解决此题
     * <p>
     * 时间复杂度O(logx)
     *
     * 注意： 二分查找
     * ☆ 如果使用int mid = (end - start) / 2;可能会引发死循环问题，
     * 因为当start 和 end 相差 1的状态下， mid为 0 这样会适中转不出去循环
     *
     * 应该替换为 ** int mid = start + (end - start) / 2; **
     * 如果 start = 2 end = 3 则 mid = 1 这样就规避了 mid = 0 触发的死循环问题
     */
    public static int mySqrt1(int x) {
        int start = 0, end = x, res = 0;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if ((long) mid * mid <= x) {
                // 如果中间数平方小于目标数，就进行start指针移位
                res = mid;
                start = mid + 1;
            } else {
                // 如果中间数平方发育目标数，就进行end指针移位
                end = mid - 1;
            }
        }
        return res;
    }
```



## ☆21.爬楼梯问题 -> ClimbingStairs☆

> 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
>
> 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
>
> 注意：给定 n 是一个正整数。
>
> 示例 1：
>
> 输入： 2
> 输出： 2
> 解释： 有两种方法可以爬到楼顶。
>
> 1.  1 阶 + 1 阶
> 2.  2 阶
> 示例 2：
>
> 输入： 3
> 输出： 3
> 解释： 有三种方法可以爬到楼顶。
> 1.  1 阶 + 1 阶 + 1 阶
> 2.  1 阶 + 2 阶
> 3.  2 阶 + 1 阶
>

思考：

我们先想一下每一次要爬楼梯的解题数：

1 -> 1

2 -> 2

3 -> 3

4 -> 5

5 -> 8

仔细一想，这不是斐波那契数列么？(f(n) = f(n -1) + f(n -2))

答案：

1. 动态规划(**时间复杂度 O(n),空间复杂度O(n)**)

```java
    public static int climbStairs1(int n) {
			if(n <= 2){
			    return n;
			}
			// 可以加上缓存功能，防止因为递归调用带来的不必要的计算
			int[] cache = new int[n];
			cache[0] = 1;
			cache[1] = 2;
			for (int i = 2; i < n; i++) {
			    cache[i] = cache[i - 1] + cache[i - 2];
			}

			return cache[n - 1];
    }
```

优化：因为上述数组存储了没什么从的从前值，所以可以优化掉，直接使用变量来存储前值还有前前值 **空间复杂度 O(1)**

```java
public static int climbStairs1(int n) {
        if(n <= 2){
            return n;
        }
        // 上面代码的优化版 优化空间复杂度为 O(1)
        // preOfPre 代表 f(n - 2) pre 代表 f(n -1) cur 代表最终结果
        // 因为我们知道了 f(1) = 1 f(2) = 2 所以要减少不必要的计算，所以直接将 pre / preOfPre 赋值结果
        int pre = 2, preOfPre = 1, cur = 1;
        // 然后我们从 f(3) = f(2) + f(1) 开始计算
        for(int i = 3; i <= n; i++) {
            // f(n -1) + f(n -2)
            cur = pre + preOfPre;
            // 因为要继续下一次循环了，所以要将f(n -2)调整到 f(n -1)的状态
            preOfPre = pre;
            // 因为要继续下一次循环了，所以要将当前的cur结果赋值给 f(n -1) 以供下一次计算 f(n -1) + f(n -2)
            pre = cur;
        }
        return cur;
}
```



## 22. 删除排序链表中的重复元素 -> RemoveDuplicatesLinkedList

> 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
>
> 示例 1:
>
> 输入: 1->1->2
> 输出: 1->2
> 示例 2:
>
> 输入: 1->1->2->3->3
> 输出: 1->2->3

解题方案：

1.使用递归的方案

	+ 进行嵌套递归，如果当前递归的值和下一个相等，那么就将当前递归的下一个值赋值给当前的递归值

```java
public static ListNode deleteDuplicates(ListNode head) {
    if(head == null || head.next == null){
        return head;
    }
    head.next = deleteDuplicates(head.next);
    if(head.val == head.next.val) head = head.next;
    return head;
}
```

2.使用遍历的方案

	+ 创建一个新的链表，内存地址是head的内存地址(两个是同一份) 然后进行遍历这个新的链表

```java
public static ListNode deleteDuplicates1(ListNode head) {
    ListNode tmp = head;
    while(tmp != null && tmp.next != null){
        if(tmp.val == tmp.next.val){
            // 为什么不是 tmp = tmp.next.next? 因为这样什么都没有改变
            tmp.next = tmp.next.next;
        } else {
            tmp = tmp.next;
        }
    }
    return head;
}
```



## ☆23.合并两个有序的数组 -> MergeSortedArray☆

> 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
>
> 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
>
>  
>
> 示例 1：
>
> 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
> 输出：[1,2,2,3,5,6]
> 示例 2：
>
> 输入：nums1 = [1], m = 1, nums2 = [], n = 0
> 输出：[1]

刚开始做本题的时候，直接没有思路，唯一的思路是用双指针法进行求解，但是写了一通实在是做不出来，遂看了答案，这才恍然大悟，原来可以这么做，下面看看官方的三种答案：

+ JDK自带API

```java
/**
     * 方法一 : 合并后排序
     * 使用jdk自带的api进行排序
     * 时间复杂度： O((m+n)log(m+n))
     * 最朴素的解法就是将两个数组合并之后再排序。该算法只需要一行(Java是2行)，时间复杂度较差，为O((n + m)\log(n + m))O((n+m)log(n+m))。这是由于这种方法没有利用两个数组本身已经有序这一点。
 */
public static void merge(int[] nums1, int m, int[] nums2, int n) {
    System.arraycopy(nums2, 0, nums1, m, n);
    Arrays.sort(nums1);
    Arrays.stream(nums1).forEach(System.out::println);
}
```



+ 双指针 + 临时数组

```java
/**
 * 方法二： 双指针法
 * 1.声明一个临时的数组tmp，指向有数据的nums1
 * 2.将nums1有数据的复制到这个临时数组tmp
 * 3.声明三个指针，分别指向 nums1 nums2 tmp
 * 4.进行循环判断 如果小于就进行相关赋值
 * 5.如果最后遍历的元素还没有全部赋值完毕要做最后的补充
 * 时间复杂度： O(m+n)
 * 空间复杂度： O(m)
 */
public static void merge1(int[] nums1, int m, int[] nums2, int n) {
    int[] tmp = new int[m];
    System.arraycopy(nums1,0,tmp,0,m);

    int pointer1 = 0,pointer2 = 0, pointer3 = 0;

    while((pointer1 < m)&&(pointer2 < n)){
        nums1[pointer3++] = (tmp[pointer1] < nums2[pointer2]) ? tmp[pointer1++] : nums2[pointer2++];
    }

    if(pointer1 < m){
        System.arraycopy(tmp, pointer1, nums1, pointer1 + pointer2, m + n - pointer1 - pointer2);
    }
    if(pointer2 < n){
        System.arraycopy(nums2, pointer2, nums1, pointer1 + pointer2, m + n - pointer1 - pointer2);
    }
    Arrays.stream(nums1).forEach(System.out::print);
}
```



+ 双指针

```java
/**
 * merge1的简化版，将空间复杂度进一步优化
 * 时间复杂度O(m+n)
 * 空间复杂度O(1)
 */
public static void merge2(int[] nums1, int m, int[] nums2, int n) {

    int pointer1 = m - 1, pointer2 = n - 1, pointer3 = m + n -1;

    while((pointer1 >= 0)&&(pointer2 >= 0)){
        nums1[pointer3--] = (nums1[pointer1] < nums2[pointer2]) ? nums2[pointer2--]: nums1[pointer1--];
    }
    System.arraycopy(nums2, 0, nums1, 0, pointer2 + 1);

    Arrays.stream(nums1).forEach(System.out::print);
}
```

+ 双指针法极致优化版

```java
/**
 * 对于上一步的极致优化版 (代码读起来更容易)
 */
public static void merge3(int[] nums1, int m, int[] nums2, int n) {
    // 获取两个数组合并后的最后一个元素的索引
    int p = m-- + n-- - 1;
    while (m >= 0 && n >= 0) {
        // 进行尾部赋值比较
        nums1[p--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
    }

    // 如果 nums2 还没有遍历完，那么就将没有遍历完的直接赋值(充分利用数组有序的原则)
    while (n >= 0) {
        nums1[p--] = nums2[n--];
    }

    Arrays.stream(nums1).forEach(System.out::print);
}
```



## 24. 重塑矩阵 -> ReShape

> 在MATLAB中，有一个非常有用的函数 reshape，它可以将一个矩阵重塑为另一个大小不同的新矩阵，但保留其原始数据。
>
> 给出一个由二维数组表示的矩阵，以及两个正整数r和c，分别表示想要的重构的矩阵的行数和列数。
>
> 重构后的矩阵需要将原始矩阵的所有元素以相同的行遍历顺序填充。
>
> 如果具有给定参数的reshape操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
>
> 示例 1:
>
> 输入: 
> nums = 
> [[1,2],
>  [3,4]]
> r = 1, c = 4
> 输出: 
> [[1,2,3,4]]
> 解释:
> 行遍历nums的结果是 [1,2,3,4]。新的矩阵是 1 * 4 矩阵, 用之前的元素值一行一行填充新矩阵。
> 示例 2:
>
> 输入: 
> nums = 
> [[1,2],
>  [3,4]]
> r = 2, c = 4
> 输出: 
> [[1,2],
>  [3,4]]
> 解释:
> 没有办法将 2 * 2 矩阵转化为 2 * 4 矩阵。 所以输出原矩阵。
> 注意：
>
> 给定矩阵的宽和高范围在 [1, 100]。
> 给定的 r 和 c 都是正数。

一道经典的矩阵问题，本题的目的是将原矩阵转换成一个新的矩阵，比较有意思，但是实现起来比较简单，就像整数回文一样

思路大致为：

先判断原数组的元素是否和要转换的矩阵元素数量是否相等，如果不相等就直接返回原矩阵

如果相等，就遍历循环原数组的长(length)和宽(width)的乘积，循环因子为x

那么对于原数组的映射为：

​	nums[一维] [二维]：

​			[一维]： x / width

​			[二维]： x % width

对于新矩阵的映射为(c为新矩阵的宽)：

​	res[一维] [二维]:

​		[一维]：x / c

​		[二维] :  x % c

所以答案应该为：

```java
public static int[][] matrixReshape(int[][] nums, int r, int c) {
    int length = nums.length;
    int width = nums[0].length;
    if(length * width != r * c){
        return nums;
    }
    int[][] res = new int[r][c];
    // 将数组进行转换处理
    for (int x = 0; x < length*width; x++) {
        res[x/c][x%c] = nums[x/width][x%width];
    }
    return res;
}
```



## ☆25.相同的树 -> SameTree☆

> 给你两棵二叉树的根节点 `p` 和 `q` ，编写一个函数来检验这两棵树是否相同。
>
> 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。

对于 二叉树遍历算法可见 [某博客](https://blog.csdn.net/qq_40913465/article/details/110388685)

思路：

​	因为涉及到二叉树的查找，所以肯定涉及到 

  + 深度优先搜索算法(DFS)

    > 通常深度优先搜索法不全部保留结点，扩展完的结点从数据库中弹出删去，这样，一般在数据库中存储的结点数就是深度值，因此它占用空间较少。所以，当搜索树的结点较多，用其它方法易产生内存溢出时，深度优先搜索不失为一种有效的求解方法。
    > 
    >
    > 特点： 深度优先搜素算法：不全部保留结点，占用空间少；有回溯操作，运行速度慢。
    >
    > 算法思路：首先访问该顶点v，然后依次从它的各个未被访问的邻接点出发深度优先搜索遍历图，直至图中所有和v有路径相通的顶点都被访问到。若此时尚有其他顶点未被访问到，则另选一个未被访问的顶点作起始点，重复上述过程，直至所有顶点都被访问到为止。

    思路：

    如果两个二叉树都不为空，那么首先判断它们的根节点的值是否相同，若不相同则两个二叉树一定不同，若相同，再分别判断两个二叉树的左子树是否相同以及右子树是否相同。这是一个递归的过程，因此可以使用深度优先搜索，递归地判断两个二叉树是否相同。

    代码：

    ```java
    /**
     * 本方法涉及到二叉树遍历问题：
     * 本方法使用深度优先搜索算法
     * 时间复杂度：O(min(m,n))
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if(p== null && q == null){
            // 递归终点，如果都为null 那么两个树形结构都相同
            return true;
        }else if(p ==null || q == null){
            // 如果 两个有一个为null 有一个不为null，那么说明两个不相等，就返回false
            return false;
        } else if(p.val != q.val){
            // 如果两个值不相等，那么就是真的不相等
            return false;
        } else {
            return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
        }
    }
    ```

    

  + 广度优先搜索算法(BFS)

    > 广度优先搜索算法，一般需存储产生的所有结点，占用的存储空间要比深度优先搜索大得多，因此，程序设计中，必须考虑溢出和节省内存空间的问题。但广度优先搜索法一般无回溯操作，所以运行速度比深度优先搜索要快些。
    > 
    >
    > 特点： 保留全部结点，占用空间大； 无回溯操作，运行速度快。
    >
    > 算法思路：首先访问起始顶点v，然后由v出发，依次访问v的各个未被访问过的邻接顶点w1,w2,w3….wn，然后再依次访问w1，w2,…,wn的所有未被访问过的邻接顶点，再从这些访问过的顶点出发，再访问它们所有未被访问过的邻接顶点….以此类推，直到所有的顶点都被访问过为止。

    思路：

    使用两个队列分别存储两个二叉树的节点。初始时将两个二叉树的根节点分别加入两个队列。每次从两个队列各取出一个节点，进行如下比较操作。

    1. 比较两个节点的值，如果两个节点的值不相同则两个二叉树一定不同；
    2. 如果两个节点的值相同，则判断两个节点的子节点是否为空，如果只有一个节点的左子节点为空，或者只有一个节点的右子节点为空，则两个二叉树的结构不同，因此两个二叉树一定不同；
    3. 如果两个节点的子节点的结构相同，则将两个节点的非空子节点分别加入两个队列，子节点加入队列时需要注意顺序，如果左右子节点都不为空，则先加入左子节点，后加入右子节点。

    如果搜索结束时两个队列同时为空，则两个二叉树相同。如果只有一个队列为空，则两个二叉树的结构不同，因此两个二叉树不同

    代码：

    ```java
    /**
     * 广度优先搜索算法
     * 我们使用两个队列分别存储两个树节点，然后进行出队列比较
     * 时间复杂度：O(min(m,n))
     */
    public static boolean isSameTree1(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
    
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
    
        queue1.offer(p);
        queue2.offer(q);
        while(!queue1.isEmpty() && !queue2.isEmpty()){
            // 进行判断
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            if(node1.val != node2.val){
                return false;
            }
            TreeNode left1 = node1.left,right1 = node1.right;
            TreeNode left2 = node2.left,right2 = node2.right;
            /**
             * 位异或运算（^）
             * 运算规则是：两个数转为二进制，然后从高位开始比较，如果相同则为0，不相同则为1。
             */
            if(left1== null ^ left2 == null){
                return false;
            }
            if(right1 == null ^ right2 == null){
                return false;
            }
            if(left1 != null){
                queue1.offer(left1);
            }
            if(right1 != null){
                queue1.offer(right1);
            }
            if(left2 != null){
                queue2.offer(left2);
            }
            if(right2 != null){
                queue2.offer(right2);
            }
        }
        return queue1.isEmpty() && queue2.isEmpty();
    }
    ```

    

## ☆26. 对称二叉树 -> SymmetricTree☆

> 给定一个二叉树，检查它是否是镜像对称的。
>
>  
>
> 例如，二叉树 `[1,2,2,3,4,4,3]` 是对称的。
>
> ```
>     1
>    / \
>   2   2
>  / \ / \
> 3  4 4  3
> ```
>
>  
>
> 但是下面这个 `[1,2,2,null,3,null,3]` 则不是镜像对称的:
>
> ```
>     1
>    / \
>   2   2
>    \   \
>    3    3
> ```
>
>  
>
> **进阶：**
>
> 你可以运用递归和迭代两种方法解决这个问题吗？

本题解决方法：

```
对于二叉树的查找，几乎都是基于 DFS BFS 搜索算法 对于本题 "二叉树是否对称"的问题貌似就不再适用了
```

 + 递归

   > 递归思路：
   >
   > 对于此题： 递归的点怎么找？思路如下：
   >
   > 1.怎么判断一棵树是不是对称二叉树？
   > 答案：如果所给根节点，为空，那么是对称。如果不为空的话，当他的左子树与右子树对称时，他对称
   >
   > 2.那么怎么知道左子树与右子树对不对称呢？在这我直接叫为左树和右树
   > 答案：如果左树的左孩子与右树的右孩子对称，左树的右孩子与右树的左孩子对称，那么这个左树和右树就对称。

   ```java
   /**
    * 对于二叉树的查找，几乎都是基于 DFS BFS 搜索算法
    * 对于本题 "二叉树是否对称"的问题貌似就不再适用了
    * 所以 可以使用以下两种方式来解决：
    * 1. 递归 时间复杂度为 O(n)
    * 2. 迭代 时间复杂度为 O(n)
    *
    * 对于此题： 递归的点怎么找？思路如下：
    *
    * 1.怎么判断一棵树是不是对称二叉树？
    * 答案：如果所给根节点，为空，那么是对称。如果不为空的话，当他的左子树与右子树对称时，他对称
    *
    * 2.那么怎么知道左子树与右子树对不对称呢？在这我直接叫为左树和右树
    * 答案：如果左树的左孩子与右树的右孩子对称，左树的右孩子与右树的左孩子对称，那么这个左树和右树就对称。
    */
   public static boolean isSymmetric(TreeNode root) {
       if(root == null) return true;
       // 进行递归调用：
       return checkIfEqual(root,root);
   }
   
   public static boolean checkIfEqual(TreeNode left,TreeNode right){
       if(left == null && right == null) return true;
   
       if(left == null || right == null) return false;
   
       if(left.val != right.val) return false;
   
       return checkIfEqual(left.left,right.right) && checkIfEqual(left.right,right.left);
   }
   ```

   

 + 迭代

   ```java
   /**
    * 迭代：
    * 继续使用 上一题的判断两个二叉树是否相同的思路来解决
    * 使用一个 队列，分别将队列进行 offer poll 然后判断两个值是否不相等，如果不相等 就返回false
    * 时间复杂度为 O(n)
    */
   public static boolean isSymmetric1(TreeNode root) {
       if(root == null) return true;
       return checkIfEqualByQueue(root,root);
   }
   
   public static boolean checkIfEqualByQueue(TreeNode left,TreeNode right){
       Queue<TreeNode> queue = new LinkedList<TreeNode>();
       queue.offer(left);
       queue.offer(right);
       while (!queue.isEmpty()) {
           left = queue.poll();
           right = queue.poll();
           if (left == null && right == null) {
               continue;
           }
           if (left == null || right == null) {
               return false;
           }
   
           if(left.val != right.val){
               return false;
           }
   
           queue.offer(left.left);
           queue.offer(right.right);
   
           queue.offer(left.right);
           queue.offer(right.left);
       }
       return true;
   }
   ```

   