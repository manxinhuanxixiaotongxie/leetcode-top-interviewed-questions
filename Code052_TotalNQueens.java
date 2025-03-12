public class Code052_TotalNQueens {

    public static int totalNQueens(int n) {
        return process(new int[n], 0);
    }

    public static int process(int[] n, int index) {
        if (index == n.length) {
            return 1;
        }
        // n[0] 表示第0行的皇后放在了n[0]列上
        int ans = 0;
        for (int col = 0; col < n.length; col++) {
            if (isValid(n, index, col)) {
                n[index] = col;
                ans += process(n, index + 1);
            }
        }
        return ans;
    }

    public static boolean isValid(int[] n, int row, int col) {
        // 不能共列
        for (int i = 0; i < row; i++) {
            if (n[i] == col) {
                return false;
            }
        }
        // 不能共斜线
        // 不共斜线 表达     Math.abs(row - i) != Math.abs(col - n[i])
        for (int i = 0; i < row; i++) {
            if (Math.abs(row - i) == Math.abs(col - n[i])) {
                return false;
            }
        }
        return true;
    }

    public static int totalNQueens2(int n) {
        int limit = (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    public static int process2(int limit, int colLimit, int colLeftLimit, int colRightLimit) {
        if (colLimit == limit) {
            return 1;
        }
        // 那么能放的位置是 pos = limit & (~(colLimit | colLeftLimit | colRightLimit))
        int pos = limit & (~(colLimit | colLeftLimit | colRightLimit));
        int ans = 0;
        while (pos != 0) {
            int mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            ans += process2(limit, colLimit | mostRightOne, (colLeftLimit | mostRightOne) << 1, (colRightLimit | mostRightOne) >>> 1);
        }
        return ans;
    }

}
