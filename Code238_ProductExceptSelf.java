/**
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 * <p>
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 * <p>
 * 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,4]
 * 输出: [24,12,8,6]
 * 示例 2:
 * <p>
 * 输入: nums = [-1,1,0,-3,3]
 * 输出: [0,0,9,0,0]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= nums.length <= 10^5
 * -30 <= nums[i] <= 30
 * 输入 保证 数组 answer[i] 在  32 位 整数范围内
 * <p>
 * <p>
 * 进阶：你可以在 O(1) 的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组 不被视为 额外空间。）
 */
public class Code238_ProductExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        // 要求在O（N）的时间复杂度下完成
        // 辅助数组
        int[] left = new int[nums.length - 1];
        int[] right = new int[nums.length - 1];
        int mul = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            mul *= nums[i];
            left[i] = mul;
        }
        mul = 1;
        int index = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            mul *= nums[i];
            right[index++] = mul;
        }
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            // 0 0 0   0  0
            // 0 1 2   3  4
            //   3  2  1  0
            ans[i] = i == 0 ? right[right.length - 1] : i == nums.length - 1 ? left[left.length - 1] :
                    left[i - 1] * right[right.length - 1 - i];
        }

        return ans;
    }

    public static void main(String[] args) {
        Code238_ProductExceptSelf test = new Code238_ProductExceptSelf();
        int[] nums = {0, 0};
        int[] ans = test.productExceptSelf(nums);
        for (int i : ans) {
            System.out.print(i + " ");
        }
    }
}
