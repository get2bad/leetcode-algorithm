# [比赛中的配对次数](https://leetcode-cn.com/problems/count-of-matches-in-tournament/)<font color=green>简单</font>

给你一个整数 `n` ，表示比赛中的队伍数。比赛遵循一种独特的赛制：

- 如果当前队伍数是 **偶数** ，那么每支队伍都会与另一支队伍配对。总共进行 `n / 2` 场比赛，且产生 `n / 2` 支队伍进入下一轮。
- 如果当前队伍数为 **奇数** ，那么将会随机轮空并晋级一支队伍，其余的队伍配对。总共进行 `(n - 1) / 2` 场比赛，且产生 `(n - 1) / 2 + 1` 支队伍进入下一轮。

返回在比赛中进行的配对次数，直到决出获胜队伍为止。

 

**示例 1：**

```
输入：n = 7
输出：6
解释：比赛详情：
- 第 1 轮：队伍数 = 7 ，配对次数 = 3 ，4 支队伍晋级。
- 第 2 轮：队伍数 = 4 ，配对次数 = 2 ，2 支队伍晋级。
- 第 3 轮：队伍数 = 2 ，配对次数 = 1 ，决出 1 支获胜队伍。
总配对次数 = 3 + 2 + 1 = 6
```

**示例 2：**

```
输入：n = 14
输出：13
解释：比赛详情：
- 第 1 轮：队伍数 = 14 ，配对次数 = 7 ，7 支队伍晋级。
- 第 2 轮：队伍数 = 7 ，配对次数 = 3 ，4 支队伍晋级。 
- 第 3 轮：队伍数 = 4 ，配对次数 = 2 ，2 支队伍晋级。
- 第 4 轮：队伍数 = 2 ，配对次数 = 1 ，决出 1 支获胜队伍。
总配对次数 = 7 + 3 + 2 + 1 = 13
```



刚看这道题，发现这么简单！直接上手撸代码：

```java
public static int numberOfMatches(int n) {
    if(n == 0) return 0;
    int total = 0;
    while(n > 0){
        if(n % 2 == 1 && n != 1){
            // 如果是基数
            n = (n - 1) / 2 + 1;
        }else{
            // 如果是偶数
            n = n / 2;
        }
        total += n;
    }
    return total - 1;
}
```

然后，测试了几个小例子，发现这不对味啊，这特么不就是个减一的问题？来，让我们回顾一下马保国老师的

名言：

> 欸 朋友们好啊 
>
> 我是菜鸡算法掌门人**老王**
>
>  刚才有个朋友问我 
>
> 王老师发生什么事啦 
>
> 我说怎么回事 给我发了几张截图 我一看 
>
> 哦 原来是今天早上 有一道算法题，表演上看着很牛逼， 
>
> 题目说 欸 说是 有队伍要比赛 让你求一求总共比赛多少次，
>
>  王老师你能不能教教我菜鸡功法 
>
> 欸 帮助治疗一下我的不会做 
>
> 我说可以 我说你在力扣练死劲儿不好用 
>
> 他不服气 欸 我说小朋友 你两个手来折我一个手指头 他折不动 他说你这也没用 
>
> 我说我这有用 这是化劲儿 解题功夫是讲化劲儿的 
>
> 四两拨千斤 二百多斤的英国大理石 
>
> 都握不动我这一个手指 他说要和我试试 
>
> 我说可以 
>
> 欸 我一说
>
> 他啪一下就站起来了 很快啊 然后上来就是 一个左正蹬 一个右鞭腿 一个左刺拳 
>
> 气得我直接 return n - 1;秒AC！

```java
// 侮辱智商版
public static int numberOfMatches1(int n) {
    return n - 1;
}
```

![image-20220125111732634](http://rloqc3ngo.hd-bkt.clouddn.com/image-20220125111732634.png)