/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 示例 2:
 * <p>
 * 输入: nums = [0]
 * 输出: [0]
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= nums.length <= 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 */
public class Code283_MoveZero {

    /**
     * 暴力解
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        // 题目要求不更改非0 元素的相对位置
        // 并且不能复制数组 在原数组上进行操作
        // 从右到左遍历 遇到0就将0遍历到最后一个为止的0上
        int limit = nums.length-1;
        for (int r = nums.length - 1; r >= 0; r--) {
            if (nums[r] == 0) {
                // 交换到r的最后一个为止
                int temp = r;
                while (temp < limit) {
                    swap(nums, temp, ++temp);
                }
                limit--;
            }
        }
    }

    /**
     *
     * 双指针解法
     *
     * 设计两个指针，一个左指针一个右指针
     * @param nums
     */
    public void moveZeroes2(int[] nums) {
        int l = 0;
        int r = nums.length-1;
        while (l < r) {
            if (nums[r] == 0) {
                r--;
            }else if (nums[l] == 0) {
                swap(nums, l, r);
                l++;
                r--;
            }else {
                swap(nums, l, r);
                r--;
            }
        }
    }

    private void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
