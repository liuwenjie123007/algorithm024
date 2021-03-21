package Week_07;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <h1>127. 单词接龙</h1>
 * <a>https://leetcode-cn.com/problems/word-ladder/</a>
 * <h3>解法1: 双向BFS</h3>
 * @author WENJIE
 */
public class WordLadder {
    Set<String> startQueue = new HashSet<>();
    Set<String> endQueue = new HashSet<>();
    Set<String> memo = new HashSet<>();
    Set<String> wordSet = new HashSet<>();

    // 双向bfs
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        wordSet.addAll(wordList);
        if (!wordSet.contains(endWord))
            return 0;

        startQueue.add(beginWord);
        endQueue.add(endWord);
        int step = 0;
        while (!startQueue.isEmpty() && !endQueue.isEmpty()) {
            if (startQueue.size() > endQueue.size()) {
                Set<String> temp = endQueue;
                endQueue = startQueue;
                startQueue = temp;
            }

            step++;

            Set<String> nextQueue = new HashSet<>();

            for (String s : startQueue) {
                char[] chars = s.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char current = chars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == current)
                            continue;
                        chars[i] = c;
                        String next = String.valueOf(chars);
                        if (wordSet.contains(next)) {
                            if (endQueue.contains(next))
                                return step + 1;
                            if (!memo.contains(next)) {
                                memo.add(next);
                                nextQueue.add(next);
                            }
                        }
                    }
                    chars[i] = current;
                }
            }
            startQueue = nextQueue;
        }
        return 0;
    }
}
