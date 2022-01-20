package com.wills.leetcode.other.middle.stone_game;

/**
 * @ClassName StoneGame
 * @Date 2022/1/20 10:37
 * @Author 王帅
 * @Version 1.0
 * @Description
 * 石头博弈游戏
 */
public class StoneGameIX {

    public static void main(String[] args) {
        int[] source = {5,2,1,4,3};
        System.out.println(stoneGameIX(source));
    }


    /**
     * 为什么要先声明一个长度为3的整数数组？
     * 因为题目要求已经移除的石头价值总和是否可以被3整除，所以一共就有三种结果 0，1，2
     * 步骤：
     * 1. 声明一个新的数组(长度为3)，
     * 2. 分别计算数组内的每一次求模后的结果
     * 3. 我们来探究一下 游戏的情况
     *  i. 我们先看0的数量即 stoneRes[0]对最后获胜方的影响
     *      a. 0 的数量对价值总和没影响，所以 0 的数量只和奇偶性有关，和大小无关
     *  ii. 然后看stoneRes[1]、stoneRes[2]对最后获胜方的影响
     *      a. 如果一开始 Alice 选了1，那么选取的顺序应该是 1,1,2,1,2,1,2,1......
     *      b. 如果一开始 Alice 选了2，那么选取的顺序应该是 2,2,1,2,1,2,1,2......、
     * 总结：
     *  1. 如果0的数量为偶数，因为Alice先手，只要stoneRes[1]和stoneRes[2]都大于0即可，就是Alice赢
     *      {
     *          解释：
     *          为什么 stoneRes[1]和stoneRes[2]都要大于0，假设我们传入的值为
     *          [5,8,2,2,1] => [2,2,2,2,1] 因为Alice先手，所以符合上方第3步的 ii下b的逻辑
     *          依次的顺序为 2,2,1,2,2 由于是Bob移除了最后一个2，导致总价值s=9，被3整除。所以在这种条件下Alice必赢
     *      }
     *  2. 如果0的数量为奇数，相当于1和2的序列里面可以插一个0，因为Alice先手，
     *      只要stoneRes[1]和stoneRes[2]之间绝对之差2,则Alice赢
     *      (
     *          解释：
     *          为什么要绝对值差2，如果序列为 [5,8,2,2,3] => [2,2,2,2,0]
     *          由于 Alice先手，所以 他先拿 2，则 Bob可以拿2，如果Alice想赢肯定要穿插0，
     *          后面Bob无论如何也要拿一个2，所以在这种条件下 Alice必赢
     *      )
     * @param stones
     * @return
     */
    public static boolean stoneGameIX(int[] stones) {
        int[] stoneRes = new int[3];
        for (int stone : stones) {
            stoneRes[stone%3]++;
        }
        // 进行 0 数量的奇偶性判断
        if(stoneRes[0] % 2 == 0){
            // 如果为偶数,就要判断 stoneRes[1] stoneRes[2]是否都大于0，如果都大于0，就返回true即可
            if(stoneRes[1] > 0 && stoneRes[2] > 0) return true;
            return false;
        }
        // 如果为奇数，就要看绝对值差是否大于2
        int difference = Math.abs(stoneRes[1] - stoneRes[2]);
        return difference > 2;
    }
}
