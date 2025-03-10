/**
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * <p>
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 200
 */
public class Code064_MinPathSum {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        return process(grid, grid.length - 1, grid[0].length - 1, 0, 0);
    }

    // 从c1 r1出发到r c的最小路径和
    private int process(int[][] grid, int r, int c, int r1, int c1) {
        if (r1 == r && c1 == c) {
            return grid[r][c];
        }
        if (r1 == r) {
            // 只能向右走
            return grid[r][c1] + process(grid, r, c, r1, c1 + 1);
        }
        if (c1 == c) {
            // 只能向下走
            return grid[r1][c] + process(grid, r, c, r1 + 1, c1);
        }
        // 普遍位置
        // 第一种情况
        // 向下走
        int p1 = grid[r1][c1] + process(grid, r, c, r1 + 1, c1);
        // 第二种情况
        // 向右走
        int p2 = grid[r1][c1] + process(grid, r, c, r1, c1 + 1);
        return Math.min(p1, p2);
    }

    /**
     * 改成动态规划
     *
     * @param grid
     * @return
     */
    public int minPathSum2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int r = grid.length;
        int c = grid[0].length;
        int[][] dp = new int[r][c];
        dp[r - 1][c - 1] = grid[r - 1][c - 1];
        // 最后一行
        for (int i = grid[0].length - 2; i >= 0; i--) {
            dp[r - 1][i] = grid[r - 1][i] + dp[r - 1][i + 1];
        }
        // 最后一列
        for (int i = grid.length - 2; i >= 0; i--) {
            dp[i][c - 1] = grid[i][c - 1] + dp[i + 1][c - 1];
        }
        for (int i = r - 2; i >= 0; i--) {
            for (int j = c - 2; j >= 0; j--) {
                dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
            }
        }
        return dp[0][0];
    }

}
