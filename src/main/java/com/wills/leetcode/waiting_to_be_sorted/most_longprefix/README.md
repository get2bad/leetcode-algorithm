## 10. 字符串数组最长公共前缀 -> MostLongStrPrefix

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

