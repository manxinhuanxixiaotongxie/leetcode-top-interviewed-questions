/**
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 * <p>
 * 每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
 * <p>
 * 0 <= j <= nums[i]
 * i + j < n
 * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= nums.length <= 10^4
 * 0 <= nums[i] <= 1000
 * 题目保证可以到达 nums[n-1]
 */
public class Code045_Jump {
    public int jump(int[] nums) {
        return process(nums, 0);
    }

    public int process(int[] nums, int index) {
        if (index >= nums.length - 1) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        // 当前位置可以跳转的步数
        int step = nums[index];
        for (int i = 1; i <= step; i++) {
            // 递归调用，计算从当前位置跳到下一个位置的最小步数
            int nextStep = process(nums, index + i);
            if (nextStep != Integer.MAX_VALUE) {
                ans = Math.min(ans, nextStep + 1);
            }
        }
        return ans;
    }

    public int jump2(int[] nums) {
        int[] dp = new int[nums.length];
        for (int index = nums.length - 2; index >= 0; index--) {
            int ans = Integer.MAX_VALUE;
            // 当前位置可以跳转的步数
            int step = nums[index];
            for (int i = 1; i <= step; i++) {
                // 递归调用，计算从当前位置跳到下一个位置的最小步数
                int nextStep = index + i <= nums.length - 1 ? dp[index + i] : 0;
                if (nextStep != Integer.MAX_VALUE) {
                    ans = Math.min(ans, nextStep + 1);
                }
            }
            dp[index] = ans;
        }
        return dp[0];
    }


}
