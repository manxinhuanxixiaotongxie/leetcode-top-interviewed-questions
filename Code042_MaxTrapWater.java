import java.util.Stack;

/**
 * 接雨水问题
 * <p>
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 */
public class Code042_MaxTrapWater {
    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int ans = 0;
        // 使用单调栈解决这个问题
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {

            while (!stack.isEmpty() && height[stack.peek()] <= height[i]) {
                int pop = stack.pop();
                // 计算当前弹出位置可以接的雨水
                if (!stack.isEmpty()) {
                    int left = stack.peek();
                    int right = i;
                    int h = Math.min(height[left], height[right]) - height[pop];
                    // 获取的雨水
                    ans += (right - left - 1) * h;
                }
            }
            stack.push(i);
        }
        return ans;
    }

    /**
     * 采用左右指针的方式
     *
     * <p>
     * 转化一下思路：
     * 其实就是求每个位置能够接到的雨水
     *
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int ans = 0;
        int leftMax = height[0];
        int rightMax = height[height.length - 1];
        int left = 1;
        int right = height.length - 2;
        while (left <= right) {
            // 求当前位置能存放的雨水数量
            if (leftMax < rightMax) {
                // 结算左边
                ans += Math.max(0,leftMax - height[left]);
                leftMax = Math.max(leftMax,height[left]);
                left++;
            } else {
                // 结算右边
                ans += Math.max(0, rightMax - height[right]);
                rightMax = Math.max(rightMax,height[right]);
                right--;
            }
        }

        return ans;
    }
}
