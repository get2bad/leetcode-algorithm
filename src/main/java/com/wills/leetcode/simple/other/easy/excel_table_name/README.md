# [Excel表列名称](https://leetcode-cn.com/problems/excel-sheet-column-title/)<font color=green>简单</font>

给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。

例如：

A -> 1
B -> 2
C -> 3
...
Z -> 26
AA -> 27
AB -> 28 
...

> 示例 1：
>
> 输入：columnNumber = 1
> 输出："A"
> 示例 2：
>
> 输入：columnNumber = 28
> 输出："AB"
> 示例 3：
>
> 输入：columnNumber = 701
> 输出："ZY"
> 示例 4：
>
> 输入：columnNumber = 2147483647
> 输出："FXSHRXW"



拿到这道题，发现是一道很简单的26进制的问题，那么这就简单了，有两种解决方式：

+ 循环

  ```java
  public String convertToTitle(int columnNumber) {
      // 代表进制
      int source = 26;
      // 进制的加数
      int addition = 64;
      StringBuilder sb = new StringBuilder();
      // 循环终止的边界条件，当小于0时
      while(columnNumber > 0){
          int x = (columnNumber - 1) % source + 1;
          sb.append((char)(x + addition));
          columnNumber = (columnNumber - x) / source;
      }
      // 返回最终结果
      return sb.reverse().toString();
  }
  ```

+ 递归

  ```java
  public String convertToTitle1(int columnNumber) {
      // 代表进制
      int source = 26;
      // 进制的加数
      int addition = 64;
      StringBuilder sb = new StringBuilder();
      // 递归终止的边界条件，当小于26时
      if(columnNumber < source){
          return String.valueOf((char)(addition+columnNumber));
      }
      // 求除数
      int i = columnNumber / source;
      // 求余数
      columnNumber = columnNumber % source;
      // 先增加除数转换的字母
      sb.append((char)(addition+columnNumber));
      // 然后递归调用本方法进行循环
      sb.append(convertToTitle(i));
      // 返回最终结果
      return sb.toString();
  }
  ```

  简简单单完成任务！成功AC！

  ![image-20220117160538726](http://image.tinx.top/image-20220117160538726.png)