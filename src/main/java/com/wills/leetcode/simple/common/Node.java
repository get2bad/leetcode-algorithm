package com.wills.leetcode.simple.common;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @ClassName Node
 * @Date 2022/3/10 11:42
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
@Builder
@Data
public class Node {

    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
