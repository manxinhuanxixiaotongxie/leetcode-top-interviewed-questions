import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * <p>
 * 子数组是数组中元素的连续非空序列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 2 * 10^4
 * -1000 <= nums[i] <= 1000
 * -10^7 <= k <= 10^7
 */
public class Code560_SubarraySum {

    /**
     * 暴力解法O（N^2）
     * <p>
     * O(N)解法
     * 必须以i开头 或者以i结尾怎么怎么样
     * <p>
     * 讨论以i结尾的情形
     * 如果以i结尾的子数组的的累加和为k的话 那么以i结尾的子数组满足条件 就是求前面位置有多少个子串的累加和为sum-k
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int ans = 0;
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = nums[i] + sum[i - 1];
        }
        Map<Integer, Integer> sizeMap = new HashMap<>();
        sizeMap.put(0, 1);
        // sizeMap 存放的是前面位置的累加和的数量
        for (int i = 0; i < nums.length; i++) {
            // 以i位置结尾的子数组 要满足累加和为k的 那么前面以i-1位置结尾的子数组就必须要搞定
            // 0位置
            ans += sizeMap.getOrDefault(sum[i]-k,0);
            sizeMap.put(sum[i], sizeMap.getOrDefault(sum[i], 0) + 1);
        }
        return ans;
    }
}
