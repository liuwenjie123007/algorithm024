package Week_05;

import java.util.Arrays;

/**
 * <h1>410. 分割数组的最大值</h1>
 * <a>https://leetcode-cn.com/problems/split-array-largest-sum/</a>
 * <h3>方法 ： 动态规划</h3>
 * <p>最优子结构: f(i,j) 数组前i个数分割为j段最大连续子数组的最小值 </p>
 * <p>sub(l, r) 表示数组在区间[l,r]之和 </p>
 * <p>dp[i][j] = MIN{MAX{f[k][j-1], sub(k+1, i}, ....} k= [0, i - 1]</p>
 * <p>状态空间 int[n][m]</p>
 * <p>时间复杂度 O(n * n * m)</p>
 * <p>空间复杂度 O(n * m)</p>
 * @author WENJIE
 */
public class SplitArrayLargestSum {
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        int[] sub = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sub[i + 1] = sub[i] + nums[i];
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, m); j++) {
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], sub[i] - sub[k]));
                }
            }
        }
        return dp[n][m];
    }
}
