# [两个列表的最小索引总和](https://leetcode-cn.com/problems/minimum-index-sum-of-two-lists/)

假设 Andy 和 Doris 想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。

你需要帮助他们用**最少的索引和**找出他们**共同喜爱的餐厅**。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设答案总是存在。

 

**示例 1:**

```
输入: list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
输出: ["Shogun"]
解释: 他们唯一共同喜爱的餐厅是“Shogun”。
```

**示例 2:**

```
输入:list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["KFC", "Shogun", "Burger King"]
输出: ["Shogun"]
解释: 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。
```



## 暴力法

```java
// 暴力法
public static String[] findRestaurant1(String[] list1, String[] list2) {
    List<String> res = new ArrayList<>();
    int minIndex = Integer.MAX_VALUE;
    for (int i = 0; i < list1.length; i++) {
        for (int j = 0; j < list2.length; j++) {
            if(list1[i].equals(list2[j])){
                int total = i + j;
                if(total < minIndex){
                    minIndex = total;
                    res = new ArrayList<>();
                    res.add(list1[i]);
                    continue;
                }
                if(total == minIndex){
                    res.add(list1[i]);
                }
            }
        }
    }
    return res.toArray(new String[res.size()]);
}
```





## 哈希表法

```java
// 哈希表法
public static String[] findRestaurant(String[] list1, String[] list2) {
    List<String> res = new ArrayList<>();
    Map<String,Integer> source = new HashMap<>();
    int minIndex = Integer.MAX_VALUE;
    for (int i = 0; i < list1.length; i++) {
        source.put(list1[i],i);
    }
    for (int i = 0; i < list2.length; i++) {
        String s = list2[i];
        Integer index = source.get(s);
        if(index != null){
            int total = index + i;
            if(total < minIndex){
                res = new ArrayList<>();
                res.add(s);
                minIndex = total;
                continue;
            }
            if(total == minIndex){
                res.add(s);
            }
        }
    }
    return res.toArray(new String[res.size()]);
}
```