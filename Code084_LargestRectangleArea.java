import java.util.Stack;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * <p>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 */
public class Code084_LargestRectangleArea {
    public int largestRectangleArea(int[] heights) {
        // 使用单调栈解决
        // 单调栈的顺序是
        // 从栈顶到栈低由大到小
        Stack<Integer> stack = new Stack<>();
        int ans = 0;

        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                int index = stack.pop();
                ans = Math.max(ans, heights[index] * (stack.isEmpty() ? i : i - stack.peek() - 1));
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int index = stack.pop();
            ans = Math.max(ans, heights[index] * (stack.isEmpty() ? heights.length : heights.length - stack.peek() - 1));
        }
        return ans;
    }
}
