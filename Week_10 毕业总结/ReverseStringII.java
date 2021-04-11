package Week_10;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>387. 字符串中的第一个唯一字符</h1>
 * <a>https://leetcode-cn.com/problems/first-unique-character-in-a-string/</a>
 * <h3> </h3>
 * @author WENJIE
 */
public class ReverseStringII {
    public String reverseStr(String s, int k) {
        char[] a = s.toCharArray();
        for (int start = 0; start < a.length; start += 2 * k) {
            int i = start, j = Math.min(start + k - 1, a.length - 1);
            while (i < j) {
                char tmp = a[i];
                a[i++] = a[j];
                a[j--] = tmp;
            }
        }
        return new String(a);
    }
}
