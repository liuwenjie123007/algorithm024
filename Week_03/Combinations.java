package Week_03;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * <h1>77. 组合</h1>
 * <a>https://leetcode-cn.com/problems/combinations/</a>
 * <h3>解法：回溯</h3>
 * <p>时间复杂度O(n * n!), 空间复杂度O(n)</p>
 * @author WENJIE
 */
public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ret = new ArrayList<>();
        Deque<Integer> state = new LinkedList<>();
        backtrace(0, ret, state, n, k);
        return ret;
    }

    private void backtrace(int level, List<List<Integer>> ret, Deque<Integer> state, int n, int k) {
        if (state.size() == k) {
            ret.add(new ArrayList<>(state));
            return;
        }
        for (int i = level + 1; i <= n; i++) {
            state.push(i);
            backtrace(i, ret, state, n, k);
            state.pop();
        }
    }
}
