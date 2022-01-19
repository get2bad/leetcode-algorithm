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

