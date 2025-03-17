/**
 * 给你一个满足下述两条属性的 m x n 整数矩阵：
 * <p>
 * 每行中的整数从左到右按非严格递增顺序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -10^4 <= matrix[i][j], target <= 10^4
 */
public class Code074_SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        boolean ans = false;
        int row = 0;
        while (row < matrix.length && matrix[row][0] <= target) {
            row++;
        }
        // 在row-1行开始查找
        if (row == 0) {
            // 在当前行查找
            int col = 0;
            while (col < matrix[row].length) {
                if (matrix[row][col] == target) {
                    ans = true;
                    break;
                }
                col++;
            }
        } else {
            // 在row-1行查找
            int col = 0;
            while (col < matrix[row - 1].length) {
                if (matrix[row - 1][col] == target) {
                    ans = true;
                    break;
                }
                col++;
            }
        }

        return ans;
    }
}
