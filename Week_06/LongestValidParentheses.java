package Week_05;

/**
 * <h1>32. 最长有效括号</h1>
 * <a>https://leetcode-cn.com/problems/longest-valid-parentheses/</a>
 * <h3>解法：动态规划</h3>
 * <p>最优子结构: dp[i] 为 以 s[i] 结尾的最长有效括号的长度</p>
 * <p>1. s[i] == '(' 则 dp[i] = 0;</p>
 * <p>2. s[i] == ')' && s[i - 1] == '(', 则dp[i] = dp[i - 2] + 2</p>
 * <p>3. s[i] == ')' && s[i - 1] == ')' && s[i - dp[i - 1] - 1] = '(', 则 dp[i] = dp[i - 1] + dp[i - dp[i -1] - 2] + 2</p>
 * <p>4. 其他情况 dp[i] = 0;</p>
 * <p>状态空间 int[n]</p>
 * <p>时间复杂度 O(n), 空间复杂度 O(n)</p>
 * @author WENJIE
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int n = s.length();
        int[] dp = new int[n];
        int max = 0;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = 2 + dp[i - 1] + ((i - dp[i - 1] - 2) >= 0 ? dp[i - dp[i - 1] - 2] : 0);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
