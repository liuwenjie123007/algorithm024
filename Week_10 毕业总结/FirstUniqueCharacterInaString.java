package Week_10;

import java.util.*;

/**
 * <h1>387. 字符串中的第一个唯一字符</h1>
 * <a>https://leetcode-cn.com/problems/first-unique-character-in-a-string/</a>
 * <h3> </h3>
 * @author WENJIE
 */
public class FirstUniqueCharacterInaString {
    public int firstUniqChar(String s) {
        Map<Character, Integer> frequency = new HashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < s.length(); ++i) {
            if (frequency.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }
}
