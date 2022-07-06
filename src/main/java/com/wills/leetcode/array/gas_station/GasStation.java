package com.wills.leetcode.array.gas_station;

import org.junit.Test;

/**
 * @ClassName GasStation
 * @Date 2022/7/6 17:29
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class GasStation {

    private static int[] gas = {1, 2, 3, 4, 5};
    private static int[] cost = {3, 4, 5, 1, 2};

    @Test
    public void test() {
        System.out.println(canCompleteCircuit(gas,cost));
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int rest = 0, run = 0, start = 0;
        for (int i = 0; i < gas.length; ++i){
            run += (gas[i] - cost[i]);
            rest += (gas[i] - cost[i]);
            if (run < 0){
                start = i + 1;
                run = 0;
            }
        }
        return rest < 0 ? -1: start;
    }
}
