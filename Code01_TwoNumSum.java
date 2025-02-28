import java.util.HashMap;

public class Code01_TwoNumSum {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length <2) {
            return nums;
        }
        HashMap<Integer,Integer> indexMap = new HashMap<>();
        int[] ans = new int[2];
        for (int i = 0; i < nums.length;i++) {
            if (indexMap.containsKey(target-nums[i])) {
                ans[0] = indexMap.get(target-nums[i]);
                ans[1] = i;
            }
            indexMap.put(nums[i],i);
        }
        return ans;
    }
}
