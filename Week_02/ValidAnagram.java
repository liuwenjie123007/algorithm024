package Week_02;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>242. 有效的字母异位词</h1>
 * <a>https://leetcode-cn.com/problems/valid-anagram/description/</a>
 * <h3>方法1:暴力法</h3>
 * <p>时间复杂度O(n^2),空间复杂都O(n)</p>
 * <h3>方法2:排序比较</h3>
 * <p>时间复杂度O(nLogN),空间复杂都O(1)</p>
 * <h3>方法3：Map计数，两次扫描</h3>
 * <p>时间复杂度O(n)</p>
 * <p>空间复杂度度O(n)</p>
 * @author WENJIE
 */
public class ValidAnagram {
    // 解法3
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : t.toCharArray()) {
            Integer count = map.get(c);
            if (count == null || count == 0)
                return false;
            map.put(c, count - 1);
        }
        return true;
    }
}
