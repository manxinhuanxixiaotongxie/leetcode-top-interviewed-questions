import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。
 * 请你返回所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * <p>
 * 3 <= nums.length <= 3000
 * -10^5 <= nums[i] <= 10^5
 */
public class Code015_ThreeNumSum {
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }
        // 对数组进行排序
        Arrays.sort(nums);
        // 选择第一个数
        for (int i = 0; i < nums.length ; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                // 固定第一个数之后，剩下的两个数组成-nums[i]的两数之和
                int target = -nums[i];
                // 从 i+1到R为止选择
                int l = i + 1;
                int r = nums.length-1;
                while (l < r) {
                    if (nums[l] + nums[r] > target) {
                        r--;
                    }else if (nums[l] + nums[r] < target) {
                        l++;
                    }else {
                        // 相等
                        if (r==nums.length-1 || nums[r] != nums[r + 1]) {
                            List<Integer> cur = new ArrayList<>();
                            cur.add(nums[i]);
                            cur.add(nums[l]);
                            cur.add(nums[r]);
                            res.add(cur);
                        }
                        r--;
                    }
                }
            }
        }
        return res;
    }

}
