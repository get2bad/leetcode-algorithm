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

