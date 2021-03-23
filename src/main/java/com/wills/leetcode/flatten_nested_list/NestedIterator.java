package com.wills.leetcode.flatten_nested_list;

import com.wills.leetcode.common.NestedInteger;

import java.util.*;

/**
 * @author 王帅
 * @date 2021-03-23 09:28:55
 * @description:
 * 扁平化嵌套列表迭代器
 */
public class NestedIterator implements Iterator<Integer> {

    /**
     * 给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
     *
     * 列表中的每一项或者为一个整数，或者是另一个列表。其中列表的元素也可能是整数或是其他列表。
     *
     * 示例 1:
     * 输入: [[1,1],2,[1,1]]
     * 输出: [1,1,2,1,1]
     * 解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
     *
     * 示例 2:
     * 输入: [1,[4,[6]]]
     * 输出: [1,4,6]
     * 解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,4,6]。
     */
    public static void main(String[] args) {
        List<NestedInteger> nestedList = new ArrayList<>();
        nestedList.add(new NestedInteger() {
            @Override
            public boolean isInteger() {
                return false;
            }

            @Override
            public Integer getInteger() {
                return null;
            }

            @Override
            public List<NestedInteger> getList() {
                return null;
            }
        });
        NestedIterator i = new NestedIterator(nestedList);
        while (i.hasNext()){
            System.out.println(i.next());
        }
    }

    Queue<Integer> queue;

    public NestedIterator(List<NestedInteger> nestedList) {
        queue = new LinkedList<>();
        for (NestedInteger nestedInteger : nestedList) {
            getIndex(nestedInteger);
        }
    }

    /**
     * 现在最关键的就是假如说他的list里面的元素还是带着一个list，所以使用递归来解决
     *
     * 使用递归来返回一个数组
     */
    public void getIndex(NestedInteger res){
        if(!res.isInteger() && res.getList() == null) return;
        if(res.isInteger()) queue.add(res.getInteger());
        if(res.getList() != null){
            for (NestedInteger integer : res.getList()) {
                getIndex(integer);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return queue.size() > 0;
    }

    @Override
    public Integer next() {
        return queue.poll();
    }
}
