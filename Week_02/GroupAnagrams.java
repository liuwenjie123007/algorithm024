package Week_02;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <h1>49. 字母异位词分组</h1>
 * <a>https://leetcode-cn.com/problems/group-anagrams/</a>
 * <h3>解法1：hash缓存，排好序的String, 击中则添加到数组中</h3>
 * <p>时间复杂度O(n*kLogK, k为字符串长度) , 空间复杂度O(nk)</p>
 * <h3>解法2：使用Stream重构</h3>
 * <p>时间复杂度O(n*kLogK, k为字符串长度) , 空间复杂度O(nk)</p>
 * <h3>解法3：基于26字母的有限长度，使用计数法手写hash函数</h3>
 * <p>时间复杂度O(n*k, k为字符串长度) , 空间复杂度O(nk)</p>
 * @author WENJIE
 */
public class GroupAnagrams {
    //解法1
    public List<List<String>> groupAnagrams1(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedStr = String.valueOf(chars);
            List<String> list = map.getOrDefault(sortedStr, new ArrayList<>());
            list.add(str);
            map.put(sortedStr, list);
        }
        return new ArrayList<>(map.values());
    }

    //解法2
    public List<List<String>> groupAnagrams2(String[] strs) {
        return new ArrayList<>(Arrays.stream(strs)
                .collect(Collectors.groupingBy(str -> {
                    char[] chars = str.toCharArray();
                    Arrays.sort(chars);
                    return String.valueOf(chars);
                })).values());
    }

    //解法3
    public List<List<String>> groupAnagrams3(String[] strs) {
        return new ArrayList<>(Arrays.stream(strs)
                .collect(Collectors.groupingBy(str -> {
                    int[] counter = new int[26];
                    for (int i = 0; i < str.length(); i++) {
                        counter[str.charAt(i) - 'a']++;
                    }
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < 26; i++) {
                        if (counter[i] != 0) {
                            sb.append((char) ('a' + i));
                            sb.append(counter[i]);
                        }
                    }
                    return sb.toString();
                })).values());
    }
}
