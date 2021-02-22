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

