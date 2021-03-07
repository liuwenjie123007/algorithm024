package Week_05;

/**
 * <h1>221. 最大正方形</h1>
 * <a>https://leetcode-cn.com/problems/maximal-square/</a>
 * <h3>解法：动态规划</h3>
 * <p>最优子结构: dp[i，j] 为以dp[i,j]为右下角的正方形的最大边长</p>
 * <p>1. m[i][j] == '1', dp[i,j] = MIN{dp[i-1,j-1], dp[i-1,j], dp[i,j - 1]} + 1</p>
 * <p>2. m[i][j] == '0', dp[i,j] = 0</p>
 * <p>状态空间 int[m][n]</p>
 * <p>时间复杂度 O(mn), 空间复杂度 O(mn) 由于只需要保存上一行状态，可压缩到O(n)</p>
 * @author WENJIE
 */
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        int max = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = matrix[i - 1][j - 1] == '0' ? 0 :
                        (Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1);
                max = Math.max(dp[i][j], max);
            }
        }
        return max * max;
    }
}
