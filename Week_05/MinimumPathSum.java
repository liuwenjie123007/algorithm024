package Week_05;

/**
 * <h1>64. 最小路径和</h1>
 * <a>https://leetcode-cn.com/problems/minimum-path-sum/</a>
 * <h3>解法：动态规划</h3>
 * <p>最优子结构:f(i,j) = MIN{f(i - 1, j), f(i, j - 1)} + f(i,j)</p>
 * <p>状态空间 int[m][n]</p>
 * <p>时间复杂度O(n), 空间复杂度O(mn) 每次只需扫描单行，可优化到O(n)</p>
 * @author WENJIE
 */
public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n + 1];
        dp[0] = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 0) {
                    dp[j] = j == 1 ? grid[i][j - 1] : dp[j - 1] + grid[i][j - 1];
                    continue;
                }
                dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j - 1];
            }
        }
        return dp[n];
    }
}
