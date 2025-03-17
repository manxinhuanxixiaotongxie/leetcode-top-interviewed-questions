import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * <p>
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= nums.length <= 105
 * -104 <= nums[i] <= 104
 */
public class Code215_FindKthLargest {
    /**
     * 将数组有小到大进行排序
     * 第k大的数就是从右往左进行挑选
     * <p>
     * 改写快排的方式 实现O（N）的算法
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        return process(nums, k, 0, nums.length - 1);
    }


    public int process(int[] nums, int k, int l, int r) {
        if (l == r) {
            return nums[l];
        }
        int radix = (int) (Math.random() * (r - l + 1)) + l;
        int[] partition = partition(nums, l, r, nums[radix]);
        int left = partition[0];
        int right = partition[1];
        if (k - 1 >= left && k - 1 <= right) {
            return nums[k - 1];
        } else if (k - 1 < left) {
            return process(nums, k, l, left - 1);
        } else {
            return process(nums, k, right + 1, r);
        }
    }

    /**
     * @return
     */
    public int[] partition(int[] nums, int l, int r, int num) {
        // 将数组拆分成左边大 右边小的结构
        int index = l;
        r++;
        l--;
        while (index < r) {
            if (nums[index] > num) {
                swap(nums, index++, ++l);
            } else if (nums[index] < num) {
                swap(nums, index, --r);
            } else {
                // 相等
                index++;
            }
        }
        return new int[]{l + 1, r - 1};
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 借助堆
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 题目要求的是第K大的数
        /**
         * 怎么更新呢？
         * 维护一个size大小为k的小根堆
         */
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        // 第三大 那么堆的大小就是k
        for (int i = 0; i < k; i++) {
            queue.add(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] < queue.peek()) {
                continue;
            }
            queue.poll();
            queue.add(nums[i]);
        }
        return queue.peek();
    }
}

