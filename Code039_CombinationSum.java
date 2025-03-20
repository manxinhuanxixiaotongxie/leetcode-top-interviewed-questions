import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * <p>
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * <p>
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 */
public class Code039_CombinationSum {
    /**
     * 无重复元素 整数数组
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        process(candidates, 0, new ArrayList<>(), ans, target);
        return ans;
    }

    public void process(int[] candidates, int index, List<Integer> cur, List<List<Integer>> ans, int rest) {
        if (index == candidates.length) {
            if (rest == 0) {
                ans.add(cur);
            }
        } else {
            for (int zhang = 0; zhang * candidates[index] <= rest; zhang++) {
                List<Integer> list = new ArrayList<>(cur);
                for (int i = 0; i < zhang; i++) {
                    list.add(candidates[index]);
                }
                process(candidates, index + 1, list, ans, rest - zhang * candidates[index]);
            }
        }
    }
}
