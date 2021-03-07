package Week_05;

/**
 * <h1>403. 青蛙过河</h1>
 * <a>https://leetcode-cn.com/problems/frog-jump/</a>
 * <h3>方法 ： 动态规划</h3>
 * <p>最优子结构: f(i,k) 第i 个石头能否由位于 j 个石头跳k步跳过来 ,k = stones[i] - stones[j] </p>
 * <p>dp[i][k] = dp[j][k - 1] || dp[j][k] || dp[j][K+1]  </p>
 * <p>时间复杂度 O(n * n)</p>
 * <p>空间复杂度 O(n * n)</p>
 * @author WENJIE
 */
public class FrogJump {
    public boolean canCross(int[] stones) {
        int n = stones.length;
        if(stones[1] != 1) return false;
        boolean[][] dp = new boolean[n][n];
        dp[0][0] = true;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int k = stones[i] - stones[j];
                if (k <= i) {
                    dp[i][k] = dp[j][k - 1] || dp[j][k] || dp[j][k + 1];
                    if (i == n - 1 && dp[i][k]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
