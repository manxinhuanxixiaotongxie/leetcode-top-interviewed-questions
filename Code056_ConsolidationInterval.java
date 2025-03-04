import java.util.Arrays;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，
 * 并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2：
 * <p>
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10^4
 */
public class Code056_ConsolidationInterval {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        // 定义一个最小值
        int min = intervals[0][0];
        // 定义一个最大值
        int max = intervals[0][1];
        int[][] ans = new int[intervals.length][2];
        int index = 0;
        ans[index][0] = min;
        ans[index][1] = max;
        for (int i = 1; i < intervals.length; i++) {
            int curMin = intervals[i][0];
            int curMax = intervals[i][1];
            if (curMin <= max) {
                // 需要合并
                min = Math.min(min, curMin);
                max = Math.max(max, curMax);
                ans[index][0] = min;
                ans[index][1] = max;
            } else {
                // 不需要合并
                min = curMin;
                max = curMax;
                index++;
                ans[index][0] = min;
                ans[index][1] = max;
            }
        }
        int[][] res = new int[index + 1][2];
        for (int i = 0; i <= index; i++) {
            res[i][0] = ans[i][0];
            res[i][1] = ans[i][1];
        }
        return res;
    }
}
