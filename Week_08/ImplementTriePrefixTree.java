package Week_08;

/**
 * <h1>208. 实现 Trie (前缀树)</h1>
 * <a>https://leetcode-cn.com/problems/implement-trie-prefix-tree/#/description/a>
 * <h3> </h3>
 * @author WENJIE
 */
public class ImplementTriePrefixTree {
    class Trie {
        private boolean isEnd;
        private Trie[] next;

        public Trie() {
            isEnd = false;
            next = new Trie[26];
        }

        public void insert(String word) {
            if (word == null || word.length() == 0)
                return;
            Trie curr = this;
            for (int i = 0; i < word.length(); i++) {
                int n = word.charAt(i) - 'a';
                if (curr.next[n] == null)
                    curr.next[n] = new Trie();
                curr = curr.next[n];
            }
            curr.isEnd = true;
        }

        public boolean search(String word) {
            Trie node = searchPrefix(word);
            return node != null && node.isEnd;
        }

        private Trie searchPrefix(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                node = node.next[word.charAt(i) - 'a'];
                if (node == null)
                    return null;
            }
            return node;
        }

        public boolean startsWith(String prefix) {
            return searchPrefix(prefix) != null;
        }
    }
}
