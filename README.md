# leetcode-algorithm
> 本代码仓库是本菜鸟刷leetcode的记录，尽量保证每天一题(不保证突然兴起多刷几道题~~~)，奥利给，冲！

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



## 19. 早餐组合

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

