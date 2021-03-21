package Week_07;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>22. 括号生成</h1>
 * <a>https://leetcode-cn.com/problems/climbing-stairs/</a>
 * <h3>解法1: DFS剪枝</h3>
 * @author WENJIE
 */
public class GenerateParentheses {
    List<String> ret = new ArrayList<>();
    // dfs剪枝
    public List<String> generateParenthesis(int n) {
        dfs(n, 0, 0, "");
        return ret;
    }

    private void dfs(int n, int left, int right, String buffer) {
        if (left == n && right == n) {
            ret.add(buffer);
            return;
        }

        if (left < n)
            dfs(n, left + 1, right, buffer + "(");
        if (left > right && right < n)
            dfs(n, left, right + 1, buffer + ")");
    }
}
