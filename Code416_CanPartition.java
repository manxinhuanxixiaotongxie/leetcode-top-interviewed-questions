/**
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 */
public class Code416_CanPartition {
    public boolean canPartition(int[] nums) {
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum += nums[i];
        }
        if ((sum & 1) == 1) {
            return false;
        }
        return process(nums, 0, sum >> 1);
    }

    /**
     * 在index到nums.length-1的范围内
     * 如果能够凑齐的话 返回true
     * 不能够凑齐 返回false
     *
     * @param nums
     * @param index
     * @param rest
     * @return
     */
    private boolean process(int[] nums, int index, int rest) {
        if (index == nums.length) {
            return rest == 0;
        }
        boolean ans = false;
        // 第一种情况
        // 当前位置不需要
        ans |= process(nums, index + 1, rest);
        // 当前位置需要 但是是有前提的
        if (rest >= nums[index]) {
            ans |= process(nums, index + 1, rest - nums[index]);
        }
        return ans;
    }

    /**
     * 改成dp
     *
     * @param nums
     * @return
     */
    public boolean canPartition2(int[] nums) {
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum += nums[i];
        }
        if ((sum & 1) == 1) {
            return false;
        }
        sum /= 2;
        boolean[][] dp = new boolean[nums.length + 1][sum + 1];
        dp[nums.length][0] = true;
        for (int index = nums.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= sum; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest >= nums[index]) {
                    dp[index][rest] |= dp[index + 1][rest - nums[index]];
                }
            }
        }

        return dp[0][sum];
    }
}
