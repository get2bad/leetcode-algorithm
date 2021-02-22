## ☆25.相同的树 -> SameTree☆

> 给你两棵二叉树的根节点 `p` 和 `q` ，编写一个函数来检验这两棵树是否相同。
>
> 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。

对于 二叉树遍历算法可见 [某博客](https://blog.csdn.net/qq_40913465/article/details/110388685)

思路：

​	因为涉及到二叉树的查找，所以肯定涉及到

  + 深度优先搜索算法(DFS)

    > 通常深度优先搜索法不全部保留结点，扩展完的结点从数据库中弹出删去，这样，一般在数据库中存储的结点数就是深度值，因此它占用空间较少。所以，当搜索树的结点较多，用其它方法易产生内存溢出时，深度优先搜索不失为一种有效的求解方法。
    >
    >
    > 特点： 深度优先搜素算法：不全部保留结点，占用空间少；有回溯操作，运行速度慢。
    >
    > 算法思路：首先访问该顶点v，然后依次从它的各个未被访问的邻接点出发深度优先搜索遍历图，直至图中所有和v有路径相通的顶点都被访问到。若此时尚有其他顶点未被访问到，则另选一个未被访问的顶点作起始点，重复上述过程，直至所有顶点都被访问到为止。

    思路：

    如果两个二叉树都不为空，那么首先判断它们的根节点的值是否相同，若不相同则两个二叉树一定不同，若相同，再分别判断两个二叉树的左子树是否相同以及右子树是否相同。这是一个递归的过程，因此可以使用深度优先搜索，递归地判断两个二叉树是否相同。

    代码：

    ```java
    /**
     * 本方法涉及到二叉树遍历问题：
     * 本方法使用深度优先搜索算法
     * 时间复杂度：O(min(m,n))
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if(p== null && q == null){
            // 递归终点，如果都为null 那么两个树形结构都相同
            return true;
        }else if(p ==null || q == null){
            // 如果 两个有一个为null 有一个不为null，那么说明两个不相等，就返回false
            return false;
        } else if(p.val != q.val){
            // 如果两个值不相等，那么就是真的不相等
            return false;
        } else {
            return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
        }
    }
    ```



  + 广度优先搜索算法(BFS)

    > 广度优先搜索算法，一般需存储产生的所有结点，占用的存储空间要比深度优先搜索大得多，因此，程序设计中，必须考虑溢出和节省内存空间的问题。但广度优先搜索法一般无回溯操作，所以运行速度比深度优先搜索要快些。
    >
    >
    > 特点： 保留全部结点，占用空间大； 无回溯操作，运行速度快。
    >
    > 算法思路：首先访问起始顶点v，然后由v出发，依次访问v的各个未被访问过的邻接顶点w1,w2,w3….wn，然后再依次访问w1，w2,…,wn的所有未被访问过的邻接顶点，再从这些访问过的顶点出发，再访问它们所有未被访问过的邻接顶点….以此类推，直到所有的顶点都被访问过为止。

    思路：

    使用两个队列分别存储两个二叉树的节点。初始时将两个二叉树的根节点分别加入两个队列。每次从两个队列各取出一个节点，进行如下比较操作。

    1. 比较两个节点的值，如果两个节点的值不相同则两个二叉树一定不同；
    2. 如果两个节点的值相同，则判断两个节点的子节点是否为空，如果只有一个节点的左子节点为空，或者只有一个节点的右子节点为空，则两个二叉树的结构不同，因此两个二叉树一定不同；
    3. 如果两个节点的子节点的结构相同，则将两个节点的非空子节点分别加入两个队列，子节点加入队列时需要注意顺序，如果左右子节点都不为空，则先加入左子节点，后加入右子节点。

    如果搜索结束时两个队列同时为空，则两个二叉树相同。如果只有一个队列为空，则两个二叉树的结构不同，因此两个二叉树不同

    代码：

    ```java
    /**
     * 广度优先搜索算法
     * 我们使用两个队列分别存储两个树节点，然后进行出队列比较
     * 时间复杂度：O(min(m,n))
     */
    public static boolean isSameTree1(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;

        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();

        queue1.offer(p);
        queue2.offer(q);
        while(!queue1.isEmpty() && !queue2.isEmpty()){
            // 进行判断
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            if(node1.val != node2.val){
                return false;
            }
            TreeNode left1 = node1.left,right1 = node1.right;
            TreeNode left2 = node2.left,right2 = node2.right;
            /**
             * 位异或运算（^）
             * 运算规则是：两个数转为二进制，然后从高位开始比较，如果相同则为0，不相同则为1。
             */
            if(left1== null ^ left2 == null){
                return false;
            }
            if(right1 == null ^ right2 == null){
                return false;
            }
            if(left1 != null){
                queue1.offer(left1);
            }
            if(right1 != null){
                queue1.offer(right1);
            }
            if(left2 != null){
                queue2.offer(left2);
            }
            if(right2 != null){
                queue2.offer(right2);
            }
        }
        return queue1.isEmpty() && queue2.isEmpty();
    }
    ```

