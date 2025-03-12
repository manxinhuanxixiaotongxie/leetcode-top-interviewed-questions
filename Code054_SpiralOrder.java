import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 */
public class Code054_SpiralOrder {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        // 使用分圈结构
        int row = matrix.length-1;
        int col = matrix[0].length-1;
        int r1 = 0;
        int c1 = 0;
        while (r1 <= row && c1 <= col) {
            cycle(matrix, r1++, c1++, row--, col--, ans);
        }
        return ans;
    }

    /**
     * [[1, 2, 3,4],
     * [5, 6, 7, 8],
     * [9,10,11,12],
     * [13,14,15,16],
     * [17,18,19,20],
     * [21,22,23,24]]
     *
     *
     * [1,2,3,4,8,12,16,20,24,23,22,21,17,13,9,5,6,7,11,15,19,18,18,14,10,6]
     * [1,2,3,4,8,12,16,20,24,23,22,21,17,13,9,5,6,7,11,15,19,18,   14,10   ]
     *
     * @param matrix
     * @param r1
     * @param c1
     * @param r2
     * @param c2
     * @param ans
     */


    public void cycle(int[][] matrix, int r1, int c1, int r2, int c2, List<Integer> ans) {
        if (r1 == r2) {
            while (c1 <= c2) {
                ans.add(matrix[r1][c1++]);
            }
        } else if (c1 == c2) {
            while (r1 <= r2) {
                ans.add(matrix[r1++][c1]);
            }
        } else {
            // 分圈
            int temp = c1;
            // 从左到右
            while (temp < c2) {
                ans.add(matrix[r1][temp++]);
            }
            temp = r1;
            while (temp < r2) {
                ans.add(matrix[temp++][c2]);
            }
            temp = c2;
            while (temp > c1) {
                ans.add(matrix[r2][temp--]);
            }
            temp = r2;
            while (temp > r1) {
                ans.add(matrix[temp--][c1]);
            }
        }
    }
}
