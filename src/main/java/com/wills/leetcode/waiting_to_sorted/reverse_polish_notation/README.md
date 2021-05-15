# [逆波兰表达式求值](https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/)

根据[ 逆波兰表示法](https://baike.baidu.com/item/逆波兰式/128437)，求表达式的值。

有效的算符包括 `+`、`-`、`*`、`/` 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。

 

**说明：**

- 整数除法只保留整数部分。
- 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。

 

**示例 1：**

```
输入：tokens = ["2","1","+","3","*"]
输出：9
解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
```

**示例 2：**

```
输入：tokens = ["4","13","5","/","+"]
输出：6
解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
```

**示例 3：**

```
输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
输出：22
解释：
该算式转化为常见的中缀算术表达式为：
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
```



```
思路
将操作数 进栈
例如 {"2","1","+","3","*"}
那么 操作数栈的内容是 3 2 1
1.如果遇到了 操作符 就开始出栈
2.那么流程为 当遇到 + 的时候 出栈 1 和 2 做 + 运算
3.运算完毕，将结果入栈 现在栈的内容是 3 3
4.再遇到了 * 出栈两个数字 做 * 运算 3*3 = 9
5.最终流程就是 3 * （2 + 1）  = 9
```



```java
public static int evalRPN(String[] tokens) {
    Stack<Integer> operator = new Stack<>();
    for (int i = 0; i < tokens.length; i++) {
        if(tokens[i].matches("-?[0-9]+.*[0-9]*")){
            operator.push(Integer.parseInt(tokens[i]));
        } else{
            // 如果遇到了操作符
            // 先判断栈的容量，如果 > 2那么就进行操作，如果 < 2 就直接return 0;
            if(operator.size() >= 2){
                int pre = operator.pop();
                int after = operator.pop();
                switch (tokens[i]){
                    case "+" : operator.push(after + pre);break;
                    case "-" : operator.push(after - pre);break;
                    case "*" : operator.push(after * pre);break;
                    case "/" : operator.push(after / pre);break;
                }
            }
        }
    }
    return operator.pop();
}
```

