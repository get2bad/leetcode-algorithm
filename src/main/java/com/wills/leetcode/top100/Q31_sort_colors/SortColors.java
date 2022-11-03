package com.wills.leetcode.top100.Q31_sort_colors;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName SortColors
 * @Date 2022/11/3 10:58
 * @Author 王帅
 * @Version 1.0
 * @Description
 */
public class SortColors {

    private static int[] source = {2, 0, 2, 1, 1, 0};

    @Test
    public void test() {
//        sortColorsByBubble(source);
//        sortColorsByChoose(source);
//        sortColorsByInsertion(source);
//        source = sortColorsByMerge(source);
//        sortColorsByHeap(source);
        sortColorsByFast(source);
        System.out.println(Arrays.toString(source));
    }

    /**
     * 给定一个包含红色、白色和蓝色、共n 个元素的数组nums，
     * 原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     * <p>
     * 我们使用整数 0、1 和 2 分别表示红色、白色和蓝色。
     * 说到底就是个排序
     */
    // 快速排序 采用分治法
    public void sortColorsByFast(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    public int[] quickSort(int[] array, int left, int right) {
        if (left < right) {
            // 获取划分子数组的位置
            int position = partition(array, left, right);
            // 左子数组递归调用
            quickSort(array, left, position - 1);
            // 右子数组递归调用
            quickSort(array, position + 1, right);
        }
        return array;
    }

    public int partition(int[] array, int left, int right) {
        // 定义指向比中心元素大的指针，首先指向第一个元素
        int pointer = left;
        // 遍历数组中的所有元素，将比中心元素大的放在右边，比中心元素小的放在左边
        for (int i = left; i < right; i++) {
            // 将比中心元素小的元素和指针指向的元素交换位置
            // 如果第一个元素比中心元素小，这里就是自己和自己交换位置，指针和索引都向下一位移动
            // 如果元素比中心元素大，索引向下移动，指针指向这个较大的元素，直到找到比中心元素小的元素，并交换位置，指针向下移动
            if (array[right] >= array[i]) swap(array,i,pointer++);
        }
        // 将中心元素和指针指向的元素交换位置
        // 将基准值放到中间，用来区分 左边是比基准值小的，右边是比基准值大的
        swap(array,pointer,right);
        return pointer;
    }

    // 堆排序
    public void sortColorsByHeap(int[] nums) {
        // 构建大顶堆
        int len = nums.length;
        int middle = (int) Math.floor(len / 2);
        // [1, 4, 6, 6, 32, 1, 6, 8, 9, 2]
        // [1, 4, 6, 9, 32, 1, 6, 8, 6, 2]
        // [1, 32, 6, 9, 4, 1, 6, 8, 6, 2]
        // [32, 9, 6, 8, 4, 1, 6, 1, 6, 2]
        // 构建小二叉树
        for (int index = middle; index >= 0; index--) heapify(nums, index, len);

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
            heapify(nums, 0, --len);
        }
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

        // 如果 left 的索引值 大于 middle 的索引值
        if (left < len && arr[left] > arr[largest]) largest = left;

        // 如果 right 的索引值 大于 middle 的索引值
        if (right < len && arr[right] > arr[largest]) largest = right;

        // ================上面两步的作用就是取这两个值的最大值==================
        // 如果已经发生的改变(就是 结尾两个值比middle大)
        if (largest != middle) {
            // 交换 中间值 和 最大值 的位置
            swap(arr, middle, largest);
            // 因为 largest != middle 说明上面的两个if是有进入过，所以要继续进行堆排序，范围就是 left / right => len
            heapify(arr, largest, len);
        }
    }


    /**
     * 归并排序
     * 使用递归排序，进行左右分治
     * <p>
     * 归并排序的底层：
     * 1. 使用拷贝数组的方式，对 左 右 数组的第一个元素进行判断，如果 ：
     * 左 > 右 则 result[index++] = right[0] 拷贝数组，从1到最后
     * 左 < 右 则 result[index++] = left[0] 拷贝数组，从1到最后
     */
    public int[] sortColorsByMerge(int[] nums) {
        int len = nums.length;
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(nums, nums.length);
        // 递归终结
        if (len < 2) return arr;
        int middle = (int) Math.floor(len / 2);

        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, len);

        return merge(sortColorsByMerge(left), sortColorsByMerge(right));
    }

    public int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int index = 0;
        while (left.length > 0 && right.length > 0) {
            if (left[0] <= right[0]) {
                result[index++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                result[index++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }

        while (left.length > 0) {
            result[index++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        }

        while (right.length > 0) {
            result[index++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }

        return result;
    }

    // 插入排序
    public void sortColorsByInsertion(int[] nums) {
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            int tmp = nums[i], j = i;
            while (j > 0 && nums[j - 1] < tmp) {
                nums[j] = nums[j - 1];
                j--;
            }
            nums[j] = tmp;
        }
    }

    /**
     * 每次循环，都选取最小的一个，然后在最后进行值交换
     */
    public void sortColorsByChoose(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (nums[j] < nums[minIndex]) minIndex = j;
            }
            swap(nums, i, minIndex);
        }
    }

    // 冒泡排序
    public void sortColorsByBubble(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (nums[i] > nums[j]) swap(nums, i, j);
            }
        }
    }

    public void swap(int[] nums, int index1, int index2) {
        int tmp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = tmp;
    }
}
