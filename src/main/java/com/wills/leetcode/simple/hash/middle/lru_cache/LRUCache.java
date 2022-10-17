package com.wills.leetcode.simple.hash.middle.lru_cache;

import java.util.*;

/**
 * @ClassName LRUCache
 * @Date 2022/3/25 09:22
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class LRUCache {

    private final int capacity;
    private final Map<Integer,Integer> source;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        // 三个参数，第一个是初始化容量 第二个是增长因子 第三个是否是有序的
        source = new LinkedHashMap<>(capacity,0.75f,true);
    }

    public int get(int key) {
        if (source.containsKey(key)) {
            int value = source.get(key);
            source.remove(key);
            // 保证每次查询后，都在末尾
            source.put(key, value);
            return value;
        }
        return -1;
    }

    public void put(int key, int value) {
        // 进行最少使用的清除
        if (source.containsKey(key)) {
            source.remove(key);
        } else if (source.size() == capacity) {
            // 删除最少使用的第一个
            Iterator<Map.Entry<Integer, Integer>> iterator = source.entrySet().iterator();
            iterator.next();
            iterator.remove();
        }
        // 向尾部插入 key - value
        source.put(key, value);
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(2,1);
        cache.put(1,1);
        cache.put(2,3);
        cache.put(4,1);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }
}
