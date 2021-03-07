package Week_05;

/**
 * <h1>72. 编辑距离</h1>
 * <a>https://leetcode-cn.com/problems/edit-distance/</a>
 * <h3>解法：动态规划</h3>
 * <p>最优子结构: f(i,j) 为 word1 前 i个字符与 word2 前j个字符的编辑距离</p>
 * <p>1. word[i] == word[j] f(i,j) = MIN{f(i - 1,j - 1), f(i - 1, j - 1) + 1, f(i, j - 1) + 1}</p>
 * <p>2. word[i] != word[j] f(i,j) = MIN{f(i - 1,j - 1) + 1, f(i - 1, j - 1) + 1, f(i, j - 1) + 1}</p>
 * <p>状态空间 int[n][m]</p>
 * <p>时间复杂度O(nm), 空间复杂度O(nm)</p>
 * @author WENJIE
 */
public class EditDistance {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        if (n * m == 0)
            return n + m;

        int[][] dp = new int[n + 1][m + 1];
        // 边界情况
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int left = dp[i - 1][j] + 1;
                int down = dp[i][j - 1] + 1;
                int leftDown = dp[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1))
                    leftDown++;
                dp[i][j] = Math.min(left, Math.min(down, leftDown));
            }
        }
        return dp[n][m];
    }
}
