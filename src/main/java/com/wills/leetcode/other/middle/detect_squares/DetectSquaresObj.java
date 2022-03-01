package com.wills.leetcode.other.middle.detect_squares;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DetectSquares
 * @Date 2022/1/26 09:38
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class DetectSquaresObj {
    public static void main(String[] args) {
        DetectSquares detectSquares = new DetectSquares();
        detectSquares.add(new int[]{3, 10});
        detectSquares.add(new int[]{11, 2});
        detectSquares.add(new int[]{3, 2});
        detectSquares.count(new int[]{11, 10});
        //   - 第一个，第二个，和第三个点
        detectSquares.count(new int[]{14, 8});
        detectSquares.add(new int[]{11, 2});
        detectSquares.count(new int[]{11, 10});
    }
}

class DetectSquares {
    int count[][];//记录坐标点为ij的数目
    public DetectSquares() {
        count=new int[1005][1005];
    }

    public void add(int[] point) {
        count[point[0]][point[1]]++;
    }

    public int count(int[] point) {
        int ans=0;
        int a=point[0]+point[1];//对角线斜率为-1的时候坐标和
        int b=point[0]-point[1];//对角线斜率为1的时候x-y
        for(int i=0;i<=1000;i++){
            if(i==point[0]){continue;}//xi坐标是i
            int y1=a-i;
            int y2=i-b;
            if(y1>=0&&y1<=1000){ans+=count[point[0]][y1]*count[i][point[1]]*count[i][y1];}
            if(y2>=0&&y2<=1000){ans+=count[point[0]][y2]*count[i][point[1]]*count[i][y2];}
        }
        return ans;
    }
}
