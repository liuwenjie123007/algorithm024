package Week_04;

import java.util.*;

/**
 * <h1>127. 单词接龙</h1>
 * <a>https://leetcode-cn.com/problems/word-ladder/</a>
 * <h3>解法：广度优先搜索</h3>
 * <p>时间复杂度O(NxC^2),其中N为wordList的长度，C为列表中单词的长度。 空间复杂度O(NxC^2)</p>
 * @author WENJIE
 */
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int step = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            step++;
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                visited.add(word);
                if (changeOneWord(wordSet, visited, queue, word, endWord)) return step + 1;
            }
        }
        return 0;
    }

    private boolean changeOneWord(Set<String> wordSet, Set<String> visited, Queue<String> queue, String word, String endWord) {
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char currentChar = chars[i];
            for (char k = 'a'; k <= 'z'; k++) {
                if (k == currentChar) {
                    continue;
                }
                chars[i] = k;
                String nextWord = String.valueOf(chars);
                if (wordSet.contains(nextWord)) {
                    if (nextWord.equals(endWord)) return true;
                    if (!visited.contains(nextWord)) {
                        queue.add(nextWord);
                        visited.add(nextWord);
                    }
                }
            }
            chars[i] = currentChar;
        }
        return false;
    }
}
