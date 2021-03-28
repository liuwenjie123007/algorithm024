package Week_08;

import java.util.HashSet;
import java.util.Set;

/**
 * <h1>130. 被围绕的区域</h1>
 * <a>https://leetcode-cn.com/problems/surrounded-regions/</a>
 * <h3>解法: 使用并查集标记与边界相邻的O </h3>
 *
 * @author WENJIE
 */
public class SurroundedRegions {
    public void solve(char[][] board) {
        if (board == null || board.length == 0)
            return;

        UnionFind unionFind = new UnionFind(board);

        int n = board.length;
        int m = board[0].length;
        int[][] dictionary = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        Set<Integer> boardSet = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'O') {
                    for (int[] dir : dictionary) {
                        int x = i + dir[0];
                        int y = j + dir[1];
                        boolean isValid = x >= 0 && x < n && y >= 0 && y < m && board[x][y] == 'O';
                        if (isValid) {
                            int p = i * m + j;
                            int q = x * m + y;
                            unionFind.union(p, q);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'O' && (i == 0 || i == n - 1 || j == 0 || j == m - 1)) {
                    boardSet.add(unionFind.find(i * m + j));
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'O' && !(i == 0 || i == n - 1 || j == 0 || j == m - 1)) {
                    if (!boardSet.contains(unionFind.find(i * m + j))) {
                        board[i][j] = 'X';
                    }
                }
            }
        }
    }

    class UnionFind {
        int count;
        int[] parent;

        UnionFind(char[][] board) {
            int n = board.length;
            int m = board[0].length;
            parent = new int[m * n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == 'O') {
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
