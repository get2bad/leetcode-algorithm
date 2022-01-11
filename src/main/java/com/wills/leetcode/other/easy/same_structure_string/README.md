# [同构字符串](https://leetcode-cn.com/problems/isomorphic-strings/)<font color=green>简单</font>

给定两个字符串 s 和 t，判断它们是否是同构的。

如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。

每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。

> 示例 1:
>
> 输入：s = "egg", t = "add"
> 输出：true
> 示例 2：
>
> 输入：s = "foo", t = "bar"
> 输出：false
> 示例 3：
>
> 输入：s = "paper", t = "title"
> 输出：true



简单题，就是使用哈希表来存储映射关系，如果遇到了相同的，对应不上就返回false即可，不难~秒解决，看一下代码：

```java
/**
 * 题目的关键：
 * 就是使用哈希表，对建立映射关系的对象建立映射关系，如果下次遍历的跟映射关系对应不上，就直接返回false否则就是true
 * @param s
 * @param t
 * @return
 */
public static boolean isIsomorphic(String s, String t) {
    if(s.length() != t.length()){
        return false;
    }

    Map<Character, Character> map = new HashMap<>();
    for(int i=0; i<s.length(); i++){
        if(!map.containsKey(s.charAt(i))){
            if(map.containsValue(t.charAt(i))){
                return false;
            }
            map.put(s.charAt(i), t.charAt(i));
        }else{
            if(map.get(s.charAt(i))!=t.charAt(i)){
                return false;
            }
        }
    }

    return true;
}
```

最后简简单单的AC！

![image-20220111140729196](http://image.tinx.top/image-20220111140729196.png)