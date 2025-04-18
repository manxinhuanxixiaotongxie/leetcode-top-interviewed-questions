import java.util.Arrays;
import java.util.Map;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * <p>
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * 示例 2：
 * <p>
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * 示例 3：
 * <p>
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 2500
 * -10^4 <= nums[i] <= 10^4
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
 */
public class Code300_LengthOfLIS {
    public int lengthOfLIS(int[] nums) {
        return process(nums, 0, Integer.MIN_VALUE);
    }

    // index到nums.length位置 能够获取到最大的长度
    public int process(int[] nums, int index, int pre) {
        if (index == nums.length) {
            return 0;
        }
        // 第一种情况
        // 当前位置不选
        int p1 = process(nums, index + 1, pre);
        // 第二种情况
        // 当前位置参与选择 但是当前位置的值必须要大于pre
        int p2 = 0;
        if (nums[index] > pre) {
            p2 = process(nums, index + 1, nums[index]) + 1;
        }
        return Math.max(p1, p2);
    }

    /**
     * 迭代解法
     * 思路：
     * 遍历o-j位置的数组 如果当前i是大于之前的某一个数的话那么当前i的就是dp[j] = dp[i]+1
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        int ans = 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    ans = Math.max(ans, dp[i]);
                }
            }
        }
        return ans;
    }


}
