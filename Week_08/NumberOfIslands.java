package Week_08;

/**
 * <h1>200. 岛屿数量</h1>
 * <a>https://leetcode-cn.com/problems/number-of-islands/</a>
 * <h3>解法: 并查集，所有相邻的 1 Union，岛屿数量等于最终根节点数量</h3>
 * @author WENJIE
 */
public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;

        int n = grid.length;
        int m = grid[0].length;

        UnionFind unionFind = new UnionFind(grid);
        int[][] dictionary = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '0')
                    continue;
                for (int[] dir : dictionary) {
                    int x = i + dir[0];
                    int y = j + dir[1];
                    boolean isValidGrid = x >= 0 && x < n && y >= 0 && y < m && grid[x][y] == '1';
                    if (isValidGrid) {
                        int p = i * m + j;
                        int q = x * m + y;
                        unionFind.union(p, q);
                    }
                }
            }
        }

        return unionFind.count;
    }


    class UnionFind {
        int count;
        int[] parent;

        UnionFind(char[][] grid) {
            int n = grid.length;
            int m = grid[0].length;
            count = 0;
            parent = new int[n * m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == '1') {
                        count++;
                        parent[i * m + j] = i * m + j;
                    }
                }
            }
        }

        int find(int p) {
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ)
                return;
            parent[rootP] = rootQ;
            count--;
        }
    }
}
