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

