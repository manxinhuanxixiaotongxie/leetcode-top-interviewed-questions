/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 */
public class Code200_NumIslands {

    /**
     * 使用并查集  解决岛问题
     *
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        Code200UnionSet unionSet = new Code200UnionSet(grid);
        // 第一行
        for (int col = 1; col < grid[0].length; col++) {
            if (grid[0][col - 1] == '1' && grid[0][col] == '1') {
                unionSet.unionSet(0, col - 1, 0, col);
            }
        }
        // 第一列
        for (int row = 1; row < grid.length; row++) {
            if (grid[row][0] == '1' && grid[row - 1][0] == '1') {
                unionSet.unionSet(row - 1, 0, row, 0);
            }
        }
        // 普遍位置
        for (int row = 1; row < grid.length; row++) {
            for (int col = 1; col < grid[0].length; col++) {
                if (grid[row][col] == '1') {
                    if (grid[row - 1][col] == '1') {
                        unionSet.unionSet(row - 1, col, row, col);
                    }
                    if (grid[row][col - 1] == '1') {
                        unionSet.unionSet(row, col - 1, row, col);
                    }
                }
            }
        }
        return unionSet.getSize();
    }
}


class Code200UnionSet {
    private final int col;
    // 总共有5列 当前位置是1行4列  那么当前的index就是 1*col + 4
    private final int[] sizeMap;
    private final int[] fatherMap;
    private int size = 0;

    Code200UnionSet(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        this.col = col;
        this.sizeMap = new int[row * col];
        this.fatherMap = new int[row * col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    size++;
                }
                int index = getIndex(i, j);
                sizeMap[index] = 1;
                fatherMap[index] = index;
            }
        }
    }

    public void unionSet(int r1, int c1, int r2, int c2) {
        // 合并
        int father1 = findFather(r1, c1);
        int father2 = findFather(r2, c2);
        if (father1 != father2) {
            // 两者的父节点不相同就进行合并
            size--;
            if (sizeMap[father1] > sizeMap[father2]) {
                sizeMap[father1] += sizeMap[father2];
                fatherMap[father2] = father1;
            } else {
                sizeMap[father2] += sizeMap[father1];
                fatherMap[father1] = father2;
            }
        }

    }

    public int getSize() {
        return this.size;
    }


    public int findFather(int row, int col) {
        int index = getIndex(row, col);
        int[] help = new int[this.size];
        int i = 0;
        while (fatherMap[index] != index) {
            help[i++] = index;
            index = fatherMap[index];
        }
        // 此时index就是father
        while (i > 0) {
            fatherMap[help[i - 1]] = index;
            i--;
        }
        return index;
    }

    private int getIndex(int row, int col) {
        return row * this.col + col;
    }
}
