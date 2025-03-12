/**
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * <p>
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 * <p>
 * 提示：
 * <p>
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 *
 *
 *
 */
public class Code048_Rotate {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        int r1 = 0;
        int r2 = matrix.length - 1;
        // 使用分圈结构
        while (r2 > r1) {
            cycle(matrix, r1, r1++, r2, r2--);
        }
    }

    public void cycle(int[][] matrix, int r1, int c1, int r2, int c2) {
        // 交换
        // 左上角的点 r1 c1
        // 右下角的点 r2 c2
        // 总共有多少圈
        int cycle = r2 - r1;
        int index = 0;
        while (index < cycle) {
            int temp = matrix[r1][c1 + index];
            matrix[r1][c1 + index] = matrix[r2 - index][c1];
            matrix[r2 - index][c1] = matrix[r2][c2 - index];
            matrix[r2][c2 - index] = matrix[r1 + index][c2];
            matrix[r1 + index][c2] = temp;
            index++;
        }
    }

}
