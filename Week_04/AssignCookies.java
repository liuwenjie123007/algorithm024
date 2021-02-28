package Week_04;

import java.util.Arrays;

/**
 * <h1>455. 分发饼干</h1>
 * <a>https://leetcode-cn.com/problems/assign-cookies/description/</a>
 * <h3>解法：贪心单次匹配</h3>
 * <p>时间复杂度O(n), 空间复杂度O(1)</p>
 * @author WENJIE
 */
public class AssignCookies {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int eat = 0;
        for (int i = 0; i < s.length; i++) {
            if (g[eat] <= s[i]) eat++;
            if (eat == g.length) break;
        }
        return eat;
    }
}
