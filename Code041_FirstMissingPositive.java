import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * <p>
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,0]
 * 输出：3
 * 解释：范围 [1,2] 中的数字都在数组中。
 * 示例 2：
 * <p>
 * 输入：nums = [3,4,-1,1]
 * 输出：2
 * 解释：1 在数组中，但 2 没有。
 * 示例 3：
 * <p>
 * 输入：nums = [7,8,9,11,12]
 * 输出：1
 * 解释：最小的正数 1 没有出现。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 */
public class Code041_FirstMissingPositive {
    /**
     * 使用辅助结构
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int i = 1; i <= nums.length + 1; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return 1;
    }

    /**
     * 双指针
     * O（1）的解法
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive2(int[] nums) {

        // 数组的长度是nums.length 那么期望放置的值就是1~nums.length+1
        // 使用双指针的意义
        // 如果我们能在0-nums.length范围上凑齐-1-nums.length+1这么多个数
        // 那么最小确实的证书就是nums.length+1
        // 但是如果有某一个数违背了这个原则
        // 比如0-nums.length这个范围上出现了大于nums.length+1的数的话
        // 意味着0-nums.length这个范围上一定会缺失某一个正数，使得最小的正数不是nums.length+1
        // R的含义就是无效区  不满足条件的区域
        // l的含义是 在0-L 左闭右开的范围上 都满足nums[i] = i+1
        // 第一种情况就是 当前值非常大 比R还大  那么缺失的整数一定在0-R之间 已经不可能会超过R
        int l = 0;
        int r = nums.length;
        // nums 的范围是 假设是的0-12 长度13   放的数的期望是  1-13
        // r是到达不了的位置
        while (l < r) {
            if (nums[l] == l + 1) {
                l++;
            } else if (nums[l] > r || nums[l] < l + 1 || nums[nums[l] - 1] == nums[l]) {
                /**
                 *
                 * nums[l] > r 如果当前l的数已经比R要小 假设这时候的R是13 那么缺失的正数一定在1-12的范围之上
                 * 前面已经不够13个数了
                 * nums[l] < l + 1 同理的，l的范围上每一个数都要满足nums[l] = l+1 当时出现一个比较小的数的时候
                 * 说明出现了一个不合规的数 只能把范围缩小 去找缺失的正数
                 *
                 * 还有一种情况就是 如果后面的l已经出现了跟当前l位置相等的数 当前的数其实也是不需要的
                 * nums[nums[l] - 1] == nums[l] 这个位置指的是  l位置放了一个6 但是之前的的5位置上也放了一个6
                 * 举个例子 l==4 来到了4位置  当前l的数是的7 但是6位置上已经放置了7 那么7这个数是不需要的
                 *
                 */
                swap(nums, l, --r);
            } else {
                // 其余情况
                // 其余情况有哪些
                //   l+1 <nums[l] <=r
                // 将l发送到将要去的位置
                // l的当前是7 那么l就应该去到6位置
                swap(nums, l, nums[l] - 1);
            }
        }
        return l + 1;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
