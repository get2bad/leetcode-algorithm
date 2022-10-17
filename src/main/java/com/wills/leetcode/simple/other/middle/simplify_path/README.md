# [简化路径](https://leetcode-cn.com/problems/simplify-path/)<font color=orange>中等</font>

给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。

在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。

请注意，返回的 规范路径 必须遵循下述格式：

始终以斜杠 '/' 开头。
两个目录名之间必须只有一个斜杠 '/' 。
最后一个目录名（如果存在）不能 以 '/' 结尾。
此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
返回简化后得到的 规范路径 。

>  示例 1：
>
> 输入：path = "/home/"
> 输出："/home"
> 解释：注意，最后一个目录名后面没有斜杠。 
>
> 示例 2：
>
> 输入：path = "/../"
> 输出："/"
> 解释：从根目录向上一级是不可行的，因为根目录是你可以到达的最高级。
>
> 示例 3：
>
> 输入：path = "/home//foo/"
> 输出："/home/foo"
> 解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。
>
> 示例 4：
>
> 输入：path = "/a/./b/../../c/"
> 输出："/c"

本题比较简单，大致的步骤就是我们要找到一个合适的数据结构来存储我们切割 ‘/’ 后的元素，在方法的最后追加到返回值中即可，通过总和考量，我发现<font color=red>队列</font>这个数据结构十分适合我们，因为具有先进先出的特性，很容易进行我们的取出文件上层结构，以及追加文件下层结构,下面是代码：

```java
public static String simplifyPath(String path) {
    String[] sourceArr = path.split("/");
    Deque<String> queue = new ArrayDeque<>();
    for (int i = 0; i < sourceArr.length; i++) {
        String current = sourceArr[i];
        if ("..".equals(current)) {
            if (!queue.isEmpty()) {
                queue.pollLast();
            }
        } else if (current.length() > 0 && !".".equals(current)) {
            queue.offer(current);
        }
    }
    StringBuilder sb = new StringBuilder();
    if (queue.isEmpty()) {
        sb.append('/');
    } else {
        while (!queue.isEmpty()) {
            sb.append('/');
            sb.append(queue.poll());
        }
    }
    return sb.toString();
}
```

最终成功AC！

![image-20220106114456061](http://image.tinx.top/image-20220106114456061.png)