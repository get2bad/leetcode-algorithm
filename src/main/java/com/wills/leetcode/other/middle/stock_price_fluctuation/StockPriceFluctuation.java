package com.wills.leetcode.other.middle.stock_price_fluctuation;

import com.sun.javafx.collections.SortableList;
import javafx.collections.transformation.SortedList;

import java.util.*;

public class StockPriceFluctuation {

    public static void main(String[] args) {
        StockPrice price = new StockPrice();
        price.update(( int)new Date().getTime(),3);
        price.update(( int)new Date().getTime(),4);
        price.update(( int)new Date().getTime(),5);
        System.out.println(price.maximum());
        price.update(( int)new Date().getTime(),3);
        System.out.println(price.minimum());
        System.out.println(price.current());

    }
}


class StockPrice {

    int maxTime = Integer.MIN_VALUE;
    // key: timestamp value: price
    private Map<Integer,Integer> source;
    // key: price value: priceCount
    private TreeMap<Integer,Integer> currentSource;

    // 初始化
    public StockPrice() {
        source = new HashMap<>();
        currentSource = new TreeMap<>();
    }

    public void update(int timestamp, int price) {
        // 找到最大的时间
        maxTime = Math.max(timestamp, maxTime);
        if (source.containsKey(timestamp)) {
            int old = source.get(timestamp);
            int cnt = currentSource.get(old);
            // 判断相同的数目是否到了1，如果到了1 就删除这个
            if (cnt == 1) currentSource.remove(old);
            // 如果没到1，就重新赋值
            else currentSource.put(old, cnt - 1);
        }
        source.put(timestamp, price);
        currentSource.put(price, currentSource.getOrDefault(price, 0) + 1);
    }

    // 直接拿到当前最大时间的价格即可
    public int current() {
        Integer currPrice = source.get(maxTime);
        return currPrice;
    }

    // 由于使用的是有序的，天生可以排序，所以直接拿到最后的值就是最大值
    public int maximum() {
        return currentSource.lastKey();
    }
    // 由于使用的是有序的，天生可以排序，所以直接拿到最开始的值就是最小值
    public int minimum() {
        return currentSource.firstKey();
    }
}
