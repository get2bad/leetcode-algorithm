package com.wills.leetcode.simple.other.easy.excel_table_name;

/**
 * @ClassName ExcelTableName
 * @Date 2022/1/17 15:15
 * @Author 王帅
 * @Version 1.0
 * @Description
 * Excel表列名称
 */
public class ExcelTableName {

    public static void main(String[] args) {
        ExcelTableName excel = new ExcelTableName();
        int source = 701;
        String res = excel.convertToTitle(source);
        System.out.println(res);
    }

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


    // 就是一个很简单的26进制问题
    // A = 65
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
}
