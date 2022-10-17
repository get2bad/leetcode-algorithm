package com.wills.leetcode.simple.other.middle.smallest_time_difference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName SmallestTimeDifference
 * @Date 2022/1/18 09:19
 * @Author 王帅
 * @Version 1.0
 * @Description
 * 最小时间差值
 */
public class SmallestTimeDifference {

    public static void main(String[] args) {
        List<String> source = new ArrayList<>();
        source.add("23:59");
        source.add("00:00");
        source.add("23:59");
        int difference = findMinDifference(source);
        System.out.println(difference);
    }

    public static int findMinDifference(List<String> timePoints) {
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

    public static int getMinutes(String t) {
        return ((t.charAt(0) - '0') * 10 + (t.charAt(1) - '0')) * 60 + (t.charAt(3) - '0') * 10 + (t.charAt(4) - '0');
    }

    public static int findMinDifference2(List<String> timePoints) {
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
}
