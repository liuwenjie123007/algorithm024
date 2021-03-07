package Week_05;

/**
 * <h1>312. 戳气球</h1>
 * <a>https://leetcode-cn.com/problems/burst-balloons/</a>
 * <h3>解法：动态规划</h3>
 * <p>最优子结构: f(i,j) 为 在开区间(i,j) 戳破气球能获得的最多金币数 {0 <= i < j <= n}</p>
 * <p>k 为在开区间内 最后戳破气球的下标则</p>
 * <p>f(i,j) = MAX{f(i, k) + f(k, j) + nums[i] * nums[k] * nums[j]} (i < k < j)</p>
 * <p>状态空间int[n][n]</p>
 * <p>时间复杂度O(n^3) 空间复杂度O(n^2)</p>
 * @author WENJIE
 */
public class BurstBalloons {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] temp = new int [n + 2];
        temp[0] = 1;
        temp[n + 1] = 1;
        System.arraycopy(nums, 0, temp, 1, n);
        int[][] dp = new int[n + 2][n + 2];
        for (int i = 3; i <= n + 2; i++) {
            for (int l = 0; l <= n + 2 - i; l++) {
                int r = l + i - 1;
                int ret = 0;
                for (int k = l + 1; k < r; k++) {
                    int left = dp[l][k];
                    int right = dp[k][r];
                    ret = Math.max(ret, left + right + temp[l] * temp[k] * temp[r]);
                }
                dp[l][r] = ret;
            }

        }
        return dp[0][n + 1];
    }
}
