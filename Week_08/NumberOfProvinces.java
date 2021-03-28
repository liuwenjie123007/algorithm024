package Week_08;

/**
 * <h1>547. 省份数量</h1>
 * <a>https://leetcode-cn.com/problems/number-of-provinces/</a>
 * <h3>解法: 使用并查集: 并查集中根节点数量即省份数量 </h3>
 *
 * @author WENJIE
 */
public class NumberOfProvinces {
    public int findCircleNum(int[][] isConnected) {
        if (isConnected == null || isConnected.length == 0)
            return 0;
        int n = isConnected.length;
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isConnected[i][j] == 1)
                    unionFind.union(i, j);
            }
        }
        return unionFind.count;
    }

    class UnionFind {
        int count;
        int[] parent;

        UnionFind(int n) {
            count = n;
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
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
