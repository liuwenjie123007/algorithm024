package Week_05;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>76. 最小覆盖子串</h1>
 * <a>https://leetcode-cn.com/problems/minimum-window-substring/</a>
 * <h3>解法：LeetCode提供的解法为滑动窗口解法。</h3>
 * <p>没有典型的最优子接口递推方程, 还没有好好体会到，TODO 多做几遍</p>
 * <p>时间复杂度 O(n), 空间复杂度 O(n)</p>
 * @author WENJIE
 */
public class MinumumWindowSubString {
    Map<Character, Integer> ori = new HashMap<>();
    Map<Character, Integer> cnt = new HashMap<>();

    public String minWindow(String s, String t) {
        int tLen = t.length();
        // 目标子串计数
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }
        int l = 0;
        int r = -1;
        int len = Integer.MAX_VALUE;
        int ansL = -1;
        int ansR = -1;
        int sLen = s.length();
        while (r < sLen) {
            r++;
            if (r < sLen && ori.containsKey(s.charAt(r))) {
                // 当前窗口计数
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
            }
            while (check() && l <= r) {
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                if (ori.containsKey(s.charAt(l))) {
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                }
                ++l;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    private boolean check() {
        for (Map.Entry<Character, Integer> entry : ori.entrySet()) {
            // 检查是否符合子串要求
            if (cnt.getOrDefault(entry.getKey(), 0) < entry.getValue())
                return false;
        }
        return true;
    }
}
