# [ 链表随机节点](https://leetcode-cn.com/problems/linked-list-random-node/)<font color=orange>中等</font>

给你一个单链表，随机选择链表的一个节点，并返回相应的节点值。每个节点 被选中的概率一样 。

实现 Solution 类：

Solution(ListNode head) 使用整数数组初始化对象。
int getRandom() 从链表中随机选择一个节点并返回该节点的值。链表中所有节点被选中的概率相等。


示例：

> 输入
> ["Solution", "getRandom", "getRandom", "getRandom", "getRandom", "getRandom"]
> [[[1, 2, 3]], [], [], [], [], []]
> 输出
> [null, 1, 3, 2, 2, 3]
>
> 解释
> Solution solution = new Solution([1, 2, 3]);
> solution.getRandom(); // 返回 1
> solution.getRandom(); // 返回 3
> solution.getRandom(); // 返回 2
> solution.getRandom(); // 返回 2
> solution.getRandom(); // 返回 3
> // getRandom() 方法应随机返回 1、2、3中的一个，每个元素被返回的概率相等。

当看到这道题的时候，看到 "随机"这个值以后，就知道一定要使用 ```Random``` 这个类来解决，然后我们使用了临时值 + 随机数减至0的方式成功AC，代码如下：

```java
 // 使用临时值的方式
class Solution {

    ListNode source = null;
    int len = 1;

    // 构造方法，从调用方中拿到了一个完整的linkedList链
    public Solution(ListNode head) {
        this.source = head;
        while(head.next != null){
            len++;
            head = head.next;
        }
        System.out.println("链表长度为" + len);
    }

    /**
     * 设计一个，可以随机选取一个链表的算法
     * @return
     */
    public int getRandom() {
        ListNode tmp = source;
        Random random = new Random();
        int index = random.nextInt(len + 1);
        System.out.println("随机索引为："  + index);
        while( tmp != null){
            if(--index <= 0){
                return tmp.val;
            }
            tmp = tmp.next;
        }
        return -1;
    }
}
```

这个是官方的使用List的方式，使用Random + List的方式，比较巧妙

```java
// 使用List的方式
class Solution {

    private List<Integer> source;
    private Random random;

    public Solution(ListNode head) {
        source = new ArrayList<>();
        while(head != null){
            source.add(head.val);
            head = head.next;
        }
        random = new Random();
    }

    public int getRandom() {
        return source.get(random.nextInt(source.size()));
    }
}
```

下面这个代码要着重讲解一下，这部分代码主要使用了蓄水池的方式，根本的原理就是为了更加随机，然后每一次的循环都从1开始，然后依次递增，如果随机数的值为0了，就要返回当前的遍历值，如果不为0，就不记录这个值，最终返回的一定是最随机的值！

```java
//  从链表头开始，遍历整个链表，对遍历到的第 ii 个节点，
//  随机选择区间 [0,i)[0,i) 内的一个整数，如果其等于 00，则将答案置为该节点值，否则答案不变。
class Solution {

    ListNode head;
    Random random;

    public Solution(ListNode head) {
        this.random = new Random();
        this.head = head;
    }

    public int getRandom() {
        int i = 1, ans = 0;
        ListNode tmp = head;
        while(tmp != null){
            if (random.nextInt(i) == 0) {
                ans = tmp.val;
            }
            ++i;
            tmp = tmp.next;
        }
        return ans;
    }
}
```

