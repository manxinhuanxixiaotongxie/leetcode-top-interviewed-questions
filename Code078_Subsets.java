import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 * <p>
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 */
public class Code078_Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        process(nums, 0, new ArrayList<>(), ans);
        return ans;
    }

    private void process(int[] nums, int index, List<Integer> cur, List<List<Integer>> ans) {
        if (index == nums.length) {
            ans.add(cur);
            return;
        }
        process(nums, index + 1, cur, ans);
        List<Integer> newCur = new ArrayList<>(cur);
        newCur.add(nums[index]);
        process(nums, index + 1, newCur, ans);
    }
}
