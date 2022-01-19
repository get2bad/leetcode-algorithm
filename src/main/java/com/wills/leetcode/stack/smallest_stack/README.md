# [最小栈](https://leetcode-cn.com/problems/min-stack/)

设计一个支持 `push` ，`pop` ，`top` 操作，并能在常数时间内检索到最小元素的栈。

- `push(x)` —— 将元素 x 推入栈中。
- `pop()` —— 删除栈顶的元素。
- `top()` —— 获取栈顶元素。
- `getMin()` —— 检索栈中的最小元素。 

**示例:**

```
输入：
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

输出：
[null,null,null,null,-3,null,0,-2]

解释：
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.getMin();   --> 返回 -2.
```

本题闹了个大乌龙！我还以为要和之前一样使用数组 / list 来实现一个栈呢，然后就傻乎乎的使用LinkedList，结果遇到了输出最小值的时候运行超时的问题，因为我在```getMin()```函数的时候遍历了整个list，导致超时(因为官方测试用例有一个是push很多元素)，在丈二和尚摸不到头脑的情况下，看了一下答案解析，秒懂！请看下面gif动图，是不是秒懂呢？ 哎 还是太菜了，加油吧！

![](https://assets.leetcode-cn.com/solution-static/155/155_fig1.gif)

下面是我的版本：

```java
class MinStack {
    private List<Integer> stack;
    private List<Integer> minStack;
    private Integer min;
    /** initialize your data structure here. */
    public MinStack() {
        stack = new LinkedList<>();
        minStack = new LinkedList<>();
        min = Integer.MAX_VALUE;
    }
    public void push(int x) {
        stack.add(x);
        if(x < min){
            min = x;
        }
        minStack.add(min);
    }
    public void pop() {
        minStack.remove(minStack.size() - 1);
        stack.remove(stack.size() - 1);
    }
    public int top() {
        return stack.get(stack.size() - 1);
    }
    public int getMin() {
        return minStack.get(minStack.size() - 1);
    }
}
```

答案版本：

```java
class MinStack {

    Deque<Integer> xStack;
    Deque<Integer> minStack;

    public MinStack() {
        xStack = new LinkedList<Integer>();
        minStack = new LinkedList<Integer>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        xStack.push(x);
        minStack.push(Math.min(minStack.peek(), x));
    }

    public void pop() {
        xStack.pop();
        minStack.pop();
    }

    public int top() {
        return xStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
```

