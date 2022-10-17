# [ 根据二叉树创建字符串](https://leetcode-cn.com/problems/construct-string-from-binary-tree/)

你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。

空节点则用一对空括号 "()" 表示。而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。

**示例 1:**

```
输入: 二叉树: [1,2,3,4]
       1
     /   \
    2     3
   /    
  4     

输出: "1(2(4))(3)"

解释: 原本将是“1(2(4)())(3())”，
在你省略所有不必要的空括号对之后，
它将是“1(2(4))(3)”。
```

**示例 2:**

```
输入: 二叉树: [1,2,3,null,4]
       1
     /   \
    2     3
     \  
      4 

输出: "1(2()(4))(3)"

解释: 和第一个示例相似，
除了我们不能省略第一个对括号来中断输入和输出之间的一对一映射关系。
```

题目的意思是子节点需要用`()`来包裹。举例来说，二叉树`[root,left,right]`，则转换为`root(left)(right)`。如果只有`left`为空节点，则输出`root()(right)`；如果只有`right`为空节点则可以忽略右节点的`()`，输出为`root(left)`。

我们知道 遇到二叉树时，肯定躲不开要使用 DFS（深度优先搜索遍历）/BFS（广度优先搜索遍历），在本题我们使用 DFS + BFS 来解决本题。

## DFS

众所周知 DFS使用的是递归算法来解决的，所以我们使用递归的算法来解决本题。

下面方法的核心就是：

1. 声明一个递归方法，先将当前值 放入要返回的 StringBuilder中 
2. 然后进行递归判断，先从左节点开始判断
   1. 如果左节点不为空，则调用本方法进行依次递归，循环1的步骤
   2. 如果左节点为空，但是右节点不为空，那么我们要根据题目的条件，将要返回的字符串增加 () 值
3. 然后进行右节点的判断
   1. 如果右节点不为空，则调用本方法进行依次递归，循环1的步骤
4. 最后追加结束符 )
5. 返回

```java
StringBuilder sb = new StringBuilder();
public String tree2str(TreeNode root) {
    if(root == null) return "";
    dfs(root);
    // 根据题目要求要去掉左右的括号
    return sb.substring(1,sb.length() - 1);
}

private void dfs(TreeNode node){
    sb.append("(");

    // 结点值放到字符串中
    sb.append(node.val);
    // 如果是左节点，那么递归这个方法
    if(node.left != null) dfs(node.left);
    // 这边考虑到题目要求的 如果是 左节点为Null，右节点不为Null的情况下，就要将左节点显示为 ()
    else if (node.right != null) sb.append("()");
    // 如果是右节点，那么直接跳过这个结点，因为题目要求
    if(node.right != null) dfs(node.right);

    sb.append(")");
}
```





## BFS

> 讲实话，我是真的没有想到 BFS还可以解决本题，在借鉴了大佬的答案后，直呼妙不可言！

下面是算法的核心：

1. 先声明一个队列和一个Set，其中队列用来承载我们要进行遍历的这颗二叉树，而Set用于判断结束符 )
2. 将第一个根节点的值放入队列中
3. 然后进行循环，条件为 队列不为空
4. 首先将队列的最后一个值取出，进入判断
   1. 如果Set中存在这个值，说明这个 Node结点已经遍历追加过字符串，我们就要进行结束符的追加 )
   2. 如果不存在这个值，说明是第一次进行遍历的结点，那么我们就要追加 ( 以及 node.val 进去，然后再次进入判断
      1. 首先判断右节点是否为空(为什么要先判断右节点呢？因为这是一个队列，我们每次都会poll出最后的节点(后面会判断左节点，达到先序遍历的 根 -> 左 -> 右的需求)，所以我们先进行右节点的判断)，如果右节点不为空，就追加进队列的最后节点中
      2. 然后判断左节点是否为空，如果不为空，就追加队列的最后节点中，如果为空，就要追加题目要求的 () 字符串
5. 循环结束，截取我们要的结果。

```java
//  BFS 非递归的方式,使用 栈 / 队列的方式构建
public String tree2str1(TreeNode root) {
    if(root == null) return null;
    StringBuilder sb = new StringBuilder();
    Set<TreeNode> endSet = new HashSet<>();
    Deque<TreeNode> source = new ArrayDeque<>();
    source.addLast(root);
    while (!source.isEmpty()) {
        TreeNode lastAddtion = source.pollLast();
        // 判断结束,判断如果是包含，就叠加结束符 )
        if (endSet.contains(lastAddtion)) {
            sb.append(")");
        } else {
            // 为什么要将之前 poll出来的放回去？
            // 因为我们要使用之前的来判断 Sets中是否有这个 有的话叠加结束的括号 )
            source.addLast(lastAddtion);
            sb.append("(");
            sb.append(lastAddtion.val);
            // 为什么要先将 right放进去？ 因为我们每次都是 pollLast，相当于使用了队列来实现算法
            if (lastAddtion.right != null) source.addLast(lastAddtion.right);
            if (lastAddtion.left != null) source.addLast(lastAddtion.left);
            // 这边要判断 left 为空，但是right不为空的情况，因为题目条件中规定了这个情况下，左的要赋值 ()
            else if (lastAddtion.right != null) sb.append("()");
            endSet.add(lastAddtion);
        }
    }
    return sb.substring(1,sb.length());
}
```

