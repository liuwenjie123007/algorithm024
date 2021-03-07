package Week_05;

import java.util.Arrays;

/**
 * <h1>552. 学生出勤记录 II</h1>
 * <a>https://leetcode-cn.com/problems/student-attendance-record-ii/</a>
 * <h3>方法 ： 动态规划</h3>
 * <p>最优子结构: f(n) 为 长度为n 的可奖励记录（只包含字母 L 和 P)</p>
 * <p>f(n) = 2f(n-1) - f(n-4) </p>
 * <p>解：SUM{f[i - 1] * f[n - i]} (i = [1,n])</p>
 * <p>状态空间 long[n]</p>
 * <p>时间复杂度 O(n)</p>
 * <p>空间复杂度 O(n) 每次迭代只用到前4个元素，可以将空间压缩至O(1)</p>
 * @author WENJIE
 */
public class StudentAttendanceRecordII {
    public int checkRecord(int n) {
        long M = 1000000007;
        long[] dp = new long[n <= 5 ? 6 : n + 1];
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 4;
        dp[3] = 7;
        for (int i = 4; i <= n; i++) {
            dp[i] = ((2 * dp[i - 1]) % M + (M - dp[i - 4])) % M;
        }
        long sum = dp[n];
        for (int i = 1; i <= n; i++) {
            sum += (dp[i - 1] * dp[n - i]) % M;
        }
        return (int) (sum % M);
    }
}
