import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
 * <p>
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
 * <p>
 * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
 */
public class Code994_OrangesRotting {


    /**
     * 时间复杂度不好
     * @param grid
     * @return
     */
    public int orangesRotting(int[][] grid) {

        int ans = 0;
        Queue<int[]> queue = new LinkedList<>();
        // 将腐烂的橘子放入队列中
        Set<Integer> set = new HashSet<>();
        int colLimit = grid[0].length;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 2) {
                    queue.offer(new int[]{row, col});
                    set.add(getIndex(row, col, colLimit));
                }
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                int[] pop = queue.poll();
                // 弹出的腐烂橘子
                assert pop != null;
                int row = pop[0];
                int col = pop[1];
                if (row == 0 && col == 0) {
                    // 判断右边以及下边位置
                    if (col + 1 < colLimit && grid[row][col + 1] == 1 && !set.contains(getIndex(row, col + 1, colLimit))) {
                        queue.offer(new int[]{row, col + 1});
                        grid[row][col + 1] = 2;
                        set.add(getIndex(row, col + 1, colLimit));
                    }
                    if (row + 1 < grid.length && grid[row + 1][col] == 1 && !set.contains(getIndex(row + 1, col, colLimit))) {
                        queue.offer(new int[]{row + 1, col});
                        grid[row + 1][col] = 2;
                        set.add(getIndex(row + 1, col, colLimit));
                    }
                } else if (col == 0) {
                    // 上 右 下
                    if (row - 1 >= 0 && grid[row - 1][col] == 1 && !set.contains(getIndex(row - 1, col, colLimit))) {
                        queue.offer(new int[]{row - 1, col});
                        grid[row - 1][col] = 2;
                        set.add(getIndex(row - 1, col, colLimit));
                    }
                    if (col + 1 < colLimit && grid[row][col + 1] == 1 && !set.contains(getIndex(row, col + 1, colLimit))) {
                        queue.offer(new int[]{row, col + 1});
                        grid[row][col + 1] = 2;
                        set.add(getIndex(row, col + 1, colLimit));
                    }
                    if (row + 1 < grid.length && grid[row + 1][col] == 1 && !set.contains(getIndex(row + 1, col, colLimit))) {
                        queue.offer(new int[]{row + 1, col});
                        grid[row + 1][col] = 2;
                        set.add(getIndex(row + 1, col, colLimit));
                    }
                    if (row == grid.length-1) {
                        // 最后一行
                        // 右 上
                        if (col + 1 < colLimit && grid[row][col + 1] == 1 && !set.contains(getIndex(row, col + 1, colLimit))) {
                            queue.offer(new int[]{row, col + 1});
                            grid[row][col + 1] = 2;
                            set.add(getIndex(row, col + 1, colLimit));
                        }
                        if (row - 1 >= 0 && grid[row - 1][col] == 1 && !set.contains(getIndex(row - 1, col, colLimit))) {
                            queue.offer(new int[]{row - 1, col});
                            grid[row - 1][col] = 2;
                            set.add(getIndex(row - 1, col, colLimit));
                        }
                    }
                } else if (row == 0) {
                    if (col == grid[0].length - 1) {
                        // 最后一列
                        // 下 左
                        if (row + 1 < grid.length && grid[row + 1][col] == 1 && !set.contains(getIndex(row + 1, col, colLimit))) {
                            queue.offer(new int[]{row + 1, col});
                            grid[row + 1][col] = 2;
                            set.add(getIndex(row + 1, col, colLimit));
                        }
                        if (col - 1 >= 0 && grid[row][col - 1] == 1 && !set.contains(getIndex(row, col - 1, colLimit))) {
                            queue.offer(new int[]{row, col - 1});
                            grid[row][col - 1] = 2;
                            set.add(getIndex(row, col - 1, colLimit));
                        }
                    }
                    // 第一列
                    // 下 右 左
                    if (col - 1 >= 0 && grid[row][col - 1] == 1 && !set.contains(getIndex(row, col - 1, colLimit))) {
                        queue.offer(new int[]{row, col - 1});
                        grid[row][col - 1] = 2;
                        set.add(getIndex(row, col - 1, colLimit));
                    }
                    if (col + 1 < colLimit && grid[row][col + 1] == 1 && !set.contains(getIndex(row, col + 1, colLimit))) {
                        queue.offer(new int[]{row, col + 1});
                        grid[row][col + 1] = 2;
                        set.add(getIndex(row, col + 1, colLimit));
                    }
                    if (row + 1 < grid.length && grid[row + 1][col] == 1 && !set.contains(getIndex(row + 1, col, colLimit))) {
                        queue.offer(new int[]{row + 1, col});
                        grid[row + 1][col] = 2;
                        set.add(getIndex(row + 1, col, colLimit));
                    }

                } else if (row == grid.length - 1 && col == grid[0].length - 1) {
                    // 左 上
                    if (col - 1 >= 0 && grid[row][col - 1] == 1 && !set.contains(getIndex(row, col - 1, colLimit))) {
                        queue.offer(new int[]{row, col - 1});
                        grid[row][col - 1] = 2;
                        set.add(getIndex(row, col - 1, colLimit));
                    }
                    if (row - 1 >= 0 && grid[row - 1][col] == 1 && !set.contains(getIndex(row - 1, col, colLimit))) {
                        queue.offer(new int[]{row - 1, col});
                        grid[row - 1][col] = 2;
                        set.add(getIndex(row - 1, col, colLimit));
                    }
                } else if (row == grid.length - 1) {
                    // 最后一行
                    // 左 上 右
                    if (col - 1 >= 0 && grid[row][col - 1] == 1 && !set.contains(getIndex(row, col - 1, colLimit))) {
                        queue.offer(new int[]{row, col - 1});
                        grid[row][col - 1] = 2;
                        set.add(getIndex(row, col - 1, colLimit));
                    }
                    if (row - 1 >= 0 && grid[row - 1][col] == 1 && !set.contains(getIndex(row - 1, col, colLimit))) {
                        queue.offer(new int[]{row - 1, col});
                        grid[row - 1][col] = 2;
                        set.add(getIndex(row - 1, col, colLimit));
                    }
                    if (col + 1 < colLimit && grid[row][col + 1] == 1 && !set.contains(getIndex(row, col + 1, colLimit))) {
                        queue.offer(new int[]{row, col + 1});
                        grid[row][col + 1] = 2;
                        set.add(getIndex(row, col + 1, colLimit));
                    }
                    if (col == 0) {
                        // 第一列
                        // 上 右
                        if (row - 1 >= 0 && grid[row - 1][col] == 1 && !set.contains(getIndex(row - 1, col, colLimit))) {
                            queue.offer(new int[]{row - 1, col});
                            grid[row - 1][col] = 2;
                            set.add(getIndex(row - 1, col, colLimit));
                        }
                        if (col + 1 < colLimit && grid[row][col + 1] == 1 && !set.contains(getIndex(row, col + 1, colLimit))) {
                            queue.offer(new int[]{row, col + 1});
                            grid[row][col + 1] = 2;
                            set.add(getIndex(row, col + 1, colLimit));
                        }
                    }
                } else if (col == grid[0].length - 1) {
                    // 上 下 左
                    if (row - 1 >= 0 && grid[row - 1][col] == 1 && !set.contains(getIndex(row - 1, col, colLimit))) {
                        queue.offer(new int[]{row - 1, col});
                        grid[row - 1][col] = 2;
                        set.add(getIndex(row - 1, col, colLimit));
                    }
                    if (col - 1 >= 0 && grid[row][col - 1] == 1 && !set.contains(getIndex(row, col - 1, colLimit))) {
                        queue.offer(new int[]{row, col - 1});
                        grid[row][col - 1] = 2;
                        set.add(getIndex(row, col - 1, colLimit));
                    }
                    if (row + 1 < grid.length && grid[row + 1][col] == 1 && !set.contains(getIndex(row + 1, col, colLimit))) {
                        queue.offer(new int[]{row + 1, col});
                        grid[row + 1][col] = 2;
                        set.add(getIndex(row + 1, col, colLimit));
                    }
                    if (row == 0) {
                        // 第一行
                        // 下 左
                        if (row + 1 < grid.length && grid[row + 1][col] == 1 && !set.contains(getIndex(row + 1, col, colLimit))) {
                            queue.offer(new int[]{row + 1, col});
                            grid[row + 1][col] = 2;
                            set.add(getIndex(row + 1, col, colLimit));
                        }
                        if (col - 1 >= 0 && grid[row][col - 1] == 1 && !set.contains(getIndex(row, col - 1, colLimit))) {
                            queue.offer(new int[]{row, col - 1});
                            grid[row][col - 1] = 2;
                            set.add(getIndex(row, col - 1, colLimit));
                        }
                    }
                } else {
                    // 普遍位置
                    // 上 下 左 右
                    if (row - 1 >= 0 && grid[row - 1][col] == 1 && !set.contains(getIndex(row - 1, col, colLimit))) {
                        queue.offer(new int[]{row - 1, col});
                        grid[row - 1][col] = 2;
                        set.add(getIndex(row - 1, col, colLimit));
                    }
                    if (row + 1 < grid.length && grid[row + 1][col] == 1 && !set.contains(getIndex(row + 1, col, colLimit))) {
                        queue.offer(new int[]{row + 1, col});
                        grid[row + 1][col] = 2;
                        set.add(getIndex(row + 1, col, colLimit));
                    }
                    if (col - 1 >= 0 && grid[row][col - 1] == 1 && !set.contains(getIndex(row, col - 1, colLimit))) {
                        queue.offer(new int[]{row, col - 1});
                        grid[row][col - 1] = 2;
                        set.add(getIndex(row, col - 1, colLimit));
                    }
                    if (col + 1 < colLimit && grid[row][col + 1] == 1 && !set.contains(getIndex(row, col + 1, colLimit))) {
                        queue.offer(new int[]{row, col + 1});
                        grid[row][col + 1] = 2;
                        set.add(getIndex(row, col + 1, colLimit));
                    }
                }
                size--;
            }
            ans++;
        }
        // 遍历
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    return -1;
                }
            }
        }
        return ans == 0 ? 0 : ans - 1;
    }

    private int getIndex(int row, int col, int colLimit) {
        return row * colLimit + col;
    }

    public static void main(String[] args) {
        Code994_OrangesRotting orangesRotting = new Code994_OrangesRotting();
        // [[0,0,2,0,2,2,0],[1,0,2,1,2,1,2],[2,1,2,1,0,0,0]]
//        int[][] grid = new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        int[][] grid = new int[][]{{0, 0, 2, 0, 2, 2, 0},
                {1, 0, 2, 1, 2, 1, 2},
                {2, 1, 2, 1, 0, 0, 0}};
        System.out.println(orangesRotting.orangesRotting(grid));
    }
}

