# [最小时间差](https://leetcode-cn.com/problems/minimum-time-difference/)<font color=orange>中等</font>

给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。

> 示例 1：
>
> 输入：timePoints = ["23:59","00:00"]
> 输出：1
> 示例 2：
>
> 输入：timePoints = ["00:00","23:59","00:00"]
> 输出：0

刚开始拿到这道题，没有考虑到有被限制使用 ```SimpleDateFormat```，所以直接使用冒泡算法来求解，时间复杂度为O(n^2)，所以刚开始代码如下：

```java
// 冒泡算法（无法使用 SimpleDateFormat 这个类，所以暂时无法使用）
public static int findMinDifference1(List<String> timePoints) {
    if(timePoints.size() <= 1) return 0;
    String date = "2022-01-01 ";
    int res = Integer.MAX_VALUE;
    try{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (int i = 0; i < timePoints.size(); i++) {
            String preTime = date + timePoints.get(i);
            long preTimeMilles = sdf.parse(preTime).getTime();
            for (int j = i + 1; j < timePoints.size(); j++) {
                String afterTime = date + timePoints.get(j);
                long afterTimeMilles = sdf.parse(afterTime).getTime();
                int difference = Math.abs((int)((preTimeMilles - afterTimeMilles) / 1000 / 1000 / 60));
                res = Math.min(res,difference);
            }
        }
    }catch (Exception e){
        e.printStackTrace();
    }
    return res;
}
```

然后提示找不到这个类。。。。只能找其他出路

![image-20220118105510197](http://rloqc3ngo.hd-bkt.clouddn.com/image-20220118105510197.png)

所以又想到了一个新的出路，就是将小时转换为分钟，然后进行最小值处理，求两者差值，直接返回即可。

```java
public static int findMinDifference(List<String> timePoints) {
    int res = Integer.MAX_VALUE;
    int[] source = new int[timePoints.size()];
    for (int i = 0; i < source.length; i++) {
        String time = timePoints.get(i);
        int hourToMin = Integer.parseInt(time.substring(0,2)) * 60;
        int min = Integer.parseInt(time.substring(3));
        int totalMin = min + hourToMin;
        source[i] = totalMin;
    }
    Arrays.sort(source);
    for (int i = 1; i < source.length; i++) {
        res = Math.min(res,source[i] - source[i - 1]);
    }
    // 在加一层首位的差值 为什么加 1440，因为 1440分钟 = 1天
    res = Math.min(res,source[0] + 1440 - source[source.length - 1]);
    return res;
}
```

最终，终于AC！！！

![image-20220118105640273](http://rloqc3ngo.hd-bkt.clouddn.com/image-20220118105640273.png)

我们来看一下官方的另外一种解法：

```鸽巢```原理：

其实大致还是跟我的解题思路一致，就是求最小值的差值，关键点在最后那个取首尾分钟差。

```java
class Solution {
    public int findMinDifference(List<String> timePoints) {
        int n = timePoints.size();
        if (n > 1440) {
            return 0;
        }
        Collections.sort(timePoints);
        int ans = Integer.MAX_VALUE;
        int t0Minutes = getMinutes(timePoints.get(0));
        int preMinutes = t0Minutes;
        for (int i = 1; i < n; ++i) {
            int minutes = getMinutes(timePoints.get(i));
            ans = Math.min(ans, minutes - preMinutes); // 相邻时间的时间差
            preMinutes = minutes;
        }
        ans = Math.min(ans, t0Minutes + 1440 - preMinutes); // 首尾时间的时间差
        return ans;
    }

    public int getMinutes(String t) {
        return ((t.charAt(0) - '0') * 10 + (t.charAt(1) - '0')) * 60 + (t.charAt(3) - '0') * 10 + (t.charAt(4) - '0');
    }
}
```

