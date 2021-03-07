package Week_05;

/**
 * <h1>91. 解码方法</h1>
 * <a>https://leetcode-cn.com/problems/decode-ways/</a>
 * <h3>解法：动态规划</h3>
 * <p>最优子结构: dp[i] 为 编码方法总数</p>
 * <p>1. s[i] == 0 , 若 s[i - 1] == 1 || 2 则， dp[i] = dp[i - 1]  否则 全局误解 return 0;</p>
 * <p>2. s[i - 1] = 1 , dp[i] = dp[i - 1] + dp[i - 2]</p>
 * <p>3. s[i - 1] = 2 And  0<= s[i] <= 6 则，  dp[i] = dp[i - 1] + dp[i - 2]</p>
 * <p>4. 其他情况 dp[i] = dp[i - 1]</p>
 * <p>状态空间 int[n]</p>
 * <p>时间复杂度O(n), 空间复杂度O(n) 每次只需扫描前两个元素，可优化到O(1)</p>
 * @author WENJIE
 */
public class DecodeWays {
    public int numDecodings(String s) {
        if (s.isEmpty() || s.startsWith("0")) return 0;
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2')
                    dp[i + 1] = dp[i - 1];
                else
                    return 0;
            } else {
                if (s.charAt(i - 1) == '1' || (s.charAt(i - 1) == '2' && s.charAt(i) <= '6')) {
                    dp[i + 1] = dp[i] + dp[i - 1];
                } else {
                    dp[i + 1] = dp[i];
                }
            }
        }
        return dp[s.length()];
    }
}
