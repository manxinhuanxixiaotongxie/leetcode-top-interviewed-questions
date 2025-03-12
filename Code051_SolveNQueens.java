import java.util.ArrayList;
import java.util.List;

public class Code051_SolveNQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        process(new int[n], 0, ans);
        return ans;
    }

    public static int process(int[] n, int index,List<List<String>> result) {
        if (index == n.length) {
            // 将结果放入result中
            List<String> list = new ArrayList<>();
            for (int row = 0; row <n.length;row++) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int col = 0;col <n.length;col++) {
                    if (n[row] == col) {
                        stringBuilder.append("Q");
                    } else {
                        stringBuilder.append(".");
                    }
                }
                list.add(stringBuilder.toString());
            }
            result.add(list);
            return 1;
        }
        // n[0] 表示第0行的皇后放在了n[0]列上
        int ans = 0;
        for (int col = 0; col < n.length; col++) {
            if (isValid(n, index, col)) {
                n[index] = col;
                ans += process(n, index + 1,result);
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

    /**
     * 不能AC
     */
//    public List<List<String>> solveNQueens2(int n) {
//        List<List<String>> ans = new ArrayList<>();
//        process2(new int[n], (1 << n) - 1, 0, 0, 0,ans);
//        return ans;
//    }
//
//    public static int process2(int[] n,int limit, int colLimit, int colLeftLimit, int colRightLimit,List<List<String>> result) {
//        if (colLimit == limit) {
//            // 将结果放入result中
//            List<String> list = new ArrayList<>();
//            for (int row = 0; row <n.length;row++) {
//                StringBuilder stringBuilder = new StringBuilder();
//                for (int col = 0;col <n.length;col++) {
//                    if (n[row] == col) {
//                        stringBuilder.append("Q");
//                    } else {
//                        stringBuilder.append(".");
//                    }
//                }
//                list.add(stringBuilder.toString());
//            }
//            result.add(list);
//            return 1;
//        }
//        // 那么能放的位置是 pos = limit & (~(colLimit | colLeftLimit | colRightLimit))
//        int pos = limit & (~(colLimit | colLeftLimit | colRightLimit));
//        int ans = 0;
//        while (pos != 0) {
//            int mostRightOne = pos & (~pos + 1);
//            pos = pos - mostRightOne;
//            n[n.length - Integer.bitCount(mostRightOne-1) - 1] = Integer.bitCount(mostRightOne-1);
//            ans += process2(n,limit, colLimit | mostRightOne, (colLeftLimit | mostRightOne) << 1, (colRightLimit | mostRightOne) >>> 1,result);
//        }
//        return ans;
//    }
}
