package com.wills.leetcode.array.sort_array;


import java.util.Arrays;
import java.util.Random;

/**
 * @ClassName SortArray
 * @Date 2022/4/1 09:40
 * @Author 王帅
 * @Version 1.0
 * @Description
 * 排序数组
 */
public class SortArray {

    public static void main(String[] args) {
        int[] nums = {5, 2, 3, 1};
        SortArray solution = new SortArray();
        int[] res = solution.sortArrayByFast(nums);
        System.out.println(Arrays.toString(res));
    }

    // 桶排序
    // 1 <= A.length <= 10000
    // -50000 <= A[i] <= 50000
    // 10_0000
    private static final int OFFSET = 50000;
    public int[] sortArrayByBin(int[] nums) {
        int len = nums.length;
        // 第 1 步：将数据转换为 [0, 10_0000] 区间里的数
        for (int i = 0; i < len; i++) {
            nums[i] += OFFSET;
        }

        // 第 2 步：观察数据，设置桶的个数
        // 步长：步长如果设置成 10 会超出内存限制
        int step = 1000;
        // 桶的个数
        int bucketLen = 10_0000 / step;

        int[][] temp = new int[bucketLen + 1][len];
        int[] next = new int[bucketLen + 1];

        // 第 3 步：分桶
        for (int num : nums) {
            int bucketIndex = num / step;
            temp[bucketIndex][next[bucketIndex]] = num;
            next[bucketIndex]++;
        }

        // 第 4 步：对于每个桶执行插入排序
        for (int i = 0; i < bucketLen + 1; i++) {
            insertionSort(temp[i], next[i] - 1);
        }

        // 第 5 步：从桶里依次取出来
        int[] res = new int[len];
        int index = 0;
        for (int i = 0; i < bucketLen + 1; i++) {
            int curLen = next[i];
            for (int j = 0; j < curLen; j++) {
                res[index] = temp[i][j] - OFFSET;
                index++;
            }
        }
        return res;
    }
    private void insertionSort(int[] arr, int endIndex) {
        for (int i = 1; i <= endIndex; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] > temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    public int[] sortArrayByHeap(int[] nums) {
        int len = nums.length;
        // 将数组整理成堆
        heapify(nums);

        // 循环不变量：区间 [0, i] 堆有序
        for (int i = len - 1; i >= 1; ) {
            // 把堆顶元素（当前最大）交换到数组末尾
            swap(nums, 0, i);
            // 逐步减少堆有序的部分
            i--;
            // 下标 0 位置下沉操作，使得区间 [0, i] 堆有序
            siftDown(nums, 0, i);
        }
        return nums;
    }

    /**
     * 将数组整理成堆（堆有序）
     *
     * @param nums
     */
    private void heapify(int[] nums) {
        int len = nums.length;
        // 只需要从 i = (len - 1) / 2 这个位置开始逐层下移
        for (int i = (len - 1) / 2; i >= 0; i--) {
            siftDown(nums, i, len - 1);
        }
    }

    /**
     * @param nums
     * @param k    当前下沉元素的下标
     * @param end  [0, end] 是 nums 的有效部分
     */
    private void siftDown(int[] nums, int k, int end) {
        while (2 * k + 1 <= end) {
            int j = 2 * k + 1;
            if (j + 1 <= end && nums[j + 1] > nums[j]) {
                j++;
            }
            if (nums[j] > nums[k]) {
                swap(nums, j, k);
            } else {
                break;
            }
            k = j;
        }
    }

    // 快速排序
    public int[] sortArrayByFast(int[] nums) {
        int len = nums.length;
        quickSort(nums, 0, len - 1);
        return nums;
    }

    private void quickSort(int[] nums, int left, int right) {
        // 小区间使用插入排序
        if (right - left <= INSERTION_SORT_THRESHOLD) {
            insertionSort(nums, left, right);
            return;
        }

        int pIndex = partition(nums, left, right);
        quickSort(nums, left, pIndex - 1);
        quickSort(nums, pIndex + 1, right);
    }

    private static final Random RANDOM = new Random();
    private int partition(int[] nums, int left, int right) {
        int randomIndex = RANDOM.nextInt(right - left + 1) + left;
        swap(nums, left, randomIndex);

        // 基准值
        int pivot = nums[left];
        int lt = left;
        // 循环不变量：
        // all in [left + 1, lt] < pivot
        // all in [lt + 1, i) >= pivot
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < pivot) {
                lt++;
                swap(nums, i, lt);
            }
        }
        swap(nums, left, lt);
        return lt;
    }


    // 归并排序
    public int[] sortArrayByMergeSort(int[] nums) {
        int len = nums.length;
        int[] temp = new int[len];
        mergeSort(nums, 0, len - 1, temp);
        return nums;
    }

    /**
     * 对数组 nums 的子区间 [left, right] 进行归并排序
     * @param nums
     * @param left
     * @param right
     * @param temp  用于合并两个有序数组的辅助数组，全局使用一份，避免多次创建和销毁
     */
    // 列表大小等于或小于该大小，将优先于 mergeSort 使用插入排序
    private static final int INSERTION_SORT_THRESHOLD = 7;
    private void mergeSort(int[] nums, int left, int right, int[] temp) {
        // 小区间使用插入排序
        if (right - left <= INSERTION_SORT_THRESHOLD) {
            insertionSort(nums, left, right);
            return;
        }

        int mid = left + (right - left) / 2;
        // Java 里有更优的写法，在 left 和 right 都是大整数时，即使溢出，结论依然正确
        // int mid = (left + right) >>> 1;
        // 折半 先整理左边
        mergeSort(nums, left, mid, temp);
        // 然后整理 中间 -> 结尾
        mergeSort(nums, mid + 1, right, temp);
        // 如果数组的这个子区间本身有序，无需合并
        if (nums[mid] <= nums[mid + 1]) {
            return;
        }
        mergeOfTwoSortedArray(nums, left, mid, right, temp);
    }

    /**
     * 对数组 arr 的子区间 [left, right] 使用插入排序
     *
     * @param arr   给定数组
     * @param left  左边界，能取到
     * @param right 右边界，能取到
     */
    private void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int temp = arr[i];
            int j = i;
            while (j > left && arr[j - 1] > temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    /**
     * 合并两个有序数组：先把值复制到临时数组，再合并回去
     * @param nums 原数组
     * @param left
     * @param mid   [left, mid] 有序，[mid + 1, right] 有序
     * @param right
     * @param temp  全局使用的临时数组
     */
    private void mergeOfTwoSortedArray(int[] nums, int left, int mid, int right, int[] temp) {
        System.arraycopy(nums, left, temp, left, right + 1 - left);

        int i = left;
        int j = mid + 1;

        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {
                nums[k] = temp[j];
                j++;
            } else if (j == right + 1) {
                nums[k] = temp[i];
                i++;
            } else if (temp[i] <= temp[j]) {
                // 注意写成 < 就丢失了稳定性（相同元素原来靠前的排序以后依然靠前）
                nums[k] = temp[i];
                i++;
            } else {
                // temp[i] > temp[j]
                nums[k] = temp[j];
                j++;
            }
        }
    }

    // 插入排序 （时间复杂度： N^2 ）
    // 动图地址
    // https://pic.leetcode-cn.com/710dd138492c0da4324657033971f3bee0355514f2ab2834756c988a90398cbb-file_1585624920301
    public int[] sortArrayByInsert(int[] nums) {
        int len = nums.length;
        // 循环不变量：将 nums[i] 插入到区间 [0, i) 使之成为有序数组
        for (int i = 1; i < len; i++) {
            // 先暂存这个元素，然后之前元素逐个后移，留出空位
            int temp = nums[i];
            int j = i;
            // 注意边界 j > 0 依次右移
            while (j > 0 && nums[j - 1] > temp) {
                nums[j] = nums[j - 1];
                j--;
            }
            // 然后交换至目标的位置
            nums[j] = temp;
        }
        return nums;
    }

    // 选择排序：每一轮选择最小元素交换到未排定部分的开头（类似于 冒泡排序 时间复杂度 n^2）
    public int[] sortArrayByChoose(int[] nums) {
        int len = nums.length;
        // 循环不变量：[0, i) 有序，且该区间里所有元素就是最终排定的样子
        for (int i = 0; i < len - 1; i++) {
            // 选择区间 [i, len - 1] 里最小的元素的索引，交换到下标 i
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            swap(nums, i, minIndex);
        }
        return nums;
    }

    // 暴力法 冒泡排序 (超时)
    public int[] sortArrayByBubble(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if(nums[i] > nums[j]){
                    swap(nums,i,j);
                }
            }
        }
        return nums;
    }

    // 就是简单的交换值
    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
