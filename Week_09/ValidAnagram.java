package Week_09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * <h1>242. 有效的字母异位词</h1>
 * <a>https://leetcode-cn.com/problems/valid-anagram/</a>
 * <h3> </h3>
 * @author WENJIE
 */
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        char[] chars1 = s.toCharArray();
        Arrays.sort(chars1);
        char[] chars2 = t.toCharArray();
        Arrays.sort(chars2);
        return String.valueOf(chars1).equals(String.valueOf(chars2));
    }
}
