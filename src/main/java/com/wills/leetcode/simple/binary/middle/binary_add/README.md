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