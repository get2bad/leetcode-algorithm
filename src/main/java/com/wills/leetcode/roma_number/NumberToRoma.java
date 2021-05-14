package com.wills.leetcode.roma_number;

import java.util.HashMap;
import java.util.Map;

public class NumberToRoma {

    public static void main(String[] args) {
        int source = 1994;
        System.out.println(intToRoman(source));
    }

    static int[] intSource = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
    static String[] strSource = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    public static String intToRoman(int num) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < intSource.length; i++) {
            int intRes = intSource[i];
            String strRes = strSource[i];
            while(num >= intRes){
                num -= intRes;
                res.append(strRes);
            }
        }
        return res.toString();
    }
}
