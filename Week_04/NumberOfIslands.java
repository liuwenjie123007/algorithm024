package Week_04;

import java.util.*;

/**
 * <h1>200. 岛屿数量</h1>
 * <a>https://leetcode-cn.com/problems/number-of-islands/</a>
 * <h3>解法：广度优先搜索</h3>
 * <p>时间复杂度O(NM) 空间复杂度O(NM)</p>
 * @author WENJIE
 */
public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        int num = 0;
        int height = grid.length;
        int width = grid[0].length;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '0') continue;
                Queue<Integer> neighbor = new LinkedList<>();
                grid[i][j] = '0';
                num++;
                neighbor.offer(i * width + j);
                while (!neighbor.isEmpty()) {
                    Integer point = neighbor.poll();
                    int row = point / width;
                    int col = point % width;
                    if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                        neighbor.offer((row - 1) * width + col);
                        grid[row - 1][col] = '0';
                    }

                    if (row + 1 < height && grid[row + 1][col] == '1') {
                        neighbor.offer((row + 1) * width + col);
                        grid[row + 1][col] = '0';
                    }

                    if (col + 1 < width && grid[row][col + 1] == '1') {
                        neighbor.offer(row * width + col + 1);
                        grid[row][col + 1] = '0';
                    }
                    if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                        neighbor.offer(row * width + col - 1);
                        grid[row][col - 1] = '0';
                    }
                }
            }
        }
        return num;
    }
}
