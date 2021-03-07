package Week_05;

/**
 * <h1>647. 回文子串</h1>
 * <a>https://leetcode-cn.com/problems/palindromic-substrings/</a>
 * <h3>解法1：中心扩展</h3>
 * <p>注意扩展中心有n * 2 - 1个</p>
 * <p>时间复杂度O(n^2),空间复杂都O(1)</p>
 * <h3>方法2 ： 动态规划</h3>
 * <p>最优子结构: dp[i，j] 在字符串s [i,j]区间的子串是否是一个回文串</p>
 * <p>1. s[i] == s[j] && (j - j < 2 || dp[i + 1][j - 1] 时， dp[i][j] = true</p>
 * <p>2. 否则 dp[i][j] 为 false</p>
 * <p>时间复杂度 O(n * n)</p>
 * <p>空间复杂度 O(n * n)</p>
 * @author WENJIE
 */
public class PalindromicSubStrings {
    public int countSubstrings1(String s) {
        int n = s.length();
        int ret = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= 1; j++) {
                int left = i;
                int right = i + j;
                while (left >= 0 && right < n && s.charAt(left--) == s.charAt(right++))
                    ret++;
            }
        }
        return ret;
    }

    public int countSubstrings2(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int ret = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    ret++;
                }
            }
        }
        return ret;
    }
}
