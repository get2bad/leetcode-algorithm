package com.wills.leetcode.reverse_polish_notation;

import java.util.Stack;

/**
 * @author 王帅
 * @date 2021-03-20 09:50:16
 * @description:
 * 逆波兰表达式求值
 */
public class ReversePolishNotation {

    public static void main(String[] args) {
        String[] source = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        System.out.println(evalRPN(source));
    }

    /**
     * 思路
     * 将操作数 进栈
     * 例如 {"2","1","+","3","*"}
     * 那么 操作数栈的内容是 3 2 1
     * 1.如果遇到了 操作符 就开始出栈
     * 2.那么流程为 当遇到 + 的时候 出栈 1 和 2 做 + 运算
     * 3.运算完毕，将结果入栈 现在栈的内容是 3 3
     * 4.再遇到了 * 出栈两个数字 做 * 运算 3*3 = 9
     * 5.最终流程就是 3 * （2 + 1）  = 9
     */
    public static int evalRPN(String[] tokens) {
        Stack<Integer> operator = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if(tokens[i].matches("-?[0-9]+.*[0-9]*")){
                operator.push(Integer.parseInt(tokens[i]));
            } else{
                // 如果遇到了操作符
                // 先判断栈的容量，如果 > 2那么就进行操作，如果 < 2 就直接return 0;
                if(operator.size() >= 2){
                    int pre = operator.pop();
                    int after = operator.pop();
                    switch (tokens[i]){
                        case "+" : operator.push(after + pre);break;
                        case "-" : operator.push(after - pre);break;
                        case "*" : operator.push(after * pre);break;
                        case "/" : operator.push(after / pre);break;
                    }
                }
            }
        }
        return operator.pop();
    }
}
