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

