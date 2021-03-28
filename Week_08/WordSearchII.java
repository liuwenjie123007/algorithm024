package Week_08;

import java.util.*;

/**
 * <h1>212. 单词搜索 II</h1>
 * <a>https://leetcode-cn.com/problems/word-search-ii/</a>
 * <h3>解法: 字典树辅助回溯 </h3>
 *
 * @author WENJIE
 */
public class WordSearchII {
    List<String> ret = new ArrayList<>();
    char[][] board;
    int[][] dictionary = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (Character letter : word.toCharArray()) {
                if (node.children.containsKey(letter)) {
                    node = node.children.get(letter);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(letter, newNode);
                    node = newNode;
                }
            }
            node.word = word;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (root.children.containsKey(board[i][j])) {
                    backtrace(i, j, root);
                }
            }
        }

        return ret;
    }

    private void backtrace(int i, int j, TrieNode root) {
        Character letter = this.board[i][j];
        TrieNode current = root.children.get(letter);
        if (current.word != null) {
            ret.add(current.word);
            current.word = null;
        }

        board[i][j] = '#';
        for (int[] dir : dictionary) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < 0 || x >= board.length || y < 0 || y >= board[0].length)
                continue;
            if (current.children.containsKey(board[x][y])) {
                backtrace(x, y, current);
            }
        }
        board[i][j] = letter;

        // 由于结果集不重复，在这里删除叶子节点可大幅度加速
        if (current.children.isEmpty()) {
            root.children.remove(letter);
        }
    }

    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        String word = null;
    }
}
