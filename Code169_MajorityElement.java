import java.util.HashMap;

/**
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= n <= 5 * 10^4
 * -10^9 <= nums[i] <= 1^9
 * <p>
 * <p>
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 */
public class Code169_MajorityElement {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                int count = map.get(num);
                map.put(num, count + 1);
            } else {
                map.put(num, 1);
            }
        }
        int max = 0;
        int result = 0;
        for (Integer key : map.keySet()) {
            if (map.get(key) > max) {
                max = map.get(key);
                result = key;
            }
        }
        return result;
    }

    /**
     * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
     *
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums) {
        int n = nums.length;
        int num = nums[n - 1];
        int l = 0;
        int index = 0;
        while (index < n) {
            if (nums[index] <= num) {
                swap(nums,index++,l++);
            }else {
                index++;
            }
        }
        return nums[n/2];
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
