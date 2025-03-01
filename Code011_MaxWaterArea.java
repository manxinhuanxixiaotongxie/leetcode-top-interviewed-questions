/**
 * 盛水最多的容器
 *
 *
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 *
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 返回容器可以储存的最大水量。
 *
 * 说明：你不能倾斜容器。
 *
 * 提示：
 *
 * n == height.length
 * 2 <= n <= 10^5
 * 0 <= height[i] <= 10^4
 *
 */
public class Code011_MaxWaterArea {
    public int maxArea(int[] height) {
        if (height == null || height.length <2) {
            return 0;
        }
        int r = height.length-1;
        int l = 0;
        int ans = 0;
        while (l < r) {
            int cur = (r-l)*Math.min(height[l],height[r]);
            ans = Math.max(ans,cur);

            if (height[l] < height[r]) {
                l++;
            }else {
                r--;
            }
        }
        return ans;
    }
}
