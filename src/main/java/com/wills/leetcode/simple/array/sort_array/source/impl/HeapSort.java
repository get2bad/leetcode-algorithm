package com.wills.leetcode.simple.array.sort_array.source.impl;

import com.wills.leetcode.simple.array.sort_array.source.Sort;

/**
 * @ClassName HeapSort
 * @Date 2022/4/2 09:41
 * @Author 王帅
 * @Version 1.0
 * @Description
 * 注记图： https://www.runoob.com/wp-content/uploads/2019/03/heapSort.gif
 *
 * 大顶堆排序步骤：
 *
 * 第一步：将无序序列当作完全二叉树处理，并将其构建成大顶堆
 *
 * （1）从最后一个非叶子节点开始向前遍历，逐步构建大顶堆。
 * （2）将待调整父节点的两个子节点先做比较，选出较大值。
 * （3）将选出的较大子结点与父结点的值比较，如果子节点更大，则交换父节点与子节点。
 * （4）将被交换的子节点的位置作为父节点重复（2）和（3）。
 * （5）当构建到根节点时，大顶堆就构成完成了。
 * 第二步：在大顶堆的基础上开始堆排序
 *
 * （1）交换根节点与尾节点，此时最大值就排在尾部了。
 * （2）将待排序节点数减少1（当前待排节点的最大值已经被排列，待排节点减少1个）
 * （3）从根节点开始重构大顶堆，继续重复（1）和（2）
 * （4）当待排序堆的大小变为 1 时，排序完成
 */
public class HeapSort implements Sort {

    @Override
    public int[] sort(int[] nums) {
        // 构建大顶堆
        int len = nums.length;
        int middle = (int) Math.floor(len / 2);
        // [1, 4, 6, 6, 32, 1, 6, 8, 9, 2]
        // [1, 4, 6, 9, 32, 1, 6, 8, 6, 2]
        // [1, 32, 6, 9, 4, 1, 6, 8, 6, 2]
        // [32, 9, 6, 8, 4, 1, 6, 1, 6, 2]
        // 构建小二叉树
        for (int index = middle; index >= 0; index--) {
            heapify(nums, index, len);
        }

        /**
         * 第二步：在大顶堆的基础上开始堆排序
         * （1）交换根节点与尾节点，此时最大值就排在尾部了。
         * （2）将待排序节点数减少1（当前待排节点的最大值已经被排列，待排节点减少1个）
         * （3）从根节点开始重构大顶堆，继续重复（1）和（2）
         * （4）当待排序堆的大小变为 1 时，排序完成
         */
        // 刚进入的 nums值为 依次为
        // [32, 9, 6, 8, 4, 1, 6, 1, 6, 2]
        // [9, 8, 6, 6, 4, 1, 6, 1, 2, 32]
        // [8, 6, 6, 2, 4, 1, 6, 1, 9, 32]
        // [6, 4, 6, 2, 1, 1, 6, 8, 9, 32]
        // [4, 2, 1, 1, 6, 6, 6, 8, 9, 32]
        // [2, 1, 1, 4, 6, 6, 6, 8, 9, 32]
        // [1, 1, 2, 4, 6, 6, 6, 8, 9, 32]
        // 堆排序
        for (int i = len - 1; i > 0; i--) {
            // 交换值
            swap(nums, 0, i);
            // 交换一次就要少一次,因为已经是顺序的了
            len--;
            heapify(nums, 0, len);
        }
        return nums;
    }

    /**
     * 第一步：将无序序列当作完全二叉树处理，并将其构建成大顶堆
     * （1）从最后一个非叶子节点开始向前遍历，逐步构建大顶堆。
     * （2）将待调整父节点的两个子节点先做比较，选出较大值。
     * （3）将选出的较大子结点与父结点的值比较，如果子节点更大，则交换父节点与子节点。
     * （4）将被交换的子节点的位置作为父节点重复（2）和（3）。
     * （5）当构建到根节点时，大顶堆就构成完成了。
     */
    // 堆化 交换
    private void heapify(int[] arr, int middle, int len) {
        // 取两个元素进行和中间值比较 其中 largest 就是 中间索引值
        int left = 2 * middle + 1, right = left + 1, largest = middle;

        // 如果 left 小于 数组总长度 并且 left 的索引值 大于 middle 的索引值
        if (left < len && arr[left] > arr[largest]) {
            largest = left;
        }

        // 如果 right 小于 数组总长度 并且 right 的索引值 大于 middle 的索引值
        if (right < len && arr[right] > arr[largest]) {
            largest = right;
        }
        // ================上面两步的作用就是取这两个值的最大值==================

        // 如果已经发生的改变(就是 结尾两个值比middle大)
        if (largest != middle) {
            // 交换 中间值 和 最大值 的位置
            swap(arr, middle, largest);
            // 因为 largest != middle 说明上面的两个if是有进入过，所以要继续进行堆排序，范围就是 left / right => len
            heapify(arr, largest, len);
        }
    }

    // 就是简单的交换值
    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
