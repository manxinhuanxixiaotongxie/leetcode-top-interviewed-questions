/**
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
 * <p>
 * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
 * <p>
 * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 10^5
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 如何证明 nums 中至少存在一个重复的数字?
 * 你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？
 */
public class Code287_FindDuplicate {
    /**
     * 使用位图的方式
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int[] index = new int[4000];
        for (int i = 0; i < nums.length; i++) {
            // 取整
            int index1 = nums[i] / 32;
            // 取余
            int index2 = nums[i] % 32;
            if (((index[index1] >> index2) & 1) == 1) {
                return nums[i];
            } else {
                index[index1] = index[index1] | (1 << index2);
            }
        }
        return -1;
    }

    /**
     * 采用链表的相交的思路
     *
     * 使用快慢指针的方式
     *
     * @param nums
     * @return
     */
    public int findDuplicate2(int[] nums) {
        // 将数组中的值想象成链表的节点
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        // 此时slow和fast相遇
        // 让slow回到起点
        fast = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
