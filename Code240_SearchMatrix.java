/**
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * <p>
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -10^9 <= matrix[i][j] <= 10^9
 * 每行的所有元素从左到右升序排列
 * 每列的所有元素从上到下升序排列
 * -10^9 <= target <= 10^9
 */
public class Code240_SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        boolean ans = false;
        int beginRow = 0;
        int beginCol = matrix[0].length - 1;
        while (beginRow < matrix.length && beginCol >= 0) {
            if (target > matrix[beginRow][beginCol]) {
                beginRow++;
            } else if (target < matrix[beginRow][beginCol]) {
                beginCol--;
            } else {
                return true;
            }
        }
        return ans;
    }
}
