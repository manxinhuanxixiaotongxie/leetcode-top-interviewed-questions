/**
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * <p>
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * <p>
 * 你可以认为每种硬币的数量是无限的。
 */
public class Code322_CoinChange {
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        int ans = process(coins, 0, amount);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public int process(int[] coins, int index, int rest) {
        if (index == coins.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }

        if (rest == 0) {
            return 0;
        }

        int ans = Integer.MAX_VALUE;
        // 使用当前货币的张数
        for (int zhang = 0; zhang * coins[index] <= rest; zhang++) {
            int next = process(coins, index + 1, rest - zhang * coins[index]);
            if (next != Integer.MAX_VALUE) {
                ans = Math.min(ans, next + zhang);
            }
        }
        return ans;
    }

    public int coinChange2(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        int N = coins.length;
        int[][] dp = new int[N + 1][amount + 1];
        for (int i = 0; i < N + 1; i++) {
            for (int j = 1; j < amount + 1; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 1; rest <= amount; rest++) {
                int ans = Integer.MAX_VALUE;
                // 使用当前货币的张数
                for (int zhang = 0; zhang * coins[index] <= rest; zhang++) {
                    int next = dp[index + 1][rest - zhang * coins[index]];
                    if (next != Integer.MAX_VALUE) {
                        ans = Math.min(ans, next + zhang);
                    }
                }
                dp[index][rest] = ans;
            }
        }
        return dp[0][amount] == Integer.MAX_VALUE ? -1 : dp[0][amount];
    }

    /**
     * 尝试使用斜率优化进行函数性能优化
     * <p>
     * <p>
     * 2 1 2 4 6 3
     * 0 1 2 3 4 5
     * <p>
     * dp[1][10] = dp[2][10] dp[2][9] dp[2][8] dp[2][7] dp[2][6] 。。。
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange3(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        int N = coins.length;
        int[][] dp = new int[N + 1][amount + 1];
        for (int i = 0; i < N + 1; i++) {
            for (int j = 1; j < amount + 1; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 1; rest <= amount; rest++) {
                int ans = Integer.MAX_VALUE;
                // 使用当前货币的张数
                if (rest - coins[index] >= 0 && dp[index][rest - coins[index]] != Integer.MAX_VALUE) {
                    ans = dp[index][rest - coins[index]] + 1;
                }
                ans = Math.min(ans, dp[index + 1][rest]);
                dp[index][rest] = ans;
            }
        }
        return dp[0][amount] == Integer.MAX_VALUE ? -1 : dp[0][amount];
    }
}
