package Week_03;

import java.util.*;

/**
 * <h1>47. 全排列 II</h1>
 * <a>https://leetcode-cn.com/problems/permutations-ii/</a>
 * <h3>解法：回溯</h3>
 * <p>时间复杂度O(n^n), 空间复杂度O(n)</p>
 * @author WENJIE
 */
public class PermutationsIi {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        Deque<Integer> state = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        backtrace(ret, state, used, nums);
        return ret;
    }

    private void backtrace(List<List<Integer>> ret, Deque<Integer> state, boolean[] used, int[] nums) {
        if (state.size() == nums.length) {
            ret.add(new ArrayList<>(state));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 保证在指定位置，重复数之中只有第一个会被用到
            if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            state.push(nums[i]);
            used[i] = true;
            backtrace(ret, state, used, nums);
            state.pop();
            used[i] = false;
        }
    }
}
