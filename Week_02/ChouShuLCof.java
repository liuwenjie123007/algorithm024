package Week_02;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>剑指 Offer 49. 丑数</h1>
 * <a>https://leetcode-cn.com/problems/chou-shu-lcof/</a>
 * <h3>解法1：动态规划, 丑数可根据小的丑数递推而来</h3>
 * <p>时间复杂度O(n), 空间复杂度O(n)</p>
 * @author WENJIE
 */
public class ChouShuLCof {
    public int nthUglyNumber(int n) {
        int u2 = 0;
        int u3 = 0;
        int u5 = 0;
        int[] temp = new int[n];
        temp[0] = 1;
        for (int i = 1; i < n; i++) {
            int ugly2 = temp[u2] * 2;
            int ugly3 = temp[u3] * 3;
            int ugly5 = temp[u5] * 5;
            temp[i] = Math.min(ugly2, Math.min(ugly3, ugly5));
            if (ugly2 == temp[i]) u2++;
            if (ugly3 == temp[i]) u3++;
            if (ugly5 == temp[i]) u5++;
        }
        return temp[n - 1];
    }
}
