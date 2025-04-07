public class Code062_UniquePaths {
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        // 从0 0位置出发
        return process(m - 1, n - 1, 0, 0);
    }

    public int process(int row, int col, int r1, int c1) {
        if (r1 == row && c1 == col) {
            return 1;
        }
        int ans = 0;
        if (r1 == row) {
            // 向右走
            ans += process(row, col, r1, c1 + 1);
        } else if (c1 == col) {
            // 下个走
            ans += process(row, col, r1 + 1, c1);
        } else {
            // 向右走
            ans += process(row, col, r1, c1 + 1);
            // 下个走
            ans += process(row, col, r1 + 1, c1);
        }
        return ans;
    }

    public int uniquePaths2(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = 1;
        for (int r1 = m - 1; r1 >= 0; r1--) {
            for (int c1 = n - 1; c1 >= 0; c1--) {
                if (r1 == m - 1) {
                    // 向右走
                    dp[r1][c1] += c1 < n - 1 ? dp[r1][c1 + 1] : 0;
                } else if (c1 == n - 1) {
                    // 下个走
                    dp[r1][c1] += r1 < m - 1 ? dp[r1 + 1][c1] : 0;
                } else {
                    // 向右走
                    dp[r1][c1] += dp[r1][c1 + 1];
                    // 下个走
                    dp[r1][c1] += dp[r1 + 1][c1];
                }
            }
        }

        return dp[0][0];
    }
}
