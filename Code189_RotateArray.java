import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右轮转 1 步: [7,1,2,3,4,5,6]
 * 向右轮转 2 步: [6,7,1,2,3,4,5]
 * 向右轮转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 * <p>
 * 输入：nums = [-1,-100,3,99], k = 2
 * 输出：[3,99,-1,-100]
 * 解释:
 * 向右轮转 1 步: [99,-1,-100,3]
 * 向右轮转 2 步: [3,99,-1,-100]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 * 0 <= k <= 10^5
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 尽可能想出更多的解决方案，至少有 三种 不同的方法可以解决这个问题。
 * 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
 */
public class Code189_RotateArray {
    public void rotate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put((i + k) % nums.length, nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = map.get(i);
        }
    }

    /**
     * O(1)的解法
     * @param nums
     * @param k
     */
    public void rotate2(int[] nums, int k) {
        // 假设数组的长度是n 给定一个k
        // 当前位置是i 那么将要去到的位置是(i+k)%n
        // 即当前位置要去到位置是(i+k)%n
        // 总共要填写n-1次
        int times = nums.length;
        int pre = nums[0];
        int preIndex = 0;
        while (times > 0) {
            // 当前位置要交换的下一个位置
            int index = (preIndex + k) % nums.length;
            int temp = nums[index];
            nums[index] = pre;
            pre = temp;
            preIndex = index;
            times--;
        }
    }

    public static void main(String[] args) {
        Code189_RotateArray rotateArray = new Code189_RotateArray();
        int[] nums = new int[]{-1, -100, 3, 99};
        rotateArray.rotate(nums, 2);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
