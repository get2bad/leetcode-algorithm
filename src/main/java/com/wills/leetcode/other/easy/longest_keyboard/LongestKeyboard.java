package com.wills.leetcode.other.easy.longest_keyboard;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class LongestKeyboard {

    public static void main(String[] args) {
        String source = "cbcd";
        int[] keyPressTime = {9,29,49,50};
        char res = slowestKey(keyPressTime, source);
        System.out.println(res);
    }

    /**
     * 简单的循环遍历的问题
     * @param releaseTimes
     * @param keysPressed
     * @return
     */
    public static char slowestKey(int[] releaseTimes, String keysPressed) {
        int maxTime = releaseTimes[0];
        char res = keysPressed.charAt(0);
//        Map<Character,Integer> source = new HashMap<>();
        for (int i = 1; i < releaseTimes.length; i++) {
            char currentChar = keysPressed.charAt(i);
//            Integer preTime = source.getOrDefault(currentChar,0);
//            int time = releaseTimes[i] - releaseTimes[i-1] + preTime;
            int time = releaseTimes[i] - releaseTimes[i-1];
            if(time > maxTime || (time == maxTime) && currentChar > res){
                maxTime = time;
                res = currentChar;
            }
//            source.put(currentChar, time);
        }
        return res;
    }
}
