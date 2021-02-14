package Week_03;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * <h1>46. 全排列</h1>
 * <a>https://leetcode-cn.com/problems/permutations/</a>
 * <h3>解法：回溯</h3>
 * <p>时间复杂度O(n^n), 空间复杂度O(n)</p>
 * @author WENJIE
 */
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        Deque<Integer> state = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        backtrace(ret, state, used, nums);
        return ret;
    }

    private void backtrace(List<List<Integer>> ret, Deque<Integer> state, boolean[] used, int[] nums) {
        if (state.size() == nums.length) {
            ret.add(new ArrayList<>(state));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            state.push(nums[i]);
            used[i] = true;
            backtrace(ret, state, used, nums);
            state.pop();
            used[i] = false;
        }
    }
}
